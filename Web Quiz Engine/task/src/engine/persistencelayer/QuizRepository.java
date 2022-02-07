package engine.persistencelayer;

import engine.businesslayer.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findById(Long id);
    List<Quiz> findAll();
    Quiz save(Quiz quiz);

}
