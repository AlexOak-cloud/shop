package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("admin.html");
    }

}
