package ds.PP3_1_2SS.controllers;
import ds.PP3_1_2SS.models.Role;
import ds.PP3_1_2SS.models.User;
import ds.PP3_1_2SS.services.CustomUserDetailsService;
import ds.PP3_1_2SS.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



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
        User currentUser = userService.getCurrentUserFromContext();
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("currentUserRoles", userService.getCurrentUserRoles(currentUser));

        model.addAttribute("emptyUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());


        return "AdminPage";
    }

    @GetMapping("/user")
    public String user( Model model) {
        User currentUser = userService.getCurrentUserFromContext();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserRoles", userService.getCurrentUserRoles(currentUser));
        return "UserPageM";
    }



    @PostMapping("/admin/delete")
    public String delete(@RequestParam("userId")Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }



    @PostMapping("/admin/edit")
    public String confirmUpdate(@ModelAttribute("user") User user, @RequestParam List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(new HashSet<>(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }





    @PostMapping("/admin/create")
    public String createUser( @ModelAttribute User user, @RequestParam List<Integer> roleIds) {
        List<Role> roles = roleService.getRolesByIds(roleIds);
        user.setRoles(new HashSet<>(roles));
        userService.save(user);
        return "redirect:/admin";
    }

//    @GetMapping("/user")
//    public String user( Model model) {
//        model.addAttribute("user", userService.getCurrentUserFromContext());
//        return "UserPage";
//    }

}













