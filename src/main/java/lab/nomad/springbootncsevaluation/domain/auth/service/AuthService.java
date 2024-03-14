package lab.nomad.springbootncsevaluation.domain.auth.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation.domain.auth.dto.JoinRequestDTO;
import lab.nomad.springbootncsevaluation.domain.auth.dto.JoinResponseDTO;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JoinResponseDTO join(JoinRequestDTO requestDTO) {

        // 패스워드 암호화
        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        // 입력받은 역할 값 확인
        UserRole requestUserRole;
        try {
            requestUserRole = UserRole.valueOf(requestDTO.getRole());
        } catch (Exception e) {
            log.error("존재하지 않는 역할입니다. : " + requestDTO.getRole());
            throw new Exception400("존재하지 않는 역할입니다. : Not Exist Role");
        }

        // 저장할 엔티티 생성
        Users userForSave = Users.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .name(requestDTO.getName())
                .tel(requestDTO.getTel())
                .email(requestDTO.getEmail())
                .role(requestUserRole)
                .build();

        // 유저 저장
        Users userPS = usersRepository.save(userForSave);

        // 응답 DTO 리턴
        return new JoinResponseDTO(userPS);
    }

}
