package ds.ppJS.controllers;

import ds.ppJS.dto.UserDTO;
import ds.ppJS.models.Role;
import ds.ppJS.models.User;
import ds.ppJS.services.CustomUserDetailsService;
import ds.ppJS.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/admin")
public class RestController {

    private final CustomUserDetailsService userService;
    private final RoleService roleService;

    public RestController(CustomUserDetailsService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> roles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> users(@PathVariable int id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        User createdUser = userService.saveOrUpdate(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserDTO userDTO, @PathVariable int id) {
        User updatedUser = userService.saveOrUpdate(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUserById(id);
        return "User with id " + id + " was deleted";
    }

}
