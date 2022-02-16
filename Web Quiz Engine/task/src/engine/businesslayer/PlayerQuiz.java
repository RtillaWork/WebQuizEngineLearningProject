package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.security.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class PlayerQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private UserEntity player;

    @ManyToOne
    private Quiz quiz;

    private boolean isCompleted = false;

    @ElementCollection
    private Set<Integer> answer; // = new HashSet<>(Collections.emptySet());

//    private LocalDateTime completedAt;

    @JsonProperty("completedAt")
    @UpdateTimestamp
    private LocalDateTime lastAttemptedAt;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime firstPlayedAt;


    public PlayerQuiz() {
    }

    public PlayerQuiz(Long id, UserEntity player, Quiz quiz, boolean isCompleted, Set<Integer> answer, LocalDateTime lastAttemptedAt) {
        this.player = player;
        this.quiz = quiz;
        this.isCompleted = isCompleted;
        this.answer = answer;
        this.lastAttemptedAt = lastAttemptedAt;
    }

    public PlayerQuiz(Quiz quiz, UserEntity player, boolean success, LocalDateTime attemptedAt) {
        this.quiz = quiz;
        this.player = player;
        this.isCompleted = success;
        this.lastAttemptedAt = attemptedAt;
    }

    public PlayerQuiz(Quiz quiz, UserEntity player, boolean success) {
        this.quiz = quiz;
        this.player = player;
        this.isCompleted = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getPlayer() {
        return player;
    }

    public void setPlayer(UserEntity player) {
        this.player = player;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getLastAttemptedAt() {
        return lastAttemptedAt;
    }

    public void setLastAttemptedAt(LocalDateTime lastAttemptedAt) {
        this.lastAttemptedAt = lastAttemptedAt;
    }

    public LocalDateTime getFirstPlayedAt() {
        return firstPlayedAt;
    }

    public void setFirstPlayedAt(LocalDateTime firstPlayedAt) {
        this.firstPlayedAt = firstPlayedAt;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
}
