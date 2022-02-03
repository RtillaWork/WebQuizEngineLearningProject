package engine.businesslayer;

import engine.presentationlayer.QuizAnswer;

import java.util.Map;

public class QuizGrader {

    private static final String quizResponseJsonTemplate = "{\"success\":%s,\"feedback\":\"%s\"}";

    private static final Map<Boolean, String> feedbackSuccess = Map.of(
            true, "Congratulations, you\'re right!",
            false, "Wrong answer! Please, try again."
    );

    private static boolean gradeAnswer(QuizAnswer quizAnswer, Quiz quiz) {
        if (!quizAnswer.getAnswer().isEmpty() && !quiz.getAnswer().isEmpty()) {
            return quiz.getAnswer().equals(quizAnswer.getAnswer());
        } else if (quizAnswer.getAnswer().isEmpty() && quiz.getAnswer().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static String feedback(QuizAnswer quizAnswer, Quiz quiz) {
        boolean success = gradeAnswer(quizAnswer, quiz);
        return String.format(quizResponseJsonTemplate, success, feedbackSuccess.get(success));
    }

    public static String feedbackDebug(QuizAnswer quizAnswer, Quiz quiz) {
        boolean success = gradeAnswer(quizAnswer, quiz);
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
