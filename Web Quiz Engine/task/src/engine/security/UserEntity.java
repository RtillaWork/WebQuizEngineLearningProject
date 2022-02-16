package engine.security;

import engine.businesslayer.PlayerQuiz;
import engine.businesslayer.Quiz;
import engine.security.RegisteredUserAuthorities;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Transactional
public class UserEntity implements UserDetails {
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

    private String roles = "REGISTERED_USER";

    @OneToMany(mappedBy = "quizAuthor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Quiz> quizzes;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerQuiz> playerQuizzes;

    public UserEntity() {
        this.username = "";
        this.quizzes = Set.of();
    }

    public UserEntity(String email, String password) {
        this.email = email;
        this.username = email;
        this.password = password;
    }

    public UserEntity(String email, String password, String roles) {
        this.email = email;
        this.username = email;
        this.password = password;
        this.roles = roles;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }


    public List<PlayerQuiz> getPlayerQuizzes() {
        return playerQuizzes;
    }

    public void setPlayerQuizzes(List<PlayerQuiz> playerQuizzes) {
        this.playerQuizzes = playerQuizzes;
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new RegisteredUserAuthorities(this));
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", roles='" + roles + '\'' +
//                ", quizzes=" + quizzes +
//                ", playerQuizzes=" + playerQuizzes +
                '}';
    }
}
