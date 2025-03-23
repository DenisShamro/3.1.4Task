package ds.ppJS.controllers;

import ds.ppJS.models.User;
import ds.ppJS.services.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class USER {

    private final CustomUserDetailsService userService;

    public USER(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String user(Model model) {
        User currentUser = userService.getCurrentUserFromContext();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserRoles", userService.getCurrentUserRoles(currentUser));
        return "UserPageM";
    }
}
