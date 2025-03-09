package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }


    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoles() {
        List<String> roles = roleService.getAllRoles().stream()
                .map(role -> role.getAuthority())
                .toList();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody @Valid User user,
                                               @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        userService.saveUser(user, roleIds);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody @Valid User user,
                                             @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {
        userService.updateUser(id, user, roleIds);
        return ResponseEntity.ok("User updated successfully!");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
