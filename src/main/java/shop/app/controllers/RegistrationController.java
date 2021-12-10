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
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("root.html");
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

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/views/registration/registration.html");
        }
        if(!user.getPassword().equals(user.getCheckPassword())){
            return new ModelAndView("/views/registration/errors/registrationPassword.html");
        }
        List<User> users = userService.allUsers();
        for(User tmp : users){
            if(user.getUsername().equals(tmp.getUsername())){
                return new ModelAndView("/views/registration/errors/registrationUserName.html");
            }
        }
        for(User tmp: users){
            if(tmp.getPhoneNumber().equals(user.getPhoneNumber())){
                return new ModelAndView("/views/registration/errors/registrationPhoneNumber.html");
            }
        }
        userService.saveUser(user);
        return new ModelAndView("redirect:/login/login");
    }
}
