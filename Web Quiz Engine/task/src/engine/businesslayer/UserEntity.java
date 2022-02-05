package engine.businesslayer;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Transactional
public class UserEntity {
    final static int MINIMUM_PASSWORD_LENGTH = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Email
    @Pattern(regexp = ".+@.+\\..+")
//    @Column(unique = true)
    private String email;

    @Email
//    @Pattern(regexp=".+@.+\\..+")
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = MINIMUM_PASSWORD_LENGTH)
//    @Transient
    private String password;

    private String role;

    @OneToMany(mappedBy = "quizAuthor")
    private Set<Quiz> quizzes;

    public UserEntity() {
        this.username = "";
        this.quizzes = Set.of();
    }

    public UserEntity(String email, String password) {
        this.email = email;
        this.username = email;
        this.password = password;
    }

    public UserEntity(String email, String password, String role) {
        this.email = email;
        this.username = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String email, String password, Set<Quiz> quizzes) {
        this.email = email;
        this.username = email;
        this.password = password;
        this.quizzes = quizzes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
