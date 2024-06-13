package lab.nomad.springbootncsevaluation.domain.ability_units;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto.ExamPaperMultipleQuestionsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ability-units")
public class AbilityUnitsController {

    // DI
    @Autowired
    private AbilityUnitsService abilityUnitsService;

    /* 능력 단위 상세보기 및 수정 */
    @GetMapping("/{id}")
    public String detailForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // abilityUnitId에 해당하는 AbilityUnitOneResponseDTO 가져오기
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(id);

        // 모델에 능력 단위 데이터를 추가
        model.addAttribute("AbilityUnit", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("AbilityUnitElementList", abilityUnitOneResponseDTO.getAbilityUnit()
                .getAbilityUnitElementList());

        // 요청을 위한 빈 DTO를 모델에 담기
        model.addAttribute("AbilityUnitUpdateRequestDTO", new AbilityUnitUpdateRequestDTO());
        model.addAttribute("AbilityUnitElementUpdateRequestDTO", new AbilityUnitElementUpdateRequestDTO());

        return "ability_units/detailForm";
    }


    /**
     * 시험지
     */

    // 시험지 상세보기
    @GetMapping("/1/exam-paper")
    public String detailForm() {

        return "exams/_papers/detailForm";
    }


    // 시험지 등록
    @GetMapping("/{abilityUnitId}/exam-paper/saveForm")
    public String saveForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long abilityUnitId) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // abilityUnitId에 해당하는 AbilityUnitOneResponseDTO 가져오기
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(abilityUnitId);

        // 요청을 위한 빈 DTO 모델에 담기
        model.addAttribute("ExamPaperSaveRequestDTO", new ExamPaperSaveRequestDTO());
        // 모델에 능력단위 ID에 해당하는 내용을 담아줌.
        model.addAttribute("AbilityUnit", abilityUnitOneResponseDTO.getAbilityUnit());
        model.addAttribute("AbilityUnitElementList", abilityUnitOneResponseDTO.getAbilityUnit()
                .getAbilityUnitElementList());

        // abilityUnitId 추가
        model.addAttribute("abilityUnitId", abilityUnitId);

        // 빈 문제 리스트를 추가
        List<ExamPaperMultipleQuestionsSaveRequestDTO> questionsList = new ArrayList<>();
        questionsList.add(new ExamPaperMultipleQuestionsSaveRequestDTO()); // 최소한의 빈 문제 항목을 추가
        model.addAttribute("questionsList", questionsList);

        return "exams/_papers/saveForm";
    }


    @GetMapping("/{id}/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        AbilityUnitOneResponseDTO abilityUnitOneResponseDTO = abilityUnitsService.one(id);

        // 데이터 필드를 채우기 위해서 응답 DTO를 모델에 담기
        model.addAttribute("AbilityUnitOneResponseDTO", abilityUnitOneResponseDTO);

        // 요청을 위한 빈 DTO를 모델에 담기
        model.addAttribute("AbilityUnitUpdateRequestDTO", new AbilityUnitUpdateRequestDTO());
        model.addAttribute("AbilityUnitElementUpdateRequestDTO", new AbilityUnitElementUpdateRequestDTO());

        return "ability_units/updateForm";
    }

    @GetMapping("/saveForm")
    public String saveForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        model.addAttribute("AbilityUnitSaveRequestDTO", new AbilityUnitSaveRequestDTO());
        model.addAttribute("AbilityUnitElementSaveRequestDTO", new AbilityUnitElementSaveRequestDTO());

        return "ability_units/saveForm";
    }


    @GetMapping
    public String listForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String searchValue) {

        // 사용자가 인증되지 않은 경우 처리
        if (customUserDetails == null) {
            System.out.println("사용자가 인증되지 않았습니다.");
            return "redirect:/login";
        }

        // 서비스 호출
        AbilityUnitPageResponseDTO abilityUnitPageResponseDTO = abilityUnitsService.page(pageable, searchValue);

        model.addAttribute("username", customUserDetails.getUsername());
        model.addAttribute("abilityUnitPage", abilityUnitPageResponseDTO);
        model.addAttribute("pageable", abilityUnitPageResponseDTO.getPageable());

        return "ability_units/listForm";
    }
}
