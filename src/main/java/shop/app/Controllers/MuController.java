package shop.app.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.app.entity.Person;
import shop.app.repos.MyCrudRepository;

@Controller
public class MuController {

    @Autowired
    private MyCrudRepository repo;

    @GetMapping("/showList")
    public ModelAndView getAll(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("showList.html");
        final Iterable<Person> all = repo.findAll();
        mav.addObject("list",all);
        return mav;
    }


}
