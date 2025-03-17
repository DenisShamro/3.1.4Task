package ds.PP3_1_2SS.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Логика обработки успешного входа
        String targetUrl = determineTargetUrl(authentication); // Определение URL для редиректа
        response.sendRedirect(targetUrl); // Редирект пользователя
    }

    private String determineTargetUrl(Authentication authentication) {
        // Логика определения редиректа
        // Пример: перенаправление на разные страницы в зависимости от роли
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            return "/admin"; // Если роль ADMIN
        } else {
            return "/user"; // Если другие роли
        }
    }
}
