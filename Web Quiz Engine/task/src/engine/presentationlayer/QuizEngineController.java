package engine.presentationlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import engine.businesslayer.PlayerQuiz;
import engine.businesslayer.QuizGrader;
import engine.persistencelayer.PlayerQuizService;
import engine.persistencelayer.QuizService;
import engine.businesslayer.Quiz;
import engine.persistencelayer.UserEntityRepositoryService;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Optional;

import static engine.ApiConfig.*;

@RestController
@Validated
public class QuizEngineController {


    final static String HS_TESTING_PATH = "/actuator/shutdown"; // POST /actuator/shutdown

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserEntityRepositoryService uers;

    @Autowired
    private PlayerQuizService playerQuizService;

//    private ConcurrentHashMap<Long, Quiz> quizMap = new ConcurrentHashMap<>();

    @PostMapping(API_ADD_QUIZ)
    public ResponseEntity<String> createQuiz(@RequestBody @Valid Quiz quiz, Principal principal)
            throws JsonProcessingException {
        // NOTE Keys are a set, do not repeat, simplistic next id is current size starting with 0
        quiz = quizService.save(quiz, principal.getName());
        if (quiz != null) {
            long id = quiz.getId(); // Math.max(quizMap.size(), 0);
//        quizMap.put(id, quiz);
            return new ResponseEntity<>(QuizMarshalling.toJson(quiz, id), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

//    @Deprecated
//    @PostMapping(API_SOLVE_QUIZ)
//    public ResponseEntity<String> solveQuiz(@PathVariable @NotNull long id,
//                                            @RequestBody(required = false) QuizAnswer quizAnswer) {
//        quizAnswer = (quizAnswer == null ? new QuizAnswer() : quizAnswer);
//        Optional<Quiz> quiz = quizService.findById(id); // quizMap.get(id);
//        if (quiz.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<String>(
//                    playerQuizService.feedback(quiz.get(), quizAnswer),
//                    HttpStatus.OK);
//        }
//    }

    @PostMapping(API_SOLVE_QUIZ)
    public ResponseEntity<String> solveQuiz(@PathVariable @NotNull long id,
                                            @RequestBody(required = false) QuizAnswer quizAnswer,
                                            Principal principal) {

        Optional<Quiz> quiz = quizService.findById(id); // quizMap.get(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            quizAnswer = (quizAnswer == null ? new QuizAnswer() : quizAnswer);
            UserEntity player = uers.findByUsername(principal.getName()).orElse(null);

            PlayerQuiz playedQuiz = playerQuizService.saveIfSuccess(quiz.get(), quizAnswer, player);

            if (playedQuiz != null) {
                return new ResponseEntity<String>(
                        playerQuizService.gradedResponse(playedQuiz),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
            }

//            return new ResponseEntity<String>(
//                    QuizGrader.gradedResponse(quizAnswer, quiz.get(), player),
//                    HttpStatus.OK);
        }
    }

    @GetMapping(API_GET_QUIZ)
    public ResponseEntity<String> getQuiz(@PathVariable long id) throws JsonProcessingException {
        Optional<Quiz> quiz = quizService.findById(id); // quizMap.get(id);

        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(QuizMarshalling.toJson(quiz.get(), id), HttpStatus.OK);
        }
    }

//    @GetMapping(API_GET_QUIZZES)
//    public ResponseEntity<String> getQuizzes() throws JsonProcessingException {
//        var quizzes = quizService.asMapFindAll();
//        return new ResponseEntity<String>(QuizMarshalling.toJson(quizzes), HttpStatus.OK);
//    }

    @GetMapping(API_GET_QUIZZES_WITH_PAGING)
    public ResponseEntity<Page<Quiz>> getQuizzesWithPaging(@RequestParam(defaultValue = "0") int page)
            throws JsonProcessingException {
        var quizzes = quizService.findAllQuizzes(page, API_MAX_PAGE_SIZE_QUIZZES);
//        return new ResponseEntity<String>(QuizMarshalling.toJson(quizzes), HttpStatus.OK);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);

    }

    @GetMapping(DEBUG_API_GET_QUIZZES_WITH_PAGING_WITH_TO_MAP)
    public ResponseEntity<String> DEBUG_getQuizzesWithPaging(@RequestParam(defaultValue = "0") int page)
            throws JsonProcessingException {
        var quizzes = quizService.findAllQuizzes(page, API_MAX_PAGE_SIZE_QUIZZES);
        return new ResponseEntity<String>(QuizMarshalling.toJson
                (quizService.toMapFromList(quizzes.toList())), HttpStatus.OK
        );
    }

    @DeleteMapping(API_DELETE_QUIZ)
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id, Principal principal) {
        Quiz quiz = quizService.findById(id).orElse(null);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity author = uers.findByUsername(principal.getName()).orElse(null);
        if (!quiz.getQuizAuthor().equals(author)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            quizService.delete(quiz, author);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }
}
