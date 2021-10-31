package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.Repository.ProductRepository;
import shop.app.entity.Product;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("showAll.html");
        final List<Product> all = repository.findAll();
        mav.addObject("all", all);
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("getById.html");
        final Product byId = repository.getById(id);
        mav.addObject("product", byId);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute("product") Product product) {
        return new ModelAndView("add.html");

    }

    @PostMapping("/add")
    public String addPost(Product product) {
        repository.save(product);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDelete(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateGet(@PathVariable("id")long id,@ModelAttribute Product product){
        final ModelAndView modelAndView = new ModelAndView("update.html");
        modelAndView.addObject("name",product.getName());
        modelAndView.addObject("price",product.getPrice());
        return modelAndView;
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") long id,@ModelAttribute("product")Product product){
        repository.save(product);
        return "redirect:/";
    }


}
