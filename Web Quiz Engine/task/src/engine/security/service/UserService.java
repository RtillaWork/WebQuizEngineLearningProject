package engine.security.service;

import engine.businesslayer.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
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

    public UserEntity save(UserEntity user) {
        userRepository.save(user);
        return user;
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

}
