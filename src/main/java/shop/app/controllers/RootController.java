package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.services.UserService;

import javax.validation.Valid;

@Controller
public class RootController {



    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("views/login/login.html");
    }

    @GetMapping("/loginError")
    public ModelAndView loginError(){
        return new ModelAndView("views/login/error/loginError.html");
    }

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user) {

        ModelAndView mav = new ModelAndView("/views/registration/registration.html");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registrationPost(@Valid User user,
                                         @Autowired BindingResult bindingResult) {

        user.setBlocked(false);
        userService.saveUser(user);
        return new ModelAndView("redirect:/");
    }
}
