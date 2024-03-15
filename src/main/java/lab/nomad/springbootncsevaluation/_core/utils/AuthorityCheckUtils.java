package lab.nomad.springbootncsevaluation._core.utils;

import lab.nomad.springbootncsevaluation._core.exception.Exception403;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;

import java.util.List;

public class AuthorityCheckUtils {

    public static boolean authorityCheck(CustomUserDetails customUserDetails, String userRole) {
        // 권한 체크
        if (!customUserDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(userRole))) {

            throw new Exception403(ExceptionMessage.COMMON_FORBIDDEN.getMessage());
        }

        return true;
    }

    public static boolean authorityCheck(CustomUserDetails customUserDetails, List<String> userRole) {

        var authorities = customUserDetails.getAuthorities().stream()
                .filter(grantedAuthority -> userRole.contains(grantedAuthority.getAuthority())).toList();

        if (authorities.size() >= 1) {
            return true;
        } else {
            throw new Exception403(ExceptionMessage.COMMON_FORBIDDEN.getMessage());
        }
    }
}
