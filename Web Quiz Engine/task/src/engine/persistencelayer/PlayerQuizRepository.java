package engine.persistencelayer;

import engine.businesslayer.PlayerQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerQuizRepository extends JpaRepository<PlayerQuiz, Long> {

//    PlayerQuiz findById(Long id);

}
