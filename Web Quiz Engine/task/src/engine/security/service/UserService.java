package engine.security.service;

import engine.businesslayer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        var user = Optional.ofNullable(new User());
        for (var u : findAll()) {
            if (u.getEmail().equals(email)) {
                user = Optional.of(u);
                break;
            }
        }

        return user;
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();

    }

}
