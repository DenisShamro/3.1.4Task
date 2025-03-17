package ds.PP3_1_2SS.controllers;
import ds.PP3_1_2SS.models.Role;
import ds.PP3_1_2SS.models.User;
import ds.PP3_1_2SS.services.CustomUserDetailsService;
import ds.PP3_1_2SS.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;

@Controller
public class AdminAndUserController {


    private final CustomUserDetailsService userService;
    private final RoleService roleService;

    @Autowired
    public AdminAndUserController(CustomUserDetailsService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "AdminPage";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("userId")Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit_user";
    }

    @PostMapping("/admin/edit")
    public String confirmUpdate(@ModelAttribute("user") User user, @RequestParam List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(new HashSet<>(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "create_user";
    }


    @PostMapping("/admin/create")
    public String createUser( @ModelAttribute User user, @RequestParam List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(new HashSet<>(roles));
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/user")
    public String user( Model model) {
        model.addAttribute("user", userService.getCurrentUserFromContext());
        return "UserPage";
    }
}













