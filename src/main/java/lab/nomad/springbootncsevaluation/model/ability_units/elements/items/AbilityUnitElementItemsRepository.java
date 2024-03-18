package lab.nomad.springbootncsevaluation.model.ability_units.elements.items;

import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbilityUnitElementItemsRepository extends JpaRepository<AbilityUnitElementItems, Long> {

    List<AbilityUnitElementItems> findAllByAbilityUnitElement(AbilityUnitElements abilityUnitElement);
}
