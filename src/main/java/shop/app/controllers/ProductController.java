package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.entity.products.Product;
import shop.app.services.ProductService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public ModelAndView createGet(@ModelAttribute("product")Product product){
        ModelAndView mav = new ModelAndView("/product/create.html");
        mav.addObject("product", new Product());
        return mav;
    }


    @PostMapping("/create")
    public ModelAndView createPost(@Valid Product product, @Autowired BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            new ModelAndView("/product/create.html");
        }
        productService.save(product);
        return new ModelAndView("redirect:/user/main");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        productService.deleteById(id);
        return new ModelAndView("redirect:/user/main");

    }

    @GetMapping("/getById/{id}")
    public ModelAndView getById(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("/product/getById.html");
        final Product byId = productService.getById(id);
        final User user = byId.getUser();
        final String date = byId.getDate().toString();
        mav.addObject("product",byId);
        mav.addObject("user",user);
        mav.addObject("date",date);
        return mav;
    }
}

