package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Role;
import shop.app.entity.User;
import shop.app.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user){
        ModelAndView mav = new ModelAndView("registration.html");
        mav.addObject("user",new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registrationPost(User user){
        final String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/hello")
    public ModelAndView hello(){
        return new ModelAndView("hello.html");
    }
}
