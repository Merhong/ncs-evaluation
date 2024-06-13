package lab.nomad.springbootncsevaluation.domain.exams._results;


import lab.nomad.springbootncsevaluation.domain.exams._results.service.ExamResultsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/exam_results")
@RequiredArgsConstructor
public class ExamResultsController {
    private  final ExamResultsRepository examResultsRepository;
    private  final CoursesRepository coursesRepository;
    private  final ExamResultsService examResultsService;

    // 과정별 학생 평가 결과 페이지 리스트
    @GetMapping("listForm/{id}")
    public String getCourseDetails(@PathVariable Long id, Model model) {
        Optional<Courses> courseOptional = coursesRepository.findById(id);
        if (courseOptional.isPresent()) {
            Courses course = courseOptional.get();
            model.addAttribute("course", course);
            model.addAttribute("examResults", examResultsService.getExamResultsByCourseId(id));
        } else {
            throw new IllegalArgumentException("Invalid course ID: " + id);
        }
        return "exam_results/listForm";
    }
}
