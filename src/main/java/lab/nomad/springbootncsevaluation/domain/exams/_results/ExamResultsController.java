package lab.nomad.springbootncsevaluation.domain.exams._results;


import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service.ExamResultMultipleItemsService;
import lab.nomad.springbootncsevaluation.domain.exams._results.service.ExamResultsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exam-results")
@RequiredArgsConstructor
public class ExamResultsController {
    private final ExamResultsRepository examResultsRepository;
    private final CoursesRepository coursesRepository;
    private final ExamResultsService examResultsService;
    private final ExamResultMultipleItemsService itemsService;
    private final AbilityUnitsService abilityUnitsService;


    // 시험지를 평가한 페이지 (시험지 - 평가지)
    @GetMapping("/paper/{id}")
    public String evalForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
            @PathVariable Long id)  {

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
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(responseDTO.getItems().get(0).getExamResultDTO().getExamsDTO().getExamPapersDTO().getAbilityUnitDTO().getId());

        // 모델에 필요한 데이터 추가
        model.addAttribute("ItemsPage", responseDTO);
        model.addAttribute("ItemList", responseDTO.getItems());
        model.addAttribute("pageable", responseDTO.getPageable());
        model.addAttribute("AbilityUnit", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("AbilityUnitElementList", abilityUnitOneResponseDTO.getAbilityUnit());

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
        ExamResults examResults = examResultsService.getExamResultById(id);
        List<ExamResultMultipleItems> examResultItems = examResultsService.getExamResultItemsByExamResultId(id);

        // 각 시험 문제에 대한 모든 답변을 초기화
        for (ExamResultMultipleItems item : examResults.getExamResultItems()) {
            Hibernate.initialize(item.getExamPaperQuestion().getAnswers());
        }

        // 모델에 시험 결과 데이터 추가
        model.addAttribute("examResults", examResults);
        model.addAttribute("examResultItems", examResultItems);

        return "/exam_results/oneForm";
    }

    // 총평 업데이트 페이지
    @PostMapping("/save")
    public String updateComment(@RequestParam Long id, @RequestParam String comment) {
        examResultsService.updateComment(id, comment);
        return "redirect:/courses";
    }

}
