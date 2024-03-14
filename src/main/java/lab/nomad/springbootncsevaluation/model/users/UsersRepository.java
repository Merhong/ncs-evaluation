package lab.nomad.springbootncsevaluation.model.users;

import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}
