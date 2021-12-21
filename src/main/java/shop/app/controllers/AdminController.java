package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.entity.products.Product;
import shop.app.services.ProductService;
import shop.app.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @GetMapping("/product/showNew")
    public ModelAndView showNewProduct() {
        ModelAndView mav = new ModelAndView("/views/products/showNews.html");
        List<Product> all = productService.getAll();
        mav.addObject("list", all);
        return mav;
    }


    @DeleteMapping("/product/delete/{id}")
    public ModelAndView deleteProductById(@PathVariable("id") int id) {
        productService.deleteById(id);
        return new ModelAndView("redirect:/product/showNew");
    }

    @PostMapping("/user/toBlock/{id}")
    public ModelAndView userToBlockById(@PathVariable("id") int id) {
        User byId = userService.getById(id);
        byId.setBlocked(true);
        return new ModelAndView("redirect:/product/showNew");
    }

    @GetMapping("/product/getById/{id}")
    public ModelAndView productGetById(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("/views/products/getByIdForAdmin.html");
        Product product = productService.getById(id);
        User user = product.getUser();
        mav.addObject("product", product);
        mav.addObject("user", user);
        return mav;
    }
}
