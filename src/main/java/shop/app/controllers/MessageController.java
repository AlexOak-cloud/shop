package shop.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Message;
import shop.app.entity.User;
import shop.app.services.MessageService;
import shop.app.services.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/chat/{recipientId}")
    public ModelAndView chatGet(@PathVariable("recipientId") int id,
                                @ModelAttribute("message") Message message) {
        User recipient = userService.getById(id);
        ModelAndView mav = new ModelAndView("/views/message/chat.html");
        mav.addObject("message ", new Message());
        mav.addObject("recipientUser", recipient);
        mav.addObject("messageList",
                messageService.formatList(
                        messageService.sortedListByDate(
                                messageService.getChat(id)), userService.getById(id)));
        return mav;
    }

    @PostMapping("/chat/{recipientId}")
    public ModelAndView chatPost(@PathVariable("recipientId") int id,
                                 Message message) {
        ModelAndView mav = new ModelAndView("redirect:/message/chat/{recipientId}");
        mav.addObject("recipientUser", userService.getById(id));
        message.setRead(false);
        message.setRecipientId(id);
        messageService.save(message);
        return mav;
    }

    @GetMapping("/chats")
    public ModelAndView getChats() {
        ModelAndView mav = new ModelAndView("/views/message/chat.html");
        return mav;
    }
}
