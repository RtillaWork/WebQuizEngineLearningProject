package engine.presentationlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.businesslayer.Quiz;
import engine.security.UserEntity;

import java.util.Set;

public class UserMarshalling {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private long id;
    private UserEntity user;

    public UserMarshalling(UserEntity user, long id) {
        this.user = user;
        this.id = id;
    }

    public static String toJson(UserEntity user, long id) throws JsonProcessingException {
        engine.presentationlayer.UserMarshalling userJsonMarshalling = new engine.presentationlayer
                .UserMarshalling(user, id);
        return objectMapper.writeValueAsString(userJsonMarshalling);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    public Set<Quiz> getQuizzes() {
        return user.getQuizzes();
    }
}
