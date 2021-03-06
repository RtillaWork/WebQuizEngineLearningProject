package engine.presentationlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import engine.businesslayer.QuizGrader;
import engine.persistencelayer.QuizService;
import engine.businesslayer.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static engine.ApiConfig.*;

@RestController
@Validated
public class QuizEngineController {


    final static String HS_TESTING_PATH = "/actuator/shutdown"; // POST /actuator/shutdown

    @Autowired
    private QuizService quizService;
//    private ConcurrentHashMap<Long, Quiz> quizMap = new ConcurrentHashMap<>();

    @PostMapping(API_ADD_QUIZ)
    public String createQuiz(@RequestBody @Valid Quiz quiz) throws JsonProcessingException {
        // NOTE Keys are a set, do not repeat, simplistic next id is current size starting with 0
        quiz = quizService.save(quiz);

        long id = quiz.getId(); // Math.max(quizMap.size(), 0);
//        quizMap.put(id, quiz);
        return QuizMarshalling.toJson(quiz, id);
    }

    @PostMapping(API_SOLVE_QUIZ)
    public ResponseEntity<String> solveQuiz(@PathVariable @NotNull long id,
                                            @RequestBody(required = false) QuizAnswer quizAnswer) {
        quizAnswer = (quizAnswer == null ? new QuizAnswer() : quizAnswer);
        Optional<Quiz> quiz = quizService.findById(id); // quizMap.get(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(
                    QuizGrader.feedback(quizAnswer, quiz.get()),
                    HttpStatus.OK);
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

    @GetMapping(API_GET_QUIZZES)
    public ResponseEntity<String> getQuiz() throws JsonProcessingException {
        var quizzes = quizService.asMapFindAll();
        return new ResponseEntity<String>(QuizMarshalling.toJson(quizzes), HttpStatus.OK);

    }


}
