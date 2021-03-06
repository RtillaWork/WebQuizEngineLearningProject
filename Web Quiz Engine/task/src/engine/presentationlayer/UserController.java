package engine.presentationlayer;

import engine.businesslayer.User;
import engine.security.service.UserRepository;
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
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(API_REGISTER_USER)
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            // TODO return this if email already taken
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
