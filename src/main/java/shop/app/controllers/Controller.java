package shop.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Account;
import shop.app.entity.Person;
import shop.app.services.AccountService;
import shop.app.services.PersonService;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private AccountService accountRepository;

    @Autowired
    private PersonService personRepository;


    @GetMapping("/")
    public String mainMenu() {
        return "main-menu.html";
    }

    @GetMapping("/signIn")
    public String signIn(@ModelAttribute("account") Account account) {
        return "sign-in.html";
    }

    @GetMapping("/registration")
    public ModelAndView registrationGet(@ModelAttribute("person") Person person,
                                        @ModelAttribute("account") Account account) {
        ModelAndView mav = new ModelAndView("registration.html");
        mav.addObject("person",person);
        mav.addObject("account ",account);
        return mav;
    }

    @PostMapping("/registration")
    public String registrationPost(Person person, Account account) {
        person.setAccount(account);
        personRepository.save(person);
        accountRepository.save(account);
        return "redirect:/";
    }
}
