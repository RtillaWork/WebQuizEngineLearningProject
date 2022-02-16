package engine.businesslayer;

import engine.presentationlayer.QuizAnswer;
import engine.security.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QuizGrader {

    private static final String quizResponseJsonTemplate = "{\"success\":%s,\"feedback\":\"%s\"}";

    private static final Map<Boolean, String> feedbackSuccess = Map.of(
            true, "Congratulations, you\'re right!",
            false, "Wrong answer! Please, try again."
    );

    public boolean gradeAnswer(Quiz quiz, QuizAnswer quizAnswer) {
        if (!quizAnswer.getAnswer().isEmpty() && !quiz.getAnswer().isEmpty()) {
            return quiz.getAnswer().equals(quizAnswer.getAnswer());
        } else if (quizAnswer.getAnswer().isEmpty() && quiz.getAnswer().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String feedback(Quiz quiz, QuizAnswer quizAnswer) {
        boolean success = gradeAnswer(quiz, quizAnswer);
        return String.format(quizResponseJsonTemplate, success, feedbackSuccess.get(success));
    }


    public String gradedResponse(Quiz quiz, QuizAnswer quizAnswer, UserEntity player) {
        boolean success = gradeAnswer(quiz, quizAnswer);
        return String.format(quizResponseJsonTemplate, success, feedbackSuccess.get(success));
    }

    public String gradedResponse(PlayerQuiz playedQuiz) {

        boolean success = gradeAnswer(playedQuiz.getPlayedQuiz(), new QuizAnswer(playedQuiz.getAnswer()));
        return String.format(quizResponseJsonTemplate, success, feedbackSuccess.get(success));
    }

    public String feedbackDebug(Quiz quiz, QuizAnswer quizAnswer) {
        boolean success = gradeAnswer(quiz, quizAnswer);
        return String.format(
                " {success: %s, feedbackSuccess: %s, " +
                        "quizAnswer: %s, quiz: %s" +
                        "quizAnswer.getAnswer(): %s" +
                        "quiz.getAnswer(): %s }",
                success, feedbackSuccess.get(success), quizAnswer.toString(), quiz.toString(),
                quizAnswer.getAnswer(), quiz.getAnswer(),
                quizAnswer.getAnswer().equals(quiz.getAnswer()));
    }
}
