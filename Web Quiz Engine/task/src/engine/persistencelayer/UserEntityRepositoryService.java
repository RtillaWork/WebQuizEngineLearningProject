package engine.persistencelayer;

//import engine.security.PasswordEncoderImpl;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserEntityRepositoryService implements UserDetailsService {
    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


//    public Optional<UserEntity> findByEmail(String email) {
//        var user = Optional.ofNullable(new UserEntity());
//        for (var u : findAll()) {
//            if (u.getEmail().equals(email)) {
//                user = Optional.of(u);
//                break;
//            }
//        }
//        return user;
//    }

    public Optional<UserEntity> findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return findByEmail(username);
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userEntityRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            return null;
        }
//        else {
//            throw new Exception("Error saving UserEntity: makeUsernameValid(...) failed");
//        }
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByEmail(username)
                .orElseThrow();
        return user;
    }
}
