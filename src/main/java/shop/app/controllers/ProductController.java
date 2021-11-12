package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Product;
import shop.app.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService repo;

    @Autowired
    public ProductController(ProductService repo) {
        this.repo = repo;
    }

    @GetMapping("/showAll")
    public ModelAndView getAll(){
        ModelAndView mav = new ModelAndView("product/showAllProduct.html");
        final List<Product> all = repo.findAll();
        mav.addObject("all",all);
        return mav;
    }

    @GetMapping("/showOne/{id}")
    public ModelAndView getById(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("product/showByIdProduct.html");
        final Product byId = repo.getById(id);
        mav.addObject("product",byId);
        return mav;
    }

    @GetMapping("/createProduct")
    public ModelAndView newProductGet(@ModelAttribute("product")Product product){
        ModelAndView mav = new ModelAndView("product/createProduct.html");
        mav.addObject("product",new Product());
        return mav;
    }

    @PostMapping("/createProduct")
    public String  newProductPost(Product product){
        repo.save(product);
        return "redirect:/product/showAll";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        repo.deleteById(id);
        return "redirect:/product/showAll";
    }
}
