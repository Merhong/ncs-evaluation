package lab.nomad.springbootncsevaluation.domain.exams;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.service.ExamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")

public class ExamsRestController {
    private  final ExamsService examsService;

    //시험생성
    @PostMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable Long id,@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   @RequestBody ExamsSaveRequestDTO requestDTO){

        ExamsSaveResponseDTO responseDTO = examsService.save(id,requestDTO);

        return  ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
