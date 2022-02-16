package engine.presentationlayer;

import engine.businesslayer.PlayerQuiz;
import engine.persistencelayer.PlayerQuizService;
import engine.persistencelayer.UserEntityRepositoryService;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static engine.ApiConfig.*;

@RestController
@Validated
public class UserEntityController {

    @Autowired
    UserEntityRepositoryService uers;

    @Autowired
    PlayerQuizService playerQuizService;

    @PostMapping(API_REGISTER_USER)
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserEntity user) {
        UserEntity newUser = uers.save(user);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            // TODO return this if email already taken
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(API_QUIZZES_COMPLETED_WITH_PAGING)
    public ResponseEntity<Page<PlayerQuiz>> getCompletedQuizzes(@RequestParam(defaultValue = "0") int page,
                                                                Principal principal) {
        UserEntity player = uers.findByUsername(principal.getName()).orElse(null);

        Page<PlayerQuiz> playerQuiz = playerQuizService
                .findByPlayerAndIsCompletedSorted(player, page, API_MAX_PAGE_SIZE_PLAYED_QUIZZES);
        if (playerQuiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(playerQuiz, HttpStatus.OK);
        }

    }
}
