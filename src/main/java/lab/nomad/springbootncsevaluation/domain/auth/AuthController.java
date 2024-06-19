package lab.nomad.springbootncsevaluation.domain.auth;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UsersRepository usersRepository;

    @GetMapping("/")
    public String bootstrap(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        model.addAttribute("user", customUserDetails.user());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        List<Users> users = usersRepository.findAll();

        model.addAttribute("users", users);
        return "auth/login";
    }

    @GetMapping("/join")
    public String join() {
        return "auth/join";
    }
}