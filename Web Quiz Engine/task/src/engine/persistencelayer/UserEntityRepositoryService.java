package engine.persistencelayer;

//import engine.security.PasswordEncoderImpl;

import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityRepositoryService implements UserDetailsService {
    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static boolean isUsernameValid(UserEntity user) {
        return (user.getUsername() != null && !user.getUsername().isEmpty()) || user.getEmail() == null;
    }

    private static boolean makeUsernameValid(UserEntity user) {
        if (isUsernameValid(user)) {
            return false;
        } else {
            user.setUsername(user.getEmail());
            return true;
        }
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return findByEmail(username);
    }

    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userEntityRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByEmail(username)
                .orElseThrow();
        return user;
    }
}
