package lab.nomad.springbootncsevaluation.domain.courses;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    /* 과정 학생 리스트 페이지 */
    // TODO : @RequestParam 말고 @PathVariable 사용해서 courseId 받아서 구현!!!
    // TODO : 구현완료하고 StudentsController의 list 메서드 없애기
    @GetMapping("/{courseId}/students")
    public String listForm(@RequestParam(required = false, defaultValue = "1") int page, Model model,
            @AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long courseId) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 페이지당 학생 수
        int pageSize = 10; // 페이지당 학생 수를 5로 설정합니다.

        // 전체 학생 수 조회
        long totalStudents = studentsRepository.count();

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        // 현재 페이지에서 가져올 학생 목록 조회
        List<Students> students = studentsRepository.findAll(PageRequest.of(page - 1, pageSize))
                .getContent();

        // 모델에 필요한 데이터 추가
        model.addAttribute("students", students);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "students/listForm";
    }

    /* 과정 학생 상세보기 페이지 */
    // TODO : @PathVariable 사용해서 courseId, studentId 받아서 구현!!!
    // TODO : 구현완료하고 StudentsController의 detailForm 메서드 없애기
    @GetMapping("/{courseId}/students/{studentId}")
    public  String detailForm(@PathVariable Long courseId, @PathVariable Long studentId, Model model){
        // 현재 페이지에서 가져올 학생 목록 조회
        Optional<Students> students = studentsRepository.findById(studentId);

        model.addAttribute("students", students.orElse(null)); // Optional이 비어있을 경우 null을 넘겨줌


        return  "students/detailForm";
    }


    /* 과정 학생 수정 페이지 */
    // TODO : @RequestParam 말고 @PathVariable 사용해서 courseId 받아서 구현!!!
    // TODO : 구현완료하고 StudentsController의 update 메서드 없애기
    @GetMapping("/{courseId}/students/updateForm")
    public String update(Model model){
        // 모든 학생 데이터 가져오기
        List<Students> students = studentsRepository.findAll();
        // 코스 데이터 가져오기
        List<Courses> courses = coursesRepository.findAll();

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "students/update";
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
    public String save(@RequestParam(required = false) Long courseId, Model model,
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
        CoursesOneResponseDTO coursesOneResponseDTO = coursesService.one(id, customUserDetails.user());


        model.addAttribute("coursesOneResponseDTO", coursesOneResponseDTO);


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
