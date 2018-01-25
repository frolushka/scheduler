package mvc.controller;

import mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String root(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("invites", userService.getAllInvites());
        return "index";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test/index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

}
