package engine.presentationlayer;

import engine.businesslayer.UserEntity;
import engine.persistencelayer.UserEntityRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static engine.ApiConfig.API_REGISTER_USER;

@RestController
@Validated
public class UserEntityController {

    @Autowired
    UserEntityRepositoryService userEntityRepositoryService;

    @PostMapping(API_REGISTER_USER)
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserEntity user) {
        UserEntity newUser = userEntityRepositoryService.save(user);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            // TODO return this if email already taken
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
