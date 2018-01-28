package mvc.controller;

import mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage() {
        return "admin/index";
    }

    @GetMapping(value = "/generate-invite")
    public String generateInvite() {
        String invite = userService.generateInvite();
        return "redirect:/admin/generate-invite/" + invite;
    }

    @GetMapping(value = "/generate-invite/{invite}")
    public String generateInvite(@PathVariable("invite") String invite) {
        return "admin/index";
    }

}
