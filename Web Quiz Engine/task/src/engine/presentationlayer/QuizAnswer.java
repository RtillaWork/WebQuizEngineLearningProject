package engine.presentationlayer;

import javax.persistence.Entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Entity
public class QuizAnswer {
    private Set<Integer> answer;

    public QuizAnswer() {
        this.answer = new HashSet<>(Collections.emptySet());
    }

    public QuizAnswer(Set<Integer> answer) {
        this.answer = (answer == null ? new HashSet<>(Collections.emptySet()) : answer);
    }

    public Set<Integer> getAnswer() {
        return this.answer;
    }
}
