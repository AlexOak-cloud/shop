package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService repo;

    @Autowired
    public UserController(UserService repo) {
        this.repo = repo;
    }

    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("user/showAll.html");
        final List<User> all = repo.findAll();
        mav.addObject("all", all);
        return mav;
    }

    @GetMapping("/showOne/{id}")
    public ModelAndView showOne(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("user/showOne.html");
        final User user = repo.getById(id);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("user/registration.html");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registrationPost(User user) {
        repo.save(user);
        return new ModelAndView("redirect:/user/showAll");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        repo.deleteById(id);
        return new ModelAndView("redirect:/user/showAll");
    }
}
