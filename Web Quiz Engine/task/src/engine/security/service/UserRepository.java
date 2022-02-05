package engine.security.service;

import engine.businesslayer.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    Optional<UserLoginInfo> findByEmail(String email);
//    User save(UserService user);
//    Optional<User> findById(Long aLong);
//    boolean existsById(Long aLong);
//    Iterable<User> findAll();


    @Override
    <S extends UserEntity> S save(S entity);
}
