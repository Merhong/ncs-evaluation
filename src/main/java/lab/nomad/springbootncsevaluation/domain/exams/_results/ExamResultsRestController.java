package lab.nomad.springbootncsevaluation.domain.exams._results;


import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results.service.ExamResultsService;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/exam_results")

public class ExamResultsRestController {
    private  final ExamResultsService examResultsService;




}
