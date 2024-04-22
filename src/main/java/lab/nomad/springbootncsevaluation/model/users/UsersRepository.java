package lab.nomad.springbootncsevaluation.model.users;

import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Page<Users> findAllByUsernameContainsAndRole(Pageable pageable, String name, UserRole role);

    Page<Users> findAllByUsernameContains(Pageable pageable, String name);

    Page<Users> findAllByRole(Pageable pageable, UserRole role);
}
