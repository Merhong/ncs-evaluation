package lab.nomad.springbootncsevaluation.domain.exams;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.service.ExamsService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //시험조회
    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable,String searchValue){

        // 권한 체크
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));

        Page<ExamsPageResponseDTO> responseDTO = examsService.page(searchValue,pageable);

        return  ResponseEntity.ok(APIUtils.success(responseDTO));

    }
    //시험 상세조회
    @GetMapping("/{id}")
    public  ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails,@PathVariable Long id){

        // 권한 체크
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));


        ExamsOneResponseDTO responseDTO = examsService.one(id);

        return  ResponseEntity.ok(APIUtils.success(responseDTO));
 }
}
