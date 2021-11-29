package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Product;
import shop.app.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public ModelAndView createGet(@ModelAttribute("product")Product product){
        ModelAndView mav = new ModelAndView("/product/create.html");
        mav.addObject("product",new Product());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView createPost(Product product){
        productService.saveProduct(product);
        return new ModelAndView("redirect:/main.html");
    }




}

