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
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
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

import java.util.List;

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

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        StudentsPageResponseDTO responseDTO = studentsService.page(courseId, pageable, searchValue,
                customUserDetails.user());

        // 모델에 필요한 데이터 추가
        model.addAttribute("studentsPage", responseDTO);
        model.addAttribute("pageable", responseDTO.getPageable());

        return "students/listForm";
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
        StudentsOneResponseDTO studentsOneResponseDTO = studentsService.one(id);

        // 학생 정보가 존재하지 않을 경우 처리
        if (studentsOneResponseDTO == null || studentsOneResponseDTO.getStudent() == null) {
            System.out.println("학생 정보를 찾을 수 없습니다.");
            return "redirect:/courses/1/students"; // 학생 목록 페이지로 리디렉션
        }

        // 상세보기 응답을 모델에 담기
        model.addAttribute("student", studentsOneResponseDTO.getStudent());
        model.addAttribute("courseId", courseId);

        return "students/detailForm";
    }


    /* 과정 학생 수정 페이지 */
    @GetMapping("/{courseId}/students/{studentId}/updateForm")
    public String updateForm(@PathVariable Long courseId, @PathVariable Long studentId, Model model,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // 모든 학생 데이터 가져오기
        List<Students> students = studentsRepository.findAll();
        // 코스 데이터 가져오기
        List<Courses> courses = coursesRepository.findAll();

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "students/updateForm";
    }


    /* 과정 학생 등록 페이지 */
    // TODO : @RequestParam 말고 @PathVariable 사용해서 courseId 받아서 구현!!!
    // TODO : 구현완료하고 아래 @GetMapping("/students/saveForm") save 메소드 없애기.
    // TODO : 구현하지 않고 @GetMapping("/students/saveForm") save 지우면 페이지 오류남!!!
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
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("courses", courses);

        return "students/saveForm";
    }

    // TODO : 위의 saveStudentForm 구현하고 없애기!!!
    @GetMapping("/students/saveForm")
    public String saveForm(@RequestParam(required = false) Long courseId, Model model,
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
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("courses", courses);

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
