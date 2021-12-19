package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Message;
import shop.app.services.MessageService;
import shop.app.services.UserService;
import java.time.LocalDateTime;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/message/chat/{recipientId}")
    public ModelAndView chatGet(@PathVariable("recipientId") int id,
                                @ModelAttribute("message") Message message) {
        ModelAndView mav = new ModelAndView("/views/message/chat.html");
        mav.addObject("message ", new Message());
        mav.addObject("recipientUser", userService.getById(id));
        return mav;
    }

    @PostMapping("/message/chat/{recipientId}")
    public ModelAndView chatPost(@PathVariable("recipientId") int id,
                                 Message message) {
        ModelAndView mav = new ModelAndView("redirect:/message/chat/{recipientId}");
        mav.addObject("recipientUser",userService.getById(id));
        message.setDate(LocalDateTime.now());
        message.setSenderUser(userService.getAuthUser());
        message.setRecipientUser(userService.getById(id));
        message.setRead(false);
        messageService.save(message);
        return mav;
    }
}
