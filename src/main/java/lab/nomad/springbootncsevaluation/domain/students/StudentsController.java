package lab.nomad.springbootncsevaluation.domain.students;


import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentsService studentsService;
    private final StudentsRepository studentsRepository;
    private  final CoursesRepository coursesRepository;

    //전체보기
    @GetMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
        // 페이지당 학생 수
        int pageSize = 10; // 페이지당 학생 수를 5로 설정합니다.

        // 전체 학생 수 조회
        long totalStudents = studentsRepository.count();

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        // 현재 페이지에서 가져올 학생 목록 조회
        List<Students> students = studentsRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();

        // 모델에 필요한 데이터 추가
        model.addAttribute("students", students);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "students/listForm";
    }


    //상세보기
    @GetMapping("/one/{id}")
    public  String detailForm(@PathVariable Long id, Model model){
        // 현재 페이지에서 가져올 학생 목록 조회
        Optional<Students> students = studentsRepository.findById(id);

        model.addAttribute("students", students.orElse(null)); // Optional이 비어있을 경우 null을 넘겨줌


        return  "students/detailForm";
    }


    //수정
    @GetMapping("/update")
    public String update(Model model){
        // 모든 학생 데이터 가져오기
        List<Students> students = studentsRepository.findAll();
        // 코스 데이터 가져오기
        List<Courses> courses = coursesRepository.findAll();

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "students/updateForm";
    }
}
