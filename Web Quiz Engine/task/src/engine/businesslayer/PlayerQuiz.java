package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne //(fetch = FetchType.EAGER)
    private UserEntity player;

    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @ManyToOne //(fetch = FetchType.EAGER)
    private Quiz playedQuiz;

    @JsonIgnore
    @ElementCollection
    private Set<Integer> answer; // = new HashSet<>(Collections.emptySet());

    @JsonIgnore
    private boolean completed = false;

//    private LocalDateTime completedAt;

    @JsonProperty("completedAt")
//    @UpdateTimestamp
    private LocalDateTime lastAttemptedAt;

//    @JsonIgnore
//    @CreationTimestamp
//    private LocalDateTime firstPlayedAt;


    public PlayerQuiz() {
    }

    public PlayerQuiz(UserEntity player, Quiz playedQuiz, Set<Integer> answer, boolean completed, LocalDateTime lastAttemptedAt) {
        this.player = player;
        this.playedQuiz = playedQuiz;
        this.completed = completed;
        this.answer = answer;
        this.lastAttemptedAt = lastAttemptedAt;
    }

    public PlayerQuiz(UserEntity player, Quiz playedQuiz, Set<Integer> answer, boolean completed) {
        this.player = player;
        this.playedQuiz = playedQuiz;
        this.answer = answer;
        this.completed = completed;
        this.lastAttemptedAt = LocalDateTime.now();
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

    public Quiz getPlayedQuiz() {
        return playedQuiz;
    }

    public void setPlayedQuiz(Quiz playedQuiz) {
        this.playedQuiz = playedQuiz;
    }

    public boolean completed() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getLastAttemptedAt() {
        return lastAttemptedAt;
    }

    public void setLastAttemptedAt(LocalDateTime lastAttemptedAt) {
        this.lastAttemptedAt = lastAttemptedAt;
    }

//    public LocalDateTime getFirstPlayedAt() {
//        return firstPlayedAt;
//    }
//
//    public void setFirstPlayedAt(LocalDateTime firstPlayedAt) {
//        this.firstPlayedAt = firstPlayedAt;
//    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    public Long getPlayedQuizId() {
//        return this.playedQuiz.getId();
        return getPlayedQuiz().getId();
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "PlayerQuiz{" +
                "id=" + id +
                ", player=" + player +
                ", playedQuiz=" + playedQuiz +
                ", answer=" + answer +
                ", completed=" + completed +
                ", lastAttemptedAt=" + lastAttemptedAt +
                '}';
    }
}
