package mvc.controller;

import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String root(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("invites", userService.getAllInvites());
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}
