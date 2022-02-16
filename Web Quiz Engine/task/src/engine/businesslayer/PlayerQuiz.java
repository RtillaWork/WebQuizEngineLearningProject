package engine.businesslayer;

import engine.security.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PlayerQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean isCompleted = false;

    @UpdateTimestamp
    private LocalDateTime lastPlayed;

    @CreationTimestamp
    private LocalDateTime lastCompleted;

    @ManyToOne
    private UserEntity player;

    @ManyToOne
    private Quiz quiz;


}
