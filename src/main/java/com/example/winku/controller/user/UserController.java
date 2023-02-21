package com.example.winku.controller.user;

import com.example.winku.Service.user.UserService;
import com.example.winku.domain.user.User;
import com.example.winku.dto.user.RegisterDto;
import com.example.winku.dto.user.SignInDto;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/landing")
    public String getSignIn(Model model) {
        model.addAttribute("user", new SignInDto());

        return "user/landing";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("register", new RegisterDto());

        return "user/register";
    }

    @PostMapping("/landing")
    public String postSingIn(@Validated @ModelAttribute("user") SignInDto signInDto,
                             BindingResult bindingResult,
                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/landing";
        }

        Optional<User> user = userService.signIn(signInDto);
        if (!user.isPresent()) {
            bindingResult.reject("singInError");
            return "user/landing";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user.get());
        return "redirect:/feed/index";
    }

    @PostMapping("/register")
    public String postRegister(@Validated @ModelAttribute("register") RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new SignInDto());
            return "user/register";
        }

        Optional<User> user = userService.findUserByLoginId(registerDto.getLoginId());
        if (user.isPresent()) {
            bindingResult.reject("alreadyExistId");
            model.addAttribute("user", new SignInDto());
            return "user/register";
        }

        userService.signUp(new User(registerDto.getName(), registerDto.getPassword(), registerDto.getLoginId(), registerDto.getEmail(), registerDto.getGender()));


        return "redirect:/user/landing";

    }

    @PostConstruct
    public void init() {
        userService.signUp(new User("test", "test", "test", "test@naver.com", "Male"));
    }
}
