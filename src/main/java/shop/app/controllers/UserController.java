package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import shop.app.services.ProductService;
import shop.app.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    @ResponseBody
    public ModelAndView userMain(Principal principal) {
        ModelAndView mav = new ModelAndView("user/main.html");

        mav.addObject("allProductsByUser",productService.getAllByUser());
        mav.addObject("user", userService.getAuthUser());
        return mav;
    }
}

