package engine.persistencelayer;

import engine.businesslayer.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class UserEntityRepositoryService {
    @Autowired
    UserEntityRepository userEntityRepository;


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

    private static boolean isUsernameValid(UserEntity user) {
        if ((user.getUsername() == null || user.getUsername().isEmpty()) && user.getEmail() != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    private static boolean makeUsernameValid(UserEntity user) {
        if (isUsernameValid(user)) {
            return false;
        } else {
            user.setUsername(user.getEmail());
            return true;
        }
    }

    public Optional<UserEntity> findById(Long id) {
        return userEntityRepository.findById(id);
    }

    public Iterable<UserEntity> findAll() {
        return userEntityRepository.findAll();

    }

    public UserEntity save(UserEntity user) {
        if (!isUsernameValid(user)) {
            makeUsernameValid(user);
        }
        try {
            return userEntityRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            return null;
        }
//        else {
//            throw new Exception("Error saving UserEntity: makeUsernameValid(...) failed");
//        }
    }

}
