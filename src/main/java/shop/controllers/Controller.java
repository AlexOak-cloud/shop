package shop.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.app.entity.Account;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/")
    public String mainMenu(){
        return "main-menu.html";
    }

    @GetMapping("/SignIn")
    public String signIn(@ModelAttribute("account")Account account){
        return "sign-in.html";
    }
}
