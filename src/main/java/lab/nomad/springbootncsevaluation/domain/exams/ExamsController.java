package lab.nomad.springbootncsevaluation.domain.exams;

import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamFormDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.service.ExamsService;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.ExamsRepository;
import lab.nomad.springbootncsevaluation.model.exams._enums.ExamStatus;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamsController {
    private  final ExamPapersRepository examPapersRepository;
    private  final StudentsRepository studentsRepository;
    private  final ExamsService examsService;
    private  final ExamsRepository examsRepository;

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
    public String submitForm(@RequestBody ExamFormDTO examForm, Model model) {
        Students student = studentsRepository.findByNameAndTel(examForm.getName(), examForm.getTel())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with details: " + examForm.getName() + ", " + examForm.getTel()));

        ExamPapers examPaper = examPapersRepository.findById(examForm.getExamPaperId())
                .orElseThrow(() -> new IllegalArgumentException("ExamPaper not found with ID: " + examForm.getExamPaperId()));

        Exams exams = Exams.builder()
                .student(student)
                .examPaper(examPaper)
                .status(ExamStatus.BEFORE_EXAM)
                .build();

        examsRepository.save(exams);
        model.addAttribute("exam", exams);
        return "redirect:/exam/oneForm";
    }


    //시험상세보기
    @GetMapping("oneForm")
    public  String one(){

        return "exam/oneForm";
    }
}
