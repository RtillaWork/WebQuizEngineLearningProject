package engine;

public class ApiConfig {
    public static final String API_ROOT_PATH = "/api";

    public static final String API_QUIZZES_PATH = API_ROOT_PATH + "/quizzes";

    public static final String API_ADD_QUIZ = API_QUIZZES_PATH; // POST Quiz
    public static final String API_SOLVE_QUIZ = API_QUIZZES_PATH + "/{id}/solve"; // POST QuizAnswer
    public static final String API_GET_QUIZ = API_QUIZZES_PATH + "/{id}"; // GET quiz_id
    public static final String API_GET_QUIZZES = API_QUIZZES_PATH; // GET all_quizzes

    public static final String API_REGISTER_USER = API_ROOT_PATH + "/register"; // POST json
    public final static String API_SESSION_LOGIN = API_ROOT_PATH + "/login";
    public final static String API_SESSION_LOGOUT = API_ROOT_PATH + "/logout";
}
