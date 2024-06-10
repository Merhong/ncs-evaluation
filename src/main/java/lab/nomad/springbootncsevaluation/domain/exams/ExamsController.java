package lab.nomad.springbootncsevaluation.domain.exams;

import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamFormDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsOneResponseDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


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
    public String submitForm(@RequestBody ExamFormDTO examForm, Model model, RedirectAttributes redirectAttributes) {
        Students student = studentsRepository.findByNameAndTel(examForm.getName(), examForm.getTel())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with details: " + examForm.getName() + ", " + examForm.getTel()));

        ExamPapers examPaper = examPapersRepository.findById(examForm.getExamPaperId())
                .orElseThrow(() -> new IllegalArgumentException("ExamPaper not found with ID: " + examForm.getExamPaperId()));

        Exams exams = Exams.builder()
                .student(student)
                .examPaper(examPaper)
                .status(ExamStatus.BEFORE_EXAM)
                .build();

        exams = examsRepository.save(exams); // 저장 후 생성된 객체를 다시 받아와야 합니다.
        model.addAttribute("exam", exams);

        redirectAttributes.addAttribute("id", exams.getId()); // 리디렉션 URL에 ID를 추가
        return "redirect:/exam/oneForm/{id}"; // 동적으로 ID를 포함한 URL로 리디렉션
    }


    //시험상세보기

    @GetMapping("oneForm/{id}")
    @Transactional(readOnly = true)
    public String one(@PathVariable Long id, Model model) {
        Optional<Exams> examOptional = examsRepository.findById(id);
        if (examOptional.isPresent()) {
            Exams exam = examOptional.get();
            ExamsOneResponseDTO examsOneResponseDTO = new ExamsOneResponseDTO(exam);
            model.addAttribute("examDetails", examsOneResponseDTO.getExams());
        } else {
            throw new IllegalArgumentException("Invalid Exam ID: " + id);
        }
        return "exam/oneForm";
    }
}
