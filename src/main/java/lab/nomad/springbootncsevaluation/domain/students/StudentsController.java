package lab.nomad.springbootncsevaluation.domain.students;


import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.students.dto.StudentsUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.students.service.StudentsService;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentsService studentsService;
    private final StudentsRepository studentsRepository;
    private  final CoursesRepository coursesRepository;

}
