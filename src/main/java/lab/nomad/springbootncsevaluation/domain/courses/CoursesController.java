package lab.nomad.springbootncsevaluation.domain.courses;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {

    // DI
    private final CoursesService coursesService;
    private final CoursesRepository coursesRepository;
    private final AbilityUnitsService abilityUnitsService;
    private final StudentsService studentsService;
    private final StudentsRepository studentsRepository;

    /**
     * 학생(students)
     */

    /* 과정 학생 목록 페이지 */
    @GetMapping("/{courseId}/students")
    public String listForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
                           @PathVariable Long courseId) {

        try {
            // 사용자가 인증되지 않은 경우 처리
            if (customUserDetails == null) {
                System.out.println("사용자가 인증되지 않았습니다.");
                return "redirect:/login";
            }

            // 서비스 호출
            StudentsPageResponseDTO responseDTO = studentsService.page(courseId, pageable, searchValue, customUserDetails.user());

            // 모델에 필요한 데이터 추가
            model.addAttribute("studentsPage", responseDTO);
            model.addAttribute("pageable", responseDTO.getPageable());

            return "students/listForm";
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            e.printStackTrace();
            model.addAttribute("errorMessage", "학생 목록을 불러오는 중 오류가 발생했습니다.");
            return "error";
        }
    }

    /* 과정 학생 상세보기 페이지 */
    @GetMapping("/{courseId}/students/{id}")
    public String detailForm(@PathVariable Long courseId, @PathVariable Long id, Model model,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        StudentsOneResponseDTO responseDTO = studentsService.one(courseId, id);

        // 학생 정보가 존재하지 않을 경우 처리
        if (responseDTO == null || responseDTO.getStudent() == null) {
            System.out.println("학생 정보를 찾을 수 없습니다.");
            return "redirect:/courses/1/students"; // 학생 목록 페이지로 리디렉션
        }

        // 상세보기 응답을 모델에 담기
        model.addAttribute("student", responseDTO.getStudent());
        model.addAttribute("courseId", courseId);

        return "students/detailForm";
    }


    /* 과정 학생 수정 페이지 */
    @GetMapping("/{courseId}/students/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
            @PathVariable Long courseId) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        StudentsPageResponseDTO responseDTO = studentsService.page(courseId, pageable, searchValue,
                customUserDetails.user());

        // 모델에 필요한 데이터 추가
        model.addAttribute("students", responseDTO.getStudent());
        model.addAttribute("pageable", responseDTO.getPageable());
        model.addAttribute("courseId", courseId);

        // 수정시 수강 상태 state 비교하기 위해 사용
        model.addAttribute("ACTIVE", StudentStatus.ACTIVE);
        model.addAttribute("DROP", StudentStatus.DROP);

        // 수정을 위한 빈 DTO 모델에 추가
        model.addAttribute("StudentUpdateRequestDTO", new StudentsUpdateRequestDTO());

        return "students/updateForm";
    }


    /* 과정 학생 등록 페이지 */
    @GetMapping("/{courseId}/students/saveForm")
    public String saveStudentForm(@PathVariable Long courseId, Model model,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 사용자 인증 정보 및 코스 ID 모델에 추가
        model.addAttribute("courseId", courseId);
        model.addAttribute("StudentsSaveRequestDTO", new StudentsSaveRequestDTO());

        // 코스 데이터가져오기
        Optional<Courses> course = coursesRepository.findById(courseId);
        model.addAttribute("course", course);

        return "students/saveForm";
    }


    /**
     * 과정(courses)
     */

    /* 과정 상세보기 페이지 */
    @GetMapping("/{id}")
    public String detailForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        // 과정 + 능력 단위
        CoursesOneResponseDTO coursesOneResponseDTO = coursesService.one(id, customUserDetails.user());

        // 상세보기 응답을 모델에 담기
        model.addAttribute("CoursesOneResponseDTO", coursesOneResponseDTO);

        // 과정 수정을 위한 빈 DTO 모델에 담기
        model.addAttribute("CoursesUpdateRequestDTO", new CoursesUpdateRequestDTO());

        return "courses/detailForm";
    }


    /* 과정 등록 페이지 */
    @GetMapping("/saveForm")
    public String saveForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) String abilityUnitSearchValue) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        CoursesPageResponseDTO coursesPageResponseDTO = coursesService.page(pageable, searchValue,
                customUserDetails.user());

        AbilityUnitPageResponseDTO abilityUnitPageResponseDTO = abilityUnitsService.page(pageable,
                abilityUnitSearchValue);


        // 기존 과정을 불러오기 위해 모델에 담아준 항목
        model.addAttribute("username", customUserDetails.getUsername());
        model.addAttribute("coursesPage", coursesPageResponseDTO);
        model.addAttribute("pageable", coursesPageResponseDTO.getPageable());
        model.addAttribute("abilityUnitPage", abilityUnitPageResponseDTO);
        model.addAttribute("abilityUnitPageable", abilityUnitPageResponseDTO.getPageable());


        model.addAttribute("CoursesSaveRequestDTO", new CoursesSaveRequestDTO());

        return "courses/saveForm";
    }

    /* 과정 목록 페이지 */
    @GetMapping
    public String listForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        CoursesPageResponseDTO coursesPageResponseDTO = coursesService.page(pageable, searchValue,
                customUserDetails.user());

        model.addAttribute("username", customUserDetails.getUsername());
        model.addAttribute("coursesPage", coursesPageResponseDTO);
        model.addAttribute("pageable", coursesPageResponseDTO.getPageable());

        return "courses/listForm";
    }
}
