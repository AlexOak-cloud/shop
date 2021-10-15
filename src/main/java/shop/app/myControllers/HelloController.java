package shop.app.myControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.Entity.Product;

@Controller
public class HelloController {

    @GetMapping("/start")
    public ModelAndView start(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("start.html");
        Product product = new Product("Milk",12);
        mav.addObject("product",product);
        return mav;
    }
}
