package engine.persistencelayer;

import engine.businesslayer.PlayerQuiz;
import engine.businesslayer.Quiz;
import engine.businesslayer.QuizGrader;
import engine.presentationlayer.QuizAnswer;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerQuizService {

    @Autowired
    PlayerQuizRepository pqr;

    @Autowired
    QuizGrader quizGrader;

//    public List<PlayerQuiz> findAllByPlayerAndIsCompleted(UserEntity player) {
//        return pqr.findByPlayerAndIsCompleted(player);
//    }

    public Page<PlayerQuiz> findByPlayerAndIsCompleted(UserEntity player, int fromPage, int toPageExc) {
        Pageable page = PageRequest.of(fromPage, toPageExc);
        Page<PlayerQuiz> playedQuizzesPage = pqr.findByPlayerAndCompleted(player, true, page);
        return playedQuizzesPage;
    }

    public Page<PlayerQuiz> findByPlayerAndIsCompletedSorted(UserEntity player, int fromPage, int toPageExc) {
        Pageable page = PageRequest.of(fromPage, toPageExc);
        Page<PlayerQuiz> playedQuizzesPage =
                pqr.findByPlayerAndCompletedOrderByLastAttemptedAtDesc(player, true, page);
        return playedQuizzesPage;
    }


    private boolean grade(Quiz quiz, QuizAnswer quizAnswer) {
        return quizGrader.gradeAnswer(quiz, quizAnswer);
    }

    public String gradedResponse(PlayerQuiz playedQuiz) {
        return quizGrader.gradedResponse(playedQuiz);
    }

    public PlayerQuiz save(Quiz quiz, QuizAnswer quizAnswer, UserEntity player) {
//        LocalDateTime attemptedAt = LocalDateTime.now();
//        boolean success = quizGrader.gradeAnswer(quiz, quizAnswer);
        boolean success = grade(quiz, quizAnswer);
//        PlayerQuiz playedQuiz = pqr.save(new PlayerQuiz(quiz, player, success));
//        PlayerQuiz playedQuiz = pqr.save(new PlayerQuiz(player, quiz, quizAnswer.getAnswer(), success));
//        PlayerQuiz playedQuiz = pqr.saveAndFlush(new PlayerQuiz(player, quiz, quizAnswer.getAnswer(), success));
        PlayerQuiz playedQuiz = pqr
                .saveAndFlush(
                        new PlayerQuiz(player, quiz, quizAnswer.getAnswer(),
                                success, LocalDateTime.now()));

        return playedQuiz;
//        PlayerQuiz playedQuiz = new PlayerQuiz(quiz, player, success, attemptedAt);
    }

    public String feedback(Quiz quiz, QuizAnswer quizAnswer) {
        return quizGrader.feedback(quiz, quizAnswer);
    }

}
