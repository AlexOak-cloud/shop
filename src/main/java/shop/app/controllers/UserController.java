package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import shop.app.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    @ResponseBody
    public ModelAndView userMain(Principal principal) {
        ModelAndView mav = new ModelAndView("user/main.html");
        final UserDetails userDetails = userService.loadUserByUsername(principal.getName());
        mav.addObject("user", userDetails);
        return mav;
    }
}

