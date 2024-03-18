package lab.nomad.springbootncsevaluation.model.ability_units.elements;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbilityUnitElementsRepository extends JpaRepository<AbilityUnitElements, Long> {

    List<AbilityUnitElements> findAllByAbilityUnit(AbilityUnits abilityUnit);
}
