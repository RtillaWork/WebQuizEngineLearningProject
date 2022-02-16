package engine.persistencelayer;

import engine.businesslayer.PlayerQuiz;
import engine.security.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerQuizRepository extends JpaRepository<PlayerQuiz, Long> {

    List<PlayerQuiz> findAll();

    Page<PlayerQuiz> findAll(Pageable pageable);

    <S extends PlayerQuiz> S save(S entity);

    Optional<PlayerQuiz> findById(Long aLong);


    Page<PlayerQuiz> findByPlayer(UserEntity player, Pageable pageable);

    Page<PlayerQuiz> findByCompleted(Boolean isCompleted, Pageable pageable);

    Page<PlayerQuiz> findByPlayerAndCompleted(UserEntity player, boolean isCompleted, Pageable pageable);

    Page<PlayerQuiz> findByPlayerAndCompletedOrderByLastAttemptedAtDesc(UserEntity player, boolean isCompleted, Pageable pageable);

}
