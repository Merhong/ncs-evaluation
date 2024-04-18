package lab.nomad.springbootncsevaluation.integration.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
public class CoursesIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_test() throws Exception {
        String name = "testCourseNo1";
        String academyName = "testAcademyNo1";

        CoursesSaveRequestDTO requestDTO = new CoursesSaveRequestDTO();

        requestDTO.setName(name);
        requestDTO.setAcademyName(academyName);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.courses.name").value(name));
        resultActions.andExpect(jsonPath("$.response.courses.academyName").value(academyName));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_fail_test_1() throws Exception {
        String name = "";
        String academyName = "testAcademyNo1";

        CoursesSaveRequestDTO requestDTO = new CoursesSaveRequestDTO();

        requestDTO.setName(name);
        requestDTO.setAcademyName(academyName);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/courses")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.success").value("false"));
        resultActions.andExpect(jsonPath("$.response").doesNotExist());
        resultActions.andExpect(jsonPath("$.error.message").value(ValidExceptionMessage.Message.EMPTY_COURSE_NAME));
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_test() throws Exception {
        // given
        int page = 0;
        int size = 2;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/courses?page="+page+"&size="+size)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.response.pageable.pageNumber").value(page));
        resultActions.andExpect(jsonPath("$.response.pageable.pageSize").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.numberOfElements").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.empty").value(false));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_search_test() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String searchValue = "B";

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/courses?page="+page+"&size="+size+"&searchValue="+searchValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.response.pageable.pageNumber").value(page));
        resultActions.andExpect(jsonPath("$.response.pageable.pageSize").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.numberOfElements").value(2));
        resultActions.andExpect(jsonPath("$.response.pageable.empty").value(false));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void one_test() throws Exception {
        // given
        long id = 2L;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/courses/"+id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void one_fail_test_1() throws Exception {
        // given
        long id = 1L;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/courses/"+id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.success").value("false"));
        resultActions.andExpect(jsonPath("$.response").doesNotExist());
        resultActions.andExpect(jsonPath("$.error.message").value(ExceptionMessage.NOT_FOUND_COURSE.getMessage()));

    }
}
