package lab.nomad.springbootncsevaluation.domain.users;

import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private  final UsersRepository usersRepository;

    //전체보기
    @GetMapping("/list")
    public String list(Model model){

        List<Users> users = usersRepository.findAll();

        model.addAttribute("users", users);

        return  "users/list";
    }

    //등록
    @GetMapping("/saveForm")
    public String saveForm(Model model){

        List<Users> users = usersRepository.findAll();

        model.addAttribute("users", users);

        return  "users/saveForm";
    }
    //상세보기
    @GetMapping("/one/{id}")
    public  String one(@PathVariable Long id, Model model){
        // 현재 페이지에서 가져올 학생 목록 조회
        Optional<Users> users = usersRepository.findById(id);



        model.addAttribute("users", users.orElse(null)); // Optional이 비어있을 경우 null을 넘겨줌


        return  "users/one";
    }

}


