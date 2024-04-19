package lab.nomad.springbootncsevaluation.integration.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
public class StudentsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;


    @Test
    @WithUserDetails(value = "admin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void save_test() throws Exception{

        //given
        Long courseId = 1L;
        String tel = "010-1111-2222";
        String name = "ssar";
        StudentStatus studentStatus = StudentStatus.ACTIVE;

        StudentsSaveRequestDTO requestDTO = new StudentsSaveRequestDTO();

       // Courses courses = Courses.builder()
               // .id(requestDTO.getCourseId())
               // .build(); // Courses 객체를 반환하는 빌더 사용
       // requestDTO.setCourseId(courses.getId()); // courseId를 설정
       // requestDTO.setCourse(courses); // 생성된 Courses 객체를 StudentsSaveRequestDTO에 설정
        requestDTO.setTel(tel);
        requestDTO.setName(name);
        requestDTO.setStatus(studentStatus); // 학생 상태 설정
        //requestDTO.setStatus(StudentStatus.ACTIVE);

        String requestBody = om.writeValueAsString(requestDTO);


        //when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/courses/"+ courseId +"/students")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        //then

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.students.tel").value(tel));
        resultActions.andExpect(jsonPath("$.response.students.name").value(name));
//        resultActions.andExpect(jsonPath("$.response.students.status").value());

    }
}
