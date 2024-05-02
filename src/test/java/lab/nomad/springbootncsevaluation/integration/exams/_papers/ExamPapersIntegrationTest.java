package lab.nomad.springbootncsevaluation.integration.exams._papers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveRequestDTO;
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
public class ExamPapersIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_test() throws Exception {
        // given
        String name = "testExamPaperNo1";
        String examType = "MULTIPLE_CHOICE";

        ExamPaperSaveRequestDTO requestDTO = new ExamPaperSaveRequestDTO();

        requestDTO.setName(name);
        requestDTO.setExamType(examType);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(post("/api/v1/ability-units/2/exam-paper").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.examPaper.name").value(name));
        resultActions.andExpect(jsonPath("$.response.examPaper.examType").value(examType));
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void page_test() throws Exception {
        // given
        int page = 0;
        int size = 5;

        // when
        ResultActions resultActions = mvc.perform(
                get("/api/v1/exam-paper?page="+page+"&size="+size)
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
}
