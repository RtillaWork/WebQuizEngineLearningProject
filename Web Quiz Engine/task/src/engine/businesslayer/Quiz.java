package engine.businesslayer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection
    @OrderColumn
    private String[] options;

    @ElementCollection
    private Set<Integer> answer; // = new HashSet<>(Collections.emptySet());

    @ManyToOne
    @JoinColumn(name = "quiz_author_id", nullable = true)
    private UserEntity quizAuthor;

    public Quiz() {
        this.title = new String();
        this.text = new String();
        this.options = new String[]{};
        // Make sure answer is consistently a Set<> and never null even if missing from Controller
        this.answer = new HashSet<>(Collections.emptySet());
    }

    public Quiz(String title, String text, @NotNull @Size(min = 2) String[] options, Set<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = (answer == null ? new HashSet<>(Collections.emptySet()) : answer);
    }

    public Quiz(long id, String title, String text, @NotNull @Size(min = 2) String[] options, Set<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = (answer == null ? new HashSet<>(Collections.emptySet()) : answer);
    }

    public Quiz(String title, String text, @NotNull @Size(min = 2) String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new HashSet<>(Collections.emptySet());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    // @JsonIgnore
    public Set<Integer> getAnswer() {
        return this.answer;
    }

    // @JsonProperty
    public void setAnswer(Set<Integer> answer) {
        this.answer = (answer == null ? new HashSet<>(Collections.emptySet()) : answer);
    }

//    // @JsonProperty
//    public void setAnswer(Object answer) {
//        this.answer = (answer instanceof Set ? (Set<Integer> ) answer
//                : answer == null ?  new HashSet<>(Collections.emptySet())
//                : new HashSet<>(Collections.emptySet()));
//    }


    public UserEntity getQuizAuthor() {
        return quizAuthor;
    }

    public void setQuizAuthor(UserEntity quizAuthor) {
        this.quizAuthor = quizAuthor;
    }
}
