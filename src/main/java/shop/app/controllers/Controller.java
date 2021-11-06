package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.repository.UserRepository;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    public ModelAndView mainMenu() {
        return new ModelAndView("main-menu.html");
    }



    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("registration.html");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/registration")
    public String registrationPost(User user) {
        repository.save(user);
        return "redirect:/";
    }
}
