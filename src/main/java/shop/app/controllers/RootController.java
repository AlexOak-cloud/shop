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
import java.util.List;

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
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/views/root/registration.html");
        }
        if(!user.getPassword().equals(user.getCheckPassword())){
            return new ModelAndView("/views/root/errors/registrationPassword.html");
        }
        List<User> users = userService.allUsers();
        for(User tmp : users){
            if(user.getUsername().equals(tmp.getUsername())){
                return new ModelAndView("/views/root/errors/registrationUserName.html");
            }
        }
        userService.saveUser(user);
        return new ModelAndView("redirect:/root/login");
    }
    /*public void validate(Object target, Errors errors) {
		SignupForm signupForm = (SignupForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username must not be empty.");
		String username = signupForm.getUsername();
		if ((username.length()) > 16) {
			errors.rejectValue("username", "username.tooLong", "Username must not more than 16 characters.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
		if (!(signupForm.getPassword()).equals(signupForm
				.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
		}

		if( !EmailValidator.getInstance().isValid( signupForm.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Email address is not valid.");
		}
	}*/
}
