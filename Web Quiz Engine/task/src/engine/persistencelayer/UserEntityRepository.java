package engine.persistencelayer;

import engine.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
//    Optional<UserLoginInfo> findByEmail(String email);
//    User save(UserService user);
//    Optional<User> findById(Long aLong);
//    boolean existsById(Long aLong);
//    Iterable<User> findAll();

    @Override
    <S extends UserEntity> S save(S entity);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);


}
