package lab.nomad.springbootncsevaluation._core.utils;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;

public class AuthorityCheckUtils {

    public static boolean authorityCheck(CustomUserDetails customUserDetails, String userRole) {
        // 권한 체크
        if (!customUserDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(userRole))) {

            throw new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage());
        }

        return true;
    }
}
