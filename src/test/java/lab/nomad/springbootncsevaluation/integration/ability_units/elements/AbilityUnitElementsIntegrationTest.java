package lab.nomad.springbootncsevaluation.integration.ability_units.elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveRequestDTO;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
public class AbilityUnitElementsIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_test() throws Exception {
        // given
        int id = 1;
        List<AbilityUnitElementSaveRequestDTO> requestDTO = new ArrayList<>();

        String code = "test1";
        String name = "테스트 능력 단위 요소1";
        List<String> itemContents = List.of("테스트 능력 단위 요소1-1", "테스트 능력 단위 요소1-2");

        AbilityUnitElementSaveRequestDTO abilityUnitSaveRequestDTO1 = new AbilityUnitElementSaveRequestDTO();

        abilityUnitSaveRequestDTO1.setCode(code);
        abilityUnitSaveRequestDTO1.setName(name);
        abilityUnitSaveRequestDTO1.setItemContents(itemContents);

        requestDTO.add(abilityUnitSaveRequestDTO1);

        code = "test2";
        name = "테스트 능력 단위 요소1";
        itemContents = List.of("테스트 능력 단위 요소2-1", "테스트 능력 단위 요소2-2");

        AbilityUnitElementSaveRequestDTO abilityUnitSaveRequestDTO2 = new AbilityUnitElementSaveRequestDTO();

        abilityUnitSaveRequestDTO2.setCode(code);
        abilityUnitSaveRequestDTO2.setName(name);
        abilityUnitSaveRequestDTO2.setItemContents(itemContents);

        requestDTO.add(abilityUnitSaveRequestDTO2);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/ability-units/"+id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.id").value(id));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[0].code")
                .value(requestDTO.get(0).getCode()));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[1].code")
                .value(requestDTO.get(1).getCode()));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[0].name")
                .value(requestDTO.get(0).getName()));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[1].name")
                .value(requestDTO.get(1).getName()));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[0].abilityUnitElementItemList[0].content")
                .value(requestDTO.get(0).getItemContents().get(0)));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[0].abilityUnitElementItemList[1].content")
                .value(requestDTO.get(0).getItemContents().get(1)));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[1].abilityUnitElementItemList[0].content")
                .value(requestDTO.get(1).getItemContents().get(0)));
        resultActions.andExpect(jsonPath("$.response.abilityUnit.abilityUnitElementList[1].abilityUnitElementItemList[1].content")
                .value(requestDTO.get(1).getItemContents().get(1)));
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
}
