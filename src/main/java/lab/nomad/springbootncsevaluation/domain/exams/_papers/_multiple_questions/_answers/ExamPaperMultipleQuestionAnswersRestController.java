package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.dto.ExamPaperMultipleQuestionAnswersSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.dto.ExamPaperMultipleQuestionAnswersSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.service.ExamPaperMultipleQuestionAnswersService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam-paper/question")
@Slf4j
public class ExamPaperMultipleQuestionAnswersRestController {

    // DI
    private final ExamPaperMultipleQuestionAnswersService answersService;

    // 시험지 문제 답안 등록
    @PostMapping("/{questionId}/answer")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @PathVariable Long questionId,
                                  @RequestBody @Valid ExamPaperMultipleQuestionAnswersSaveRequestDTO requestDTO,
                                  Errors errors) {

        // 권한 체크
        // 관리자, 강사 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        // 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
        }

        // 시험지 문제 답안 등록
        ExamPaperMultipleQuestionAnswersSaveResponseDTO responseDTO = answersService.save(customUserDetails.user(),
                questionId, requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

}
