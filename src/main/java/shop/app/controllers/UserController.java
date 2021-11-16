package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Role;
import shop.app.entity.User;
import shop.app.entity.UserRole;
import shop.app.services.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@EnableJpaRepositories
public class UserController {

    @Autowired
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
        Set<UserRole> set = new HashSet<>();
        set.add(new UserRole(Role.ROLE_USER));
        user.setRoles(set);
        repo.save(user);
        return new ModelAndView("redirect:/user/showAll");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        repo.deleteById(id);
        return new ModelAndView("redirect:/user/showAll");
    }
}
