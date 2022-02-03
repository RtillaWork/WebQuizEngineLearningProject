package engine.persistencelayer;

import engine.businesslayer.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
    Optional<Quiz> findById(Long id);

    List<Quiz> findAll();

    //    Quiz addQuiz(Quiz quiz);
//    void saveAll(Map<Long, Quiz> quizMap);
//    void saveAll(Quiz quiz);
//    Map<Long, Quiz> asMapFindAll();
    Quiz save(Quiz quiz);

}

//    private QuizRepository quizRepository = new QuizRepository() {
//        @Override
//        public Optional<Quiz> findById(Long id) {
//            return Optional.empty();
//        }
//
//        @Override
//        public List<Quiz> findQuizList() {
//            return null;
//        }
//
//        @Override
//        public Quiz addQuiz(Quiz quiz) {
//            return null;
//        }
//
//        @Override
//        public <S extends Quiz> S save(S entity) {
//            return null;
//        }
//
//        @Override
//        public <S extends Quiz> Iterable<S> saveAll(Iterable<S> entities) {
//            return null;
//        }
//
//        @Override
//        public boolean existsById(Long aLong) {
//            return false;
//        }
//
//        @Override
//        public Iterable<Quiz> findAll() {
//            return null;
//        }
//
//        @Override
//        public Iterable<Quiz> findAllById(Iterable<Long> longs) {
//            return null;
//        }
//
//        @Override
//        public long count() {
//            return 0;
//        }
//
//        @Override
//        public void deleteById(Long aLong) {
//
//        }
//
//        @Override
//        public void delete(Quiz entity) {
//
//        }
//
//        @Override
//        public void deleteAll(Iterable<? extends Quiz> entities) {
//
//        }
//
//        @Override
//        public void deleteAll() {
//
//        }
//    }