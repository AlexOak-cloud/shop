package shop.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login?error")
    public ModelAndView errorLogin(){
        return new ModelAndView("/login/errors/loginError.html");
    }

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("views/login/login.html");
    }

}
