package lab.nomad.springbootncsevaluation.domain.exams._results;


import lab.nomad.springbootncsevaluation.domain.exams._results.service.ExamResultsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    // 학생평가 상세보기 페이지
    @GetMapping("oneForm/{id}")
    public String one(@PathVariable Long id, Model model) {
        ExamResults examResults = examResultsService.getExamResultById(id);
        List<ExamResultMultipleItems> examResultItems = examResultsService.getExamResultItemsByExamResultId(id);

        // 각 시험 문제에 대한 모든 답변을 초기화
        for (ExamResultMultipleItems item : examResults.getExamResultItems()) {
            Hibernate.initialize(item.getExamPaperQuestion().getAnswers());
        }

        // 모델에 시험 결과 데이터 추가
        model.addAttribute("examResults", examResults);
        model.addAttribute("examResultItems", examResultItems);

        return "exam_results/oneForm";
    }

    // 총평 업데이트 페이지
    @PostMapping("/save")
    public String updateComment(@RequestParam Long id, @RequestParam String comment) {
        examResultsService.updateComment(id, comment);
        return "redirect:/exam_results/oneForm/" + id;
    }

}
