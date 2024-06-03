package lab.nomad.springbootncsevaluation.domain.courses;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CoursesController {

    // DI
    @Autowired
    private CoursesService coursesService;

    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping("/students/saveForm")
    public String save(@RequestParam(required = false) Long courseId, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 사용자 인증 정보 및 코스 ID 모델에 추가
        model.addAttribute("courseId", courseId);
        model.addAttribute("StudentsSaveRequestDTO", new StudentsSaveRequestDTO());

        //코스 데이터가져오기
        List<Courses> courses = coursesRepository.findAll();
        model.addAttribute("courses",courses);

        return "students/saveForm";
    }
    @GetMapping("/saveForm")
    public String saveForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        model.addAttribute("CoursesSaveRequestDTO", new CoursesSaveRequestDTO());

        return "courses/saveForm";
    }

    @GetMapping
    public String manageForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
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

        return "courses/manageForm";
    }

    // @GetMapping("/")
    // public String manage(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
    //
    //     System.out.println("JWT 토큰 들고옴 !!! " + customUserDetails.getUsername());
    //
    //     List<Courses> courses = coursesRepository.findAll();
    //
    //     model.addAttribute("username", customUserDetails.getUsername());
    //     model.addAttribute("courses", courses);
    //
    //     return "courses/manage";
    // }
}
