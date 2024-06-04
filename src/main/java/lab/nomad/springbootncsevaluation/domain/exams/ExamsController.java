package lab.nomad.springbootncsevaluation.domain.exams;

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

    // 등록
    @GetMapping("saveForm/{examPaperId}")
    public String saveForm(@PathVariable Long examPaperId, Model model) {
        ExamPapers examPapers = examPapersRepository.findByIdWithAbilityUnit(examPaperId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exam paper Id:" + examPaperId));
        model.addAttribute("examPapers", examPapers);

        return "exam/saveForm";
    }


    //시험상세보기
    @GetMapping("/one")
    public  String one(){

        return  "exam/one";
    }
}
