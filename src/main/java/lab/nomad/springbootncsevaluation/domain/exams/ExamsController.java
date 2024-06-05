package lab.nomad.springbootncsevaluation.domain.exams;

import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.service.ExamsService;
import lab.nomad.springbootncsevaluation.model.exams._enums.ExamStatus;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamsController {
    private  final ExamPapersRepository examPapersRepository;
    private  final StudentsRepository studentsRepository;
    private  final ExamsService examsService;

    // 등록
    @GetMapping("saveForm/{examPaperId}")
    public String saveForm(@PathVariable Long examPaperId, Model model) {
        ExamPapers examPapers = examPapersRepository.findByIdWithAbilityUnit(examPaperId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exam paper Id:" + examPaperId));
        model.addAttribute("examPapers", examPapers);

        return "exam/saveForm";
    }

    // 폼 제출 처리
    @PostMapping("/save")
    public String submitForm(@RequestParam String name, @RequestParam String tel, @RequestParam Long examPaperId, Model model) {
        // 학생을 이름과 연락처로 찾음
        Students student = studentsRepository.findByNameAndTel(name, tel)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student details: " + name + ", " + tel));

        // DTO 생성
        ExamsSaveRequestDTO requestDTO = new ExamsSaveRequestDTO();
        requestDTO.setStudentsId(student.getId());
        requestDTO.setExamPaperId(examPaperId);
        requestDTO.setStatus(ExamStatus.BEFORE_EXAM); // 필요한 상태 설정

        // 서비스 호출
        ExamsSaveResponseDTO responseDTO = examsService.save(examPaperId, requestDTO);
        model.addAttribute("exam", responseDTO);
        return "redirect:/exam/one";
    }


    //시험상세보기
    @GetMapping("/one")
    public  String one(){

        return  "exam/one";
    }
}
