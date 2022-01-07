package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

<<<<<<< HEAD

    @Autowired
    private UserService userService;

    @GetMapping("/login")
=======
    @Autowired
    private UserService userService;

    @GetMapping("/login?error")
    public ModelAndView errorLogin(){
        return new ModelAndView("/login/errors/loginError.html");
    }

    @GetMapping("/")
>>>>>>> d8d74fc04126cc4fabded9fee26af73cfaf87151
    public ModelAndView root() {
        return new ModelAndView("views/login/login.html");
    }

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("/views/registration/registration.html");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registrationPost(@Valid User user) {
        user.setBlocked(false);
        userService.saveUser(user);
        return new ModelAndView("redirect:/");
    }
}
