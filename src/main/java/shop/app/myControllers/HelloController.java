package shop.app.myControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Product;
import shop.app.repository.MyRepo;

import java.util.Optional;

@Controller
public class HelloController {

    @Autowired
    private MyRepo myRepo;

    @GetMapping("/start")
    public ModelAndView start(){
        final Optional<Product> byId = myRepo.findById(2L);
        final Product product = byId.get();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("start.html");
        mav.addObject("product",product);
        return mav;
    }
}
