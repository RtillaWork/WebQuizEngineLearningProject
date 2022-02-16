package engine.persistencelayer;

import engine.businesslayer.Quiz;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserEntityRepositoryService uers;

    Map<Long, Quiz> quizMap = new ConcurrentHashMap<>(Collections.emptyMap());

    public QuizService() {
    }

    public QuizService(Quiz quiz) {
        this.quizMap.put(quiz.getId(), quiz);
    }

    public QuizService(Map<Long, Quiz> quizMap) {
        this.quizMap = quizMap;
    }

    public void saveAll(Map<Long, Quiz> quizMap) {
        quizMap.forEach((aLong, quiz) -> quizRepository.save(quiz));
    }

    public void saveAll(List<Quiz> quizMap) {
        quizMap.forEach(quiz -> quizRepository.save(quiz));
    }

    public Quiz save(Quiz quiz) {
        try {
            UserEntity user = uers.findById(quiz.getQuizAuthor().getId()).orElseThrow();
            quiz.setQuizAuthor(user);
            return quizRepository.save(quiz);
        } catch (DataIntegrityViolationException d) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    public Map<Long, Quiz> asMapFindAll() {
        Map<Long, Quiz> quizMap = new HashMap<>();
        quizRepository.findAll().forEach(quiz -> quizMap.put(quiz.getId(), quiz));
        return quizMap;
    }

    public Map<Long, Quiz> findAllQuizzes() {
        return toMapFromList(quizRepository.findAll());
    }

//    public Map<Long, Quiz> findAllQuizzes(int fromPage, int toPageExc) {
//        Pageable page = PageRequest.of(fromPage, toPageExc);
//        Page<Quiz> quizPage = quizRepository.findAll(page);
//        return toMapFromList(quizPage.toList());
//    }

    public Page<Quiz> findAllQuizzes(int fromPage, int toPageExc) {
        Pageable page = PageRequest.of(fromPage, toPageExc);
        Page<Quiz> quizPage = quizRepository.findAll(page);
        return quizPage;
    }

    public Map<Long, Quiz> toMapFromList(List<Quiz> quizList) {
        Map<Long, Quiz> quizMap = new HashMap<>();
        quizList.forEach(quiz -> quizMap.put(quiz.getId(), quiz));
        return quizMap;
    }

    public Quiz save(Quiz quiz, String username) {
        try {
            UserEntity user = uers.findByUsername(username).orElseThrow();
            quiz.setQuizAuthor(user);
            return quizRepository.save(quiz);
        } catch (DataIntegrityViolationException d) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Quiz quiz, UserEntity author) {
        if (quiz.getQuizAuthor().equals(author)) {
            quizRepository.delete(quiz);
        }
    }
}
