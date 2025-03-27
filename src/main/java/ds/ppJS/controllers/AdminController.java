package ds.ppJS.controllers;

import ds.ppJS.models.User;
import ds.ppJS.services.CustomUserDetailsService;
import ds.ppJS.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final CustomUserDetailsService userService;
    private final RoleService roleService;

    public AdminController(CustomUserDetailsService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        User currentUser = userService.getCurrentUserFromContext();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserRoles", userService.getCurrentUserRoles(currentUser));
        return "FinalPage";
    }
}
