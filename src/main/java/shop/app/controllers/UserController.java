package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Roles;
import shop.app.entity.User;
import shop.app.services.RoleService;
import shop.app.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@EnableJpaRepositories
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;



    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("user/showAll.html");
        final List<User> all = userService.findAll();
        mav.addObject("all", all);
        return mav;
    }

    @GetMapping("/showOne/{id}")
    public ModelAndView showOne(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("user/showOne.html");
        final User user = userService.getById(id);
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
        Set<Roles> role = new HashSet<>();
        role.add(roleService.getById((byte) 1));
        userService.save(user);
        return new ModelAndView("redirect:/user/showAll");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        return new ModelAndView("redirect:/user/showAll");
    }
}
