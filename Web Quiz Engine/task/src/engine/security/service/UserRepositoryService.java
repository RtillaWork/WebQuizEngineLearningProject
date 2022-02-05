package engine.security.service;

import engine.businesslayer.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryService {
    @Autowired
    UserRepository userRepository;


    public Optional<UserEntity> findByEmail(String email) {
        var user = Optional.ofNullable(new UserEntity());
        for (var u : findAll()) {
            if (u.getEmail().equals(email)) {
                user = Optional.of(u);
                break;
            }
        }

        return user;
    }

    public static boolean isUsernameValid(UserEntity user) {
        if ((user.getUsername() == null || user.getUsername().isEmpty()) && user.getEmail() != null) {
            return false;
        } else {
            return true;
        }
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();

    }

    public static boolean makeUsernameValid(UserEntity user) {
        if (isUsernameValid(user)) {
            return false;
        } else {
            user.setUsername(user.getEmail());
            return true;
        }
    }

    public UserEntity save(UserEntity user) throws Exception {
        if (isUsernameValid(user)) {
            userRepository.save(user);
            return user;
        } else if (makeUsernameValid(user)) {
            userRepository.save(user);
            return user;
        } else {
            throw new Exception("Error saving UserEntity: makeUsernameValid(...) failed");
        }
    }

}
