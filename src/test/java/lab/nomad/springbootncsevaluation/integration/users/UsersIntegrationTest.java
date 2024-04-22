package lab.nomad.springbootncsevaluation.integration.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
public class UsersIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_test() throws Exception {
        // given
        int page = 0;
        int size = 2;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/users?page="+page+"&size="+size)
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
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_search_test_1() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String searchValue = "ssar";

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/users?page="+page+"&size="+size+"&searchValue="+searchValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.response.pageable.pageNumber").value(page));
        resultActions.andExpect(jsonPath("$.response.pageable.pageSize").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.numberOfElements").value(1));
        resultActions.andExpect(jsonPath("$.response.pageable.empty").value(false));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_search_test_2() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String role = "ROLE_TEACHER";

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/users?page="+page+"&size="+size+"&role="+role)
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
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_search_test_3() throws Exception {
        // given
        int page = 0;
        int size = 2;
        String searchValue = "ssar";
        String role = "ROLE_TEACHER";

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/users?page="+page+"&size="+size+"&searchValue="+searchValue+"&role="+role)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.response.pageable.pageNumber").value(page));
        resultActions.andExpect(jsonPath("$.response.pageable.pageSize").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.numberOfElements").value(1));
        resultActions.andExpect(jsonPath("$.response.pageable.empty").value(false));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }
}
