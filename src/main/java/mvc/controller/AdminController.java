package mvc.controller;

import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String adminPage() {
        return "blank";
    }

    @RequestMapping(value = "/generate-invite")
    public String generateInvite() {
        String invite = userService.generateInvite();
        return "redirect:/admin/generate-invite/" + invite;
    }

    @RequestMapping(value = "/generate-invite/{invite}")
    public String generateInvite(@PathVariable("invite") String invite) {
        return "blank";
    }

}
