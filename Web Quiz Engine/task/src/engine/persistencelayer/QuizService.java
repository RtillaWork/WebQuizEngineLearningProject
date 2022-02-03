package engine.persistencelayer;

import engine.businesslayer.Quiz;
import engine.persistencelayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuizService {
    @Autowired
    public QuizRepository quizRepository;
    //    private QuizRepository quizService = new QuizService();
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
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    public Map<Long, Quiz> asMapFindAll() {
        Map<Long, Quiz> quizMap = new HashMap<>();
        quizRepository.findAll().forEach(quiz -> quizMap.put(quiz.getId(), quiz));
        return quizMap;
    }
}
