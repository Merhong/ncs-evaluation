package lab.nomad.springbootncsevaluation.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.auth.dto.JoinRequestDTO;
import lab.nomad.springbootncsevaluation.domain.auth.dto.LoginRequestDTO;
import lab.nomad.springbootncsevaluation.domain.auth.dto.ReLoginRequestDTO;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class AuthAPIIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void join_test() throws Exception {
        // given
        String name = "교강용";
        String tel = "010-1111-2222";
        String username = "testTeacher";
        String password = "test1234";
        String email = "testTeacher@google.com";
        String requestRole = "ROLE_TEACHER";

        JoinRequestDTO requestDTO = new JoinRequestDTO();
        requestDTO.setName(name);
        requestDTO.setTel(tel);
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);
        requestDTO.setEmail(email);
        requestDTO.setRole(requestRole);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/auth/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        var requestRoleText = UserRole.valueOf(requestRole).getText();

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.user.username").value(username));
        resultActions.andExpect(jsonPath("$.response.user.email").value(email));
        resultActions.andExpect(jsonPath("$.response.user.tel").value(tel));
        resultActions.andExpect(jsonPath("$.response.user.role").value(requestRoleText));
        resultActions.andExpect(jsonPath("$.response.user.name").value(name));
    }

    @Test
    public void join_fail_test_1() throws Exception {
        // given
        String name = "교강용";
        String tel = "010-1111-2222";
        String username = "tes";
        String password = "test1234";
        String email = "testTeacher@google.com";
        String requestRole = "ROLE_TEACHER";

        JoinRequestDTO requestDTO = new JoinRequestDTO();
        requestDTO.setName(name);
        requestDTO.setTel(tel);
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);
        requestDTO.setEmail(email);
        requestDTO.setRole(requestRole);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/auth/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.success").value("false"));
        resultActions.andExpect(jsonPath("$.response").doesNotExist());
        resultActions.andExpect(jsonPath("$.error.message").value(ValidExceptionMessage.Message.INVALID_USERNAME));
    }

    @Test
    public void login_test() throws Exception {
        // given
        String username = "ssar";
        String password = "test1234";

        LoginRequestDTO requestDTO = new LoginRequestDTO();
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/auth/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.user.username").value(username));
    }

    @Test
    public void login_fail_test_1() throws Exception {
        // given
        String username = "ssar1";
        String password = "test1234";

        LoginRequestDTO requestDTO = new LoginRequestDTO();
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/auth/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then

        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.success").value("false"));
        resultActions.andExpect(jsonPath("$.response").doesNotExist());
        resultActions.andExpect(jsonPath("$.error.message").value(ExceptionMessage.LOGIN_FAIL.getMessage()));
    }

    @Test
    public void relogin_test() throws Exception {
        // given
        String refreshToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwidG9rZW4tdHlwZSI6IlJ" +
                "FRlJFU0hfVE9LRU4iLCJyb2xlIjoiUk9MRV9URUFDSEVSIiwiZXhwIjoxNzEwNzI4Njg2LCJ1c2VybmFtZSI6InNzYXIi" +
                "fQ.suG0xRBOVIV4i9ewkzkL9lUnjLnI1Lbk5EWD53ghBh9rm42UwgDULsEPh05C2v6DYm-3boK3WTkzSqmcUyB1SA";

        ReLoginRequestDTO requestDTO = new ReLoginRequestDTO();
        requestDTO.setRefreshToken(refreshToken);

        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/api/v1/auth/re-login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then

        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.success").value("true"));
    }
}
