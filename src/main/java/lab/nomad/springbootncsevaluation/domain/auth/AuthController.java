package lab.nomad.springbootncsevaluation.domain.auth;

import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private  final UsersRepository usersRepository;


    @GetMapping("/login")
    public String login(Model model) {
        List<Users>users = usersRepository.findAll();

        model.addAttribute("users",users);
        return "auth/login";
    }

    @GetMapping("/join")
    public String join() {
        return "auth/join";
    }
}