package engine.presentationlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.businesslayer.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class QuizMarshalling {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private long id;
    private Quiz quiz;

    public QuizMarshalling(Quiz quiz, long id) {
        this.quiz = quiz;
        this.id = id;
    }

    public static String toJson(Quiz quiz, long id) throws JsonProcessingException {
        QuizMarshalling quizJsonMarshalling = new QuizMarshalling(quiz, id);
        return objectMapper.writeValueAsString(quizJsonMarshalling);
    }

    public static String toJson(Map<Long, Quiz> quizMap) throws JsonProcessingException {
        List<QuizMarshalling> quizJsonList = quizMap
                .keySet().stream().map(i -> new QuizMarshalling(quizMap.get(i), i)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(quizJsonList);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return quiz.getTitle();
    }

    public String getText() {
        return quiz.getText();
    }

    public String[] getOptions() {
        return quiz.getOptions();
    }

//    @JsonIgnore
    public Set<Integer> getAnswer() {
        return quiz.getAnswer();
    }

    //    @JsonIgnore
    public Long getQuizAuthorId() {
        return quiz.getQuizAuthor().getId();
    }

//    public static String toJson(List<Quiz> quizMap) throws JsonProcessingException {
//        List<QuizMarshalling> quizJsonList = quizMap
//                .keySet().stream().map(i -> new QuizMarshalling(quizMap.get(i), i)).collect(Collectors.toList());
//        return objectMapper.writeValueAsString(quizJsonList);
//    }
}

