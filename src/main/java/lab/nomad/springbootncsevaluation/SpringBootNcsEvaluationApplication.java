package lab.nomad.springbootncsevaluation;

import lab.nomad.springbootncsevaluation._core.dev.InitData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootNcsEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNcsEvaluationApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner init(DataSource dataSource, InitData initData) {

        return (args -> {

            // User 데이터 생성
            initData.initUser();

            // 능력 단위(AbilityUnit) 데이터 생성
            initData.initAbilityUnit();

            initData.initAbilityUnitElement();

            initData.initAbilityUnitElementItem();

            // Course 데이터 생성
            initData.initCourse();

            // Student 데이터 생성
            initData.initStudent();

            // // 선다형 시험지(ExamPapers) 데이터 생성
            // initData.initExamPaper();

            // // 시험지 문제(ExamPaperMultipleQuestions) 데이터 생성
            // initData.initExamPaperMultipleQuestion();

            // // 시험지 문제 답안(ExamPaperMultipleQuestionAnswers) 데이터 생성
            // initData.initExamPaperMultipleQuestionAnswer();


            // 원래 실행 순서 : data.sql -> CommandLineRunner 순으로 데이터 초기화
            // CommandLineRunner -> data.sql 순서로 데이터 초기화 시키기 위한 코드
            Resource resource = new ClassPathResource("db/data.sql");
            try {
                ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }); // return
    }
}
