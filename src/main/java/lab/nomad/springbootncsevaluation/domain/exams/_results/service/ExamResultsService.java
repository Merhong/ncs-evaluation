package lab.nomad.springbootncsevaluation.domain.exams._results.service;

import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsPageRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.ExamsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswersRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results._enums.ExamResultStatus;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItemsRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamResultsService {
    @Autowired
    private ExamsRepository examsRepository;

    @Autowired
    private ExamResultsRepository examResultsRepository;
    @Autowired
    private ExamPaperMultipleQuestionsRepository examPaperMultipleQuestionsRepository;
    @Autowired
    private ExamPaperMultipleQuestionAnswersRepository examPaperMultipleQuestionAnswersRepository;
    @Autowired
    private ExamResultMultipleItemsRepository examResultMultipleItemsRepository;


    // 시험 결과 저장
    @Transactional
    public ExamResultsSaveResponseDTO save(Long examId, ExamResultsSaveRequestDTO requestDTO) {
        if (examId == null) {
            throw new IllegalArgumentException("Exam ID must not be null");
        }

        // 시험 ID로 시험 정보 조회
        Exams exam = examsRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        // 시험 결과가 이미 존재하는지 확인
        if (examResultsRepository.findByExamId(examId).isPresent()) {
            throw new RuntimeException("Exam result already exists for exam id: " + examId);
        }

        // 시험 문제 목록 조회
        List<ExamPaperMultipleQuestions> questions = examPaperMultipleQuestionsRepository.findByExamPaperId(exam.getExamPaper().getId());

        // 총 점수 초기화
        int totalPoints = 0;

        // 제출된 답변을 기반으로 총 점수 계산
        for (ExamPaperMultipleQuestions question : questions) {
            // 각 문제에 대해 제출된 답변 ID 가져오기
            Long selectedAnswerId = requestDTO.getSelectedAnswers().get(question.getId());
            if (selectedAnswerId == null) {
                continue;
            }
            // 해당 답변이 정답인지 확인
            boolean isCorrect = examPaperMultipleQuestionAnswersRepository.findById(selectedAnswerId)
                    .map(answer -> answer.getIsCorrect())
                    .orElse(false);

            // 정답일 경우 해당 문제의 배점을 총 점수에 더함
            if (isCorrect) {
                totalPoints += question.getPoint();
            }
        }

        // 시험 결과 엔티티 생성
        ExamResults examResults = ExamResults.builder()
                .exam(exam)
                .totalPoint(totalPoints)
                .status(ExamResultStatus.DONE)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        // 시험 결과 저장
        examResults = examResultsRepository.save(examResults);

        // 시험 결과 저장 응답 DTO 생성
        return new ExamResultsSaveResponseDTO(examResults);
    }

    // 시험 결과 업데이트
    @Transactional
    public void updateComment(Long id, String comment) {
        ExamResults examResults = examResultsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam result not found with id: " + id));
        examResults.setComment(comment);
        examResults.setStatus(ExamResultStatus.DONE);
        examResults.setUpdateDate(LocalDateTime.now());
        examResultsRepository.save(examResults);
    }

    // 시험결과리스트
    public List<ExamResultsPageRequestDTO> getExamResultsByCourseId(Long courseId) {
        return examResultsRepository.findByCourseId(courseId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ExamResultsPageRequestDTO convertToDto(ExamResults examResult) {
        String status = examResult.getStatus() == ExamResultStatus.WAIT ? "총평입력필요" : "평가완료";
        String color = examResult.getStatus() == ExamResultStatus.WAIT ? "orange" : "green";
        int grade = calculateGrade(examResult.getTotalPoint());

        return ExamResultsPageRequestDTO.builder()
                .id(examResult.getId())
                .studentName(examResult.getExam().getStudent().getName())
                .tel(examResult.getExam().getStudent().getTel())
                .grade(grade)
                .totalPoint(examResult.getTotalPoint())
                .status(status)
                .color(color)
                .build();
    }

    private int calculateGrade(int totalPoint) {
        if (totalPoint >= 90) return 5;
        if (totalPoint >= 80) return 4;
        if (totalPoint >= 70) return 3;
        if (totalPoint >= 60) return 2;
        return 1;
    }

    // 시험 결과 ID를 통해 데이터를 가져오는 메서드
    @Transactional
    public ExamResults getExamResultById(Long id) {
        ExamResults examResults = examResultsRepository.findById(id).orElse(null);
        if (examResults != null) {
            Hibernate.initialize(examResults.getExam());
            Hibernate.initialize(examResults.getExam().getExamPaper());

            if (examResults.getExam().getStudent() != null) {
                Hibernate.initialize(examResults.getExam().getStudent());
                if (examResults.getExam().getStudent().getCourse() != null) {
                    Hibernate.initialize(examResults.getExam().getStudent().getCourse());
                }
            }

            if (examResults.getExam().getExamPaper().getAbilityUnit() != null) {
                Hibernate.initialize(examResults.getExam().getExamPaper().getAbilityUnit());
            }

            List<ExamPaperMultipleQuestions> multipleQuestions = examPaperMultipleQuestionsRepository.findByExamPaperId(examResults.getExam().getExamPaper().getId());
            multipleQuestions.forEach(question -> {
                Hibernate.initialize(question);
            });

            List<ExamResultMultipleItems> examResultItems = examResultMultipleItemsRepository.findByExamResultId(examResults.getId());
            examResultItems.forEach(item -> {
                Hibernate.initialize(item.getExamPaperQuestion());
                Hibernate.initialize(item.getExamPaperMultipleQuestionAnswers());
            });

            examResults.setExamResultItems(examResultItems);
        }
        return examResults;
    }

    // 학생이 선택한 답변을 가져오는 메서드 추가
    public List<ExamResultMultipleItems> getExamResultItemsByExamResultId(Long examResultId) {
        return examResultsRepository.findItemsByExamResultId(examResultId);
    }
}