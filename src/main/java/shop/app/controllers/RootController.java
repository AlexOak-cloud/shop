package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.User;
import shop.app.services.UserService;

import javax.validation.Valid;

@Controller
public class RootController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("root.html");
    }

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("/views/root/registration.html");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView registrationPost(@Valid User user,
                                         @Autowired BindingResult bindingResult) {
       if(!user.getPassword().equals(user.getCheckPassword())){
           bindingResult.
                   rejectValue("checkPassword","error.checkPassword",
                           "Пароли не совпадают");
       }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/views/root/registration");
        }
        userService.saveUser(user);
        return new ModelAndView("redirect:/login");
    }
    /*
    @PostMapping
    public ModelAndView NameEditPost(@ModelAttribute("customer") Customer customerForm, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/account/general/edit");
        if(customerForm.getName().length() > 18 || customerForm.getName().length() < 2){
            bindingResult.
                    rejectValue("name", "error.name",
                            "Неправильное количество символов. Количество букв должно быть от 2 до 18.");
        }

        if(customerForm.getSurname().length() > 18 || customerForm.getSurname().length() < 2){
            bindingResult.
                    rejectValue("surname", "error.surname",
                            "Неправильное количество символов. Количество букв должно быть от 2 до 18.");
        }
        if(bindingResult.hasErrors()){
            System.out.println("ошибки");
            return modelAndView;
        }
        return modelAndView;
    }
}*/
}
