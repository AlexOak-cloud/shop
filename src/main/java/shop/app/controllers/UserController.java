package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.entity.products.Product;
import shop.app.services.ProductService;
import shop.app.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    @ResponseBody
    public ModelAndView userMain() {
        ModelAndView mav = new ModelAndView("user/main.html");
        mav.addObject("allProduct",productService.sortedListByDate(productService.getAll()));
        mav.addObject("allProductsByUser",productService.getAllByUser(userService.getAuthUser()));
        mav.addObject("user", userService.getAuthUser());
        return mav;
    }

    @GetMapping("/getById/{id}")
    public ModelAndView getById(@PathVariable("id")int id){
        ModelAndView mav = new ModelAndView("user/getById.html");
        final User userById = userService.getById(id);
        final List<Product> allByUser = productService.sortedListByDate(productService.getAllByUser(userService.getById(id)));
        mav.addObject("user",userById);
        mav.addObject("allProductsByUser",allByUser);
        return mav;
    }
}

