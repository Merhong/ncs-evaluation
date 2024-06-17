package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.exams._papers.service.ExamPapersService;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service.ExamResultMultipleItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/exams-results")
@RequiredArgsConstructor
public class ExamResultMultipleItemsController {

    // DI
    private final ExamResultMultipleItemsService itemsService;
    private final ExamPapersService examPapersService;
    private final AbilityUnitsService abilityUnitsService;


    // 채점지
    @GetMapping("/{resultId}/item")
    public String detailForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue,
            @PathVariable Long resultId) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        // ERMI 내용 조회
        ExamResultMultipleItemsPageResponseDTO responseDTO = itemsService.page(resultId, pageable, searchValue,
                customUserDetails.user());
        // AbilityUnit 조회
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(responseDTO.getItems().get(0).getExamResultDTO().getExamsDTO().getExamPapersDTO().getAbilityUnitDTO().getId());

        System.out.println("능력단위 ID :" + responseDTO.getItems().get(0).getExamResultDTO().getExamsDTO().getExamPapersDTO().getAbilityUnitDTO().getId());

        // 모델에 필요한 데이터 추가
        model.addAttribute("ItemsPage", responseDTO);
        model.addAttribute("ItemList", responseDTO.getItems());
        model.addAttribute("pageable", responseDTO.getPageable());
        model.addAttribute("AbilityUnit", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("AbilityUnitElementList", abilityUnitOneResponseDTO.getAbilityUnit());



        return "exams/_results/_multiple_items/detailForm";
    }

}
