package engine;

import engine.persistencelayer.PlayerQuizService;
import engine.persistencelayer.QuizService;
import engine.persistencelayer.UserEntityRepositoryService;
import engine.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceAutoConfiguration;
import org.springframework.boot.actuate.endpoint.http.ActuatorMediaType;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class WebQuizEngine {

    @Autowired
    QuizService qs;

    @Autowired
    PlayerQuizService pqs;

    @Autowired
    UserEntityRepositoryService uers;

    public static void main(String[] args) {

        SpringApplication.run(WebQuizEngine.class, args);
    }

    public class MyDebugRunner implements CommandLineRunner {

        /**
         * Callback used to run the bean.
         *
         * @param args incoming main method arguments
         * @throws Exception on error
         */
        @Override
        public void run(String... args) throws Exception {

            System.out.println("STARTING COMMANDLINE RUNNERS");


        }
    }

}
