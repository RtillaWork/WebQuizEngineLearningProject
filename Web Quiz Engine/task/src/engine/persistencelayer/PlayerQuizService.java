package engine.persistencelayer;

import engine.businesslayer.PlayerQuiz;
import engine.businesslayer.Quiz;
import engine.businesslayer.QuizGrader;
import engine.presentationlayer.QuizAnswer;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Page<PlayerQuiz> findByPlayerAndIsCompletedSorted(UserEntity player, int fromPage, int pageSize) {
        Pageable page = PageRequest.of(fromPage, pageSize,
                Sort.by(Sort.Direction.DESC, "lastAttemptedAt"));

        Page<PlayerQuiz> playedQuizzesPage = pqr.findByPlayerAndCompleted(player, true, page);
        return playedQuizzesPage;
    }


    private boolean grade(Quiz quiz, QuizAnswer quizAnswer) {
        return quizGrader.gradeAnswer(quiz, quizAnswer);
    }

    public String gradedResponse(PlayerQuiz playedQuiz) {
        return quizGrader.gradedResponse(playedQuiz);
    }

    public PlayerQuiz saveIfSuccess(Quiz quiz, QuizAnswer quizAnswer, UserEntity player) {

        boolean success = grade(quiz, quizAnswer);
        PlayerQuiz playedQuiz = new PlayerQuiz(player, quiz, quizAnswer.getAnswer(), success, LocalDateTime.now());
        return pqr.saveAndFlush(playedQuiz);
    }

    public String feedback(Quiz quiz, QuizAnswer quizAnswer) {
        return quizGrader.feedback(quiz, quizAnswer);
    }

}
