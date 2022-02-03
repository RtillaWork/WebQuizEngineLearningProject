package engine.security.service;

import engine.businesslayer.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//    Optional<UserLoginInfo> findByEmail(String email);
//    User save(UserService user);
//    Optional<User> findById(Long aLong);
//    boolean existsById(Long aLong);
//    Iterable<User> findAll();


    @Override
    <S extends User> S save(S entity);
}
