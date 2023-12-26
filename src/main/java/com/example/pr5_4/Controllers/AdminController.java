package com.example.pr5_4.Controllers;

import com.example.pr5_4.Models.Role;
import com.example.pr5_4.Models.User;
import com.example.pr5_4.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/Admin")
@Controller
public class AdminController {

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/Index")
    public String auth(Model model){
        model.addAttribute("title", "Администрирование");
        model.addAttribute("users", userRepository.findAll());
        return "/Admin/Index";
    }




    @GetMapping("/Index/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        userRepository.delete(user);
        return "redirect:/Admin/Index";
    }

    @PostMapping("/Update/{id}")
    public String updateUser(@RequestParam String username,
                             @RequestParam(name="roles[]", required = false) String[] roles,
                             @PathVariable Long id)
    {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);

        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepository.save(user);
        return "redirect:/Admin/Index";
    }

    @GetMapping("/Update/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user",userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", Role.values());
        model.addAttribute("title", "Пользователи");
        return "/Admin/Update";
    }
}
