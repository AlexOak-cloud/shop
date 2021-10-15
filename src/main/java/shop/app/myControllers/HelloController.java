package shop.app.myControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HelloController {

    @GetMapping
    public ModelAndView start(){
        return new ModelAndView("start.html");
    }
}
