package com.example.pr5_4.Controllers;

import com.example.pr5_4.Models.Role;
import com.example.pr5_4.Models.User;
import com.example.pr5_4.Repositories.ClassRepository;
import com.example.pr5_4.Repositories.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @GetMapping("/homePage")
    public String homePage(Model model){
        model.addAttribute("title", "Главная");
        return "/homePage";
    }
}
