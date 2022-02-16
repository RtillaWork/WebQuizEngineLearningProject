package engine.persistencelayer;

import engine.businesslayer.PlayerQuiz;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class PlayerQuizService {

    @Autowired
    PlayerQuizRepository pqr;

    Page<PlayerQuiz> findByPlayerAndCompleted(UserEntity player) {
        return null;
    }
}
