package lab.nomad.springbootncsevaluation.domain.students.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.students.dto.*;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final CoursesRepository coursesRepository;

    @Transactional
    public StudentsSaveResponseDTO save(Long courseId, StudentsSaveRequestDTO requestDTO) {

        // 해당 과정 ID로 과정을 찾기
        Optional<Courses> optionalCourse = coursesRepository.findById(courseId);

        // 과정이 존재하지 않는 경우
        if (optionalCourse.isEmpty()) {
            throw new Exception400(ExceptionMessage.NOT_FOUND_COURSE.getMessage());
        }

        // 과정이 있는 경우
        Courses course = optionalCourse.get();

        // 학생 등록
        Students studentForSave = Students.builder()
                .course(course)
                .tel(requestDTO.getTel())
                .name(requestDTO.getName())
                .studentStatus(requestDTO.getStatus())
                .build();

        // DB 로직
        Students studentPS = studentsRepository.save(studentForSave);

        return new StudentsSaveResponseDTO(studentPS);
    }


    // 특정 학생조회
    public StudentsOneResponseDTO one(Long id) {

        Optional<Students> optionalStudent = studentsRepository.findById(id);

        // 만약 학생 정보가 존재하지 않으면 404 에러를 발생시킵니다.
        Students studentPS = optionalStudent.orElseThrow(
                () -> new Exception400(ExceptionMessage.NOT_FOUND_STUDENT.getMessage()));

        return new StudentsOneResponseDTO(studentPS);
    }


    // 전체 학생조회
    public StudentsPageResponseDTO page(Pageable pageable, String searchValue, Users user) {
        Page<Students> pageStudents;

        // TODO : 로그인한 사람 권한에 따라 조회되는 범위 동적으로 적용시키기

        // 검색어가 있는 경우
        if (searchValue != null && !searchValue.isEmpty()) {
            pageStudents = studentsRepository.findByNameAndDeleteDateIsNull(searchValue, pageable);
        } else { // 검색어가 없는 경우,전체조회
            pageStudents = studentsRepository.findAllAndByDeleteDateIsNull(pageable);
        }

        // StudentsPageResponseDTO 페이지로 변환하여 반환
        return new StudentsPageResponseDTO(pageStudents);
    }

    // 학생수정
    @Transactional

    public StudentsUpdateResponseDTO update(Long id, Users user, StudentsSaveRequestDTO requestDTO) {

        // TODO : 관리자는 모든 학생 수정 가능하게 고치기.

        // 해당 학생을 등록한 강사인지 확인
        Courses coursePS = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.getId())
                .orElseThrow(() -> new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage()));

        // 학생 정보 가져오기
        Students studentPS = studentsRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_STUDENT.getMessage()));

        // 학생이 해당 강사의 과정에 속해 있는지 확인
        if (!studentPS.getCourse()
                .equals(coursePS)) {
            throw new Exception400(ExceptionMessage.NOT_FOUND_STUDENT.getMessage());
        }

        // 학생수정
        studentPS.update(requestDTO.getName(), requestDTO.getTel(), requestDTO.getStatus());

        // 저장 및 DTO로 반환
        return new StudentsUpdateResponseDTO(studentPS);

    }

    // 학생삭제
    @Transactional
    public StudentsDeleteResponseDTO delete(Long id, Users user) {
        // 해당 학생을 등록한 강사인지 확인
        Courses coursePS = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.getId())
                .orElseThrow(() -> new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage()));

        // 학생 정보 가져오기
        Students studentPS = studentsRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_STUDENT.getMessage()));

        // 학생이 해당 강사의 과정에 속해 있는지 확인
        if (!studentPS.getCourse()
                .equals(coursePS)) {
            throw new Exception400(ExceptionMessage.NOT_FOUND_STUDENT.getMessage());
        }

        // 삭제 시간 업데이트
        studentPS.delete();

        // 저장 및 DTO로 반환
        return new StudentsDeleteResponseDTO(studentPS);
    }
}

