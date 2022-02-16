package engine;

public class ApiConfig {
    public static final String API_ROOT_PATH = "/api";
    public static final String DEBUG_API_PREFIX = "/debug";
    public static final String DEBUG_API_ROOT_PATH = DEBUG_API_PREFIX + API_ROOT_PATH;


    public static final String API_QUIZZES_PATH = API_ROOT_PATH + "/quizzes";

    public static final String API_ADD_QUIZ = API_QUIZZES_PATH; // POST Quiz
    public static final String API_SOLVE_QUIZ = API_QUIZZES_PATH + "/{id}/solve"; // POST QuizAnswer
    public static final String API_GET_QUIZ = API_QUIZZES_PATH + "/{id}"; // GET quiz_id
    public static final String API_GET_QUIZZES = API_QUIZZES_PATH; // GET all_quizzes
    public static final String API_GET_QUIZZES_WITH_PAGING = API_GET_QUIZZES; // GET all_quizzes?page=
    public static final String DEBUG_API_GET_QUIZZES_WITH_PAGING_WITH_TO_MAP = DEBUG_API_PREFIX + API_GET_QUIZZES; // GET all_quizzes?page=

    public static final String API_DELETE_QUIZ = API_QUIZZES_PATH + "/{id}"; // DELETE a user owned quiz only

    public static final String API_REGISTER_USER = API_ROOT_PATH + "/register"; // POST json
    public static final String API_SESSION_LOGIN = API_ROOT_PATH + "/login";
    public static final String API_SESSION_LOGOUT = API_ROOT_PATH + "/logout";

    public static final String API_QUIZZES_COMPLETED_WITH_PAGING = API_QUIZZES_PATH + "/completed"; // GET with paging
    public static final String DEBUG_API_QUIZZES_PLAYED_ALL
            = DEBUG_API_PREFIX + API_QUIZZES_PATH + "/completed"; // GET all without paging

    public static final int API_MAX_PAGE_SIZE_QUIZZES = 10;
    public static final int API_MAX_PAGE_SIZE_PLAYED_QUIZZES = 10;

}
