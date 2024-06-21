package lab.nomad.springbootncsevaluation.domain.exams._results;


import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service.ExamResultMultipleItemsService;
import lab.nomad.springbootncsevaluation.domain.exams._results.service.ExamResultsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.exams.ExamsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswersRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exam-results")
@RequiredArgsConstructor
public class ExamResultsController {
    private final ExamResultsRepository examResultsRepository;
    private final CoursesRepository coursesRepository;
    private final ExamResultsService examResultsService;
    private final ExamResultMultipleItemsService itemsService;
    private final AbilityUnitsService abilityUnitsService;
    private final ExamsRepository examsRepository;
    private final ExamPaperMultipleQuestionsRepository examPaperMultipleQuestionsRepository;
    private final ExamPaperMultipleQuestionAnswersRepository examPaperMultipleQuestionAnswersRepository;

    // 시험지를 평가한 페이지 (시험지 - 평가지)
    @GetMapping("/paper/{id}")
    public String evalForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
            @PathVariable Long id) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        // ERMI 내용 조회
        ExamResultMultipleItemsPageResponseDTO responseDTO = itemsService.page(id, pageable, searchValue,
                customUserDetails.user());
        // AbilityUnit 조회
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(responseDTO.getItems()
                .get(0)
                .getExamResultDTO()
                .getExamsDTO()
                .getExamPapersDTO()
                .getAbilityUnitDTO()
                .getId());

        // ExamPaperMultipleQuestions 가져오기
        List<ExamPaperMultipleQuestions> questions = examPaperMultipleQuestionsRepository.findByExamPaperId(
                responseDTO.getItems()
                        .get(0)
                        .getExamResultDTO()
                        .getExamsDTO()
                        .getExamPapersDTO()
                        .getId());
        List<ExamPaperMultipleQuestionAnswers> answers = examPaperMultipleQuestionAnswersRepository.findByExamPaperMultipleQuestionIdIn(
                questions.stream()
                        .map(ExamPaperMultipleQuestions::getId)
                        .collect(Collectors.toList()));

        // 선택된 답안 정보를 저장하는 Map 생성
        Map<Long, Long> checkedAnswers = responseDTO.getItems().stream()
                .flatMap(item -> item.getQuestionDTO().stream()
                        .flatMap(question -> item.getAnswerDTO().stream()
                                .map(answer -> Map.entry(question.getId(), answer.getId()))
                        )
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        // 모델에 필요한 데이터 추가
        model.addAttribute("ItemsPage", responseDTO);
        model.addAttribute("ItemList", responseDTO.getItems());
        model.addAttribute("pageable", responseDTO.getPageable());
        model.addAttribute("AbilityUnit", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("AbilityUnitElementList", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        model.addAttribute("checkedAnswers", checkedAnswers);

        return "/exams/_results/evalForm";
    }

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
        return "/exam_results/listForm";
    }

    // 학생평가 상세보기 페이지
    @GetMapping("oneForm/{id}")
    public String one(@PathVariable Long id, Model model) {
        // 서비스 메서드를 호출하여 ID로 ExamResults 객체를 가져옴
        ExamResults examResults = examResultsService.getExamResultById(id);
        // 서비스 메서드를 호출하여 ID로 ExamResultItems 리스트를 가져옴
        List<ExamResultMultipleItems> examResultItems = examResultsService.getExamResultItemsByExamResultId(id);

        List<ExamPaperMultipleQuestions> questions = examPaperMultipleQuestionsRepository.findByExamPaperId(examResults.getExam().getExamPaper().getId());
        List<ExamPaperMultipleQuestionAnswers> answers = examPaperMultipleQuestionAnswersRepository.findByExamPaperMultipleQuestionIdIn(
                questions.stream().map(ExamPaperMultipleQuestions::getId).collect(Collectors.toList())
        );
        // 각 ExamResultItems 객체의 ExamPaperQuestion의 답변들을 초기화

            model.addAttribute("examResults", examResults);
            model.addAttribute("questions", questions);
            model.addAttribute("answers", answers);
        model.addAttribute("examResultItems", examResultItems);

        return "/exam_results/oneForm";  // 뷰 이름 반환
    }

    // 총평 업데이트 페이지
    @PostMapping("/save")
    public String updateComment(@RequestParam Long id, @RequestParam String comment) {
        examResultsService.updateComment(id, comment);
        return "redirect:/courses";
    }

}
