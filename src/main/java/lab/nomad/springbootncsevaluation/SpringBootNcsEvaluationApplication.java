package lab.nomad.springbootncsevaluation;

import lab.nomad.springbootncsevaluation._core.dev.InitData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootNcsEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNcsEvaluationApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner init(InitData initData) {

        return (args -> {

            // User 데이터 생성
            initData.initUser();

        }); // return
    }
}
