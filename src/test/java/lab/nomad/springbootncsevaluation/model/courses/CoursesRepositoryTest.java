package lab.nomad.springbootncsevaluation.model.courses;

import lab.nomad.springbootncsevaluation._core.dev.InitData;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@Import(InitData.class)
class CoursesRepositoryTest {
    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void findByNameContainsAndUserIdAndDeleteDateIsNull() {
        // given
        var userId = 2L;
        String searchValue = "B";
        Pageable pageable = PageRequest.of(0, 20);

        // when
        var result = coursesRepository.findByNameContainsAndUserIdAndDeleteDateIsNull(pageable, searchValue, userId);

        // then
        System.out.println("====searchValue: " + searchValue);
        System.out.println("====pageable: " + pageable);
    }

    @Test
    void findByUserIdAndDeleteDateIsNull() {
    }


    @Test
    public void findByIdAndUserIdAndDeleteDateIsNull_test() {
        // given
        var id = 2L;
        var user = usersRepository.findByUsername("ssar");

        // when
        var result = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.get().getId());

        // then
        System.out.println("디버그 : " + result.get().getName());

    }
}

