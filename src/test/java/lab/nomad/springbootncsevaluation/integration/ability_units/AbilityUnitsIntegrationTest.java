package lab.nomad.springbootncsevaluation.integration.ability_units;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
public class AbilityUnitsIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_test() throws Exception {
        // given
        String code = "test0123456";
        String name = "테스트 능력 단위";
        String purpose = "능력단위정의";
        int grade = 5;
        int totalTime = 60;
        List<String> examTypeList = List.of("MULTIPLE_CHOICE", "TASK");

        AbilityUnitSaveRequestDTO requestDTO = new AbilityUnitSaveRequestDTO();

        requestDTO.setCode(code);
        requestDTO.setName(name);
        requestDTO.setPurpose(purpose);
        requestDTO.setGrade(grade);
        requestDTO.setTotalTime(totalTime);
        requestDTO.setExamTypeList(examTypeList);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/ability-units")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.code").value(code));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.name").value(name));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.purpose").value(purpose));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.grade").value(grade));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.totalTime").value(totalTime));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.examTypeList[0]").value(examTypeList.get(0)));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.examTypeList[1]").value(examTypeList.get(1)));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_fail_test_1() throws Exception {
        // given
        String code = "test0123456";
        String name = "테스트 능력 단위";
        String purpose = "능력단위정의";
        int grade = 5;
        int totalTime = 60;
        List<String> examTypeList = List.of("MULTIPLE_CHOICE", "TASK");

        AbilityUnitSaveRequestDTO requestDTO = new AbilityUnitSaveRequestDTO();

        requestDTO.setCode(code);
        requestDTO.setName(name);
        requestDTO.setPurpose(purpose);
        requestDTO.setGrade(grade);
        requestDTO.setTotalTime(totalTime);
        requestDTO.setExamTypeList(examTypeList);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/ability-units")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.success").value("false"));
        resultActions.andExpect(jsonPath("$.response").doesNotExist());
        resultActions.andExpect(jsonPath("$.error.message").value(ExceptionMessage.COMMON_FORBIDDEN.getMessage()));
    }

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_test() throws Exception {
        // given
        int page = 0;
        int size = 10;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/ability-units?page="+page+"&size="+size)
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
    public void page_search_test() throws Exception {
        // given
        int page = 0;
        int size = 10;
        String searchValue = "애플리케이션";

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/ability-units?page="+page+"&size="+size+"&searchValue="+searchValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.response.pageable.pageNumber").value(page));
        resultActions.andExpect(jsonPath("$.response.pageable.pageSize").value(size));
        resultActions.andExpect(jsonPath("$.response.pageable.numberOfElements").value(4));
        resultActions.andExpect(jsonPath("$.response.pageable.empty").value(false));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void one_test() throws Exception {
        // given
        long id = 1L;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/ability-units/"+id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value(true));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }
}
