package lab.nomad.springbootncsevaluation.domain.students;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.students.dto.*;
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
@Slf4j
public class StudentsRestController {
    private final StudentsService studentsService;


    // 과정,한명학생조회
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {
        //getStudentById 메서드호출하고 DTO객체에 저장
        StudentsOneResponseDTO responseDTO = studentsService.one(id);

        //조회된 결과를 ResponseEntity로반환
        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }


    // 과정,전체학생조회
    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable, @RequestParam(required = false) String searchValue) {

        // 권한 체크
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));

        //전체 학생 조회 메서드 호출
        Page<StudentsPageResponseDTO> responseDTO = studentsService.page(searchValue, pageable);

        // 조회된 결과를 ResponseEntity로 변환
        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    //학생수정
    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @PathVariable Long id,
                                     @RequestBody StudentsSaveRequestDTO requestDTO){

        StudentsUpdateResponseDTO responseDTO = studentsService.update(id, customUserDetails.user(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));

    }

    //학생삭제
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @PathVariable Long id){
        StudentsDeleteResponseDTO responseDTO = studentsService.delete(id,customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));

    }
}


