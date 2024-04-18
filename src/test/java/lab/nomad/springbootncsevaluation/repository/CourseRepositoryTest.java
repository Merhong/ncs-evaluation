package lab.nomad.springbootncsevaluation.repository;

import lab.nomad.springbootncsevaluation._core.dev.InitData;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

@DataJpaTest
@Import(InitData.class)
public class CourseRepositoryTest {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void findById_test() {
        // given
        var id = 2L;
        var user = usersRepository.findByUsername("ssar");

        // when
        var result = coursesRepository.findByIdAndUserId(id, user.get().getId());

        // then
        System.out.println("디버그 : " + result.get().getName());

    }


}
