package lab.nomad.springbootncsevaluation.domain.users.service;

import lab.nomad.springbootncsevaluation.domain.users.dto.UsersPageResponseDTO;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersPageResponseDTO page(String searchValue, String role, Pageable pageable) {

        // 검색어와 권한 키워드가 둘 다 있을 경우
        if (searchValue != null && role != null) {

            // 검색어와 권한, 페이지에이블을 사용하여 리파지토리 메서드 호출(검색어는 contains, 권한은 일치)
            return new UsersPageResponseDTO(usersRepository.findAllByUsernameContainsAndRole(pageable, searchValue, UserRole.valueOf(role)));

        // 검색어 키워드만 있을 경우
        } else if (searchValue != null) {

            // 검색어와 페이지에이블을 사용하여 리파지토리 메서드 호출
            return new UsersPageResponseDTO(usersRepository.findAllByUsernameContains(pageable, searchValue));

        // 권한 키워드만 있을 경우
        } else if (role != null) {

            // 권한과 페이지에이블을 사용하여 리파지토리 메서드 호출
            return new UsersPageResponseDTO(usersRepository.findAllByRole(pageable, UserRole.valueOf(role)));

        }

        // 검색어와 권한 키워드가 둘 다 없을 경우
        // 페이지에이블을 사용하여 리파지토리 메서드 호출
        return new UsersPageResponseDTO(usersRepository.findAll(pageable));
    }
}
