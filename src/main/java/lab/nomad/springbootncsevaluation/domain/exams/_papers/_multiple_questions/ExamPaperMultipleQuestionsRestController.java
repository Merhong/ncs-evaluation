package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto.ExamPaperMultipleQuestionsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto.ExamPaperMultipleQuestionsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.service.ExamPaperMultipleQuestionsService;
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
@RequestMapping("/api/v1/exam-paper")
@Slf4j
public class ExamPaperMultipleQuestionsRestController {

    // DI
    private final ExamPaperMultipleQuestionsService questionsService;

    // 시험지 문제 등록
    @PostMapping("/{examPaperId}/question")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @PathVariable Long examPaperId,
                                  @RequestBody @Valid ExamPaperMultipleQuestionsSaveRequestDTO requestDTO,
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

        // 시험지 문제 등록
        ExamPaperMultipleQuestionsSaveResponseDTO responseDTO = questionsService.save(customUserDetails.user(),
                examPaperId, requestDTO);


        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
