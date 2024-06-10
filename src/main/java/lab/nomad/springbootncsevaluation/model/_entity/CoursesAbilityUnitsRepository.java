package lab.nomad.springbootncsevaluation.model._entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesAbilityUnitsRepository extends JpaRepository<CoursesAbilityUnits, Long> {

    List<CoursesAbilityUnits> findByCourseId(Long courseId);
}
