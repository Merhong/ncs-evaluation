package lab.nomad.springbootncsevaluation.model.ability_units;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

public interface AbilityUnitsRepository extends JpaRepository<AbilityUnits, Long> {

    boolean existsByCode(String code);

    Page<AbilityUnits> findAllByNameContains(Pageable pageable, String name);
}
