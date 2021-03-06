package engine.presentationlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.businesslayer.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMarshalling {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private long id;
    private User user;

    public UserMarshalling(User user, long id) {
        this.user = user;
        this.id = id;
    }

    public static String toJson(User user, long id) throws JsonProcessingException {
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

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getEmail() {
        return user.getEmail();
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

//    public String getPassword() {
//        return password;
//    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUsername() {
        return user.getUsername();
    }

}
