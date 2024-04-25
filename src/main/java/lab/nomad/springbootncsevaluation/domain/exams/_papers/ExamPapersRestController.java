package lab.nomad.springbootncsevaluation.domain.exams._papers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam-paper")
@Slf4j
public class ExamPapersRestController {

    /**
     * ExamPaper 등록은 AbilityUnit Id가 필요해서
     * {@link lab.nomad.springbootncsevaluation.domain.ability_units.AbilityUnitsRestController} 에서
     *  PostMapping("/{abilityUnitId}/exam-paper") 처리
     */


}
