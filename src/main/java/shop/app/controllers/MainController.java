package shop.app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {


    @GetMapping()
    public ModelAndView mainMenu(){
        return new ModelAndView("main.html");
    }

}