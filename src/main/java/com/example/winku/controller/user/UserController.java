package com.example.winku.controller.user;

import com.example.winku.Service.user.UserService;
import com.example.winku.domain.user.User;
import com.example.winku.dto.user.ProfileDto;
import com.example.winku.dto.user.RegisterDto;
import com.example.winku.dto.user.SignInDto;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String getSignIn(@RequestParam @Nullable String redirect, Model model) {
        model.addAttribute("user", new SignInDto());
        model.addAttribute("redirect", redirect);
        return "user/landing";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("register", new RegisterDto());

        return "user/register";
    }

    @PostMapping("/landing")
    public String postSingIn(@Validated @ModelAttribute("user") SignInDto signInDto, @RequestParam(defaultValue = "/feed/index") String redirect,
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

        HttpSession session = request.getSession(); // 세션이 있다면 해당 세션을 리턴 없다면 새로 발행
        session.setAttribute("user", user.get());


        return "redirect:" + redirect;
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션이 있다면 해당 세션을 리턴 없다면 새로 발행 - false로
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/user/landing";
    }
    @PostMapping("/profileUpdate")
    public String profileUpdate(@ModelAttribute ProfileDto profileDto, @RequestParam String redirect) {
        userService.profilUpdate(profileDto);
        return "redirect:" + redirect;
    }


    @PostConstruct
    public void init() {
        userService.signUp(new User("test", "test", "test", "test@naver.com", "Male"));
        userService.signUp(new User("asd", "asd", "asd", "test@naver.com", "Male"));
    }



}
