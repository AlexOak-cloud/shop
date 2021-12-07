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
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public ModelAndView createGet(@ModelAttribute("product")Product product){
        ModelAndView mav = new ModelAndView("/views/products/create.html");
        mav.addObject("product", new Product());
        return mav;
    }


    @PostMapping("/create")
    public ModelAndView createPost(@Valid Product product, @Autowired BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            new ModelAndView("/views/products/create.html");
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
        ModelAndView mav = new ModelAndView("/views/products/getById.html");
        final Product byId = productService.getById(id);
        final User user = byId.getUser();
        final String date = byId.getDate().toString();
        mav.addObject("product",byId);
        mav.addObject("user",user);
        mav.addObject("date",date);
        return mav;
    }

    @PostMapping("product/search/byName")
    public ModelAndView searchByName(String name){
        ModelAndView mav = new ModelAndView("redirect:/views/products/productList.html");
        final List<Product> products = productService.getAllByName(name);
        mav.addObject("list", products);
        return mav;
    }
}

