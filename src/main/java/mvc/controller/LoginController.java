package mvc.controller;

import mvc.model.user.Invite;
import mvc.model.user.User;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLogin(Model model) {
        return "login/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "login/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @Valid User user, BindingResult bindingResult) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "error.password", "Введенные пароли не совпадают.");
//            model.addAttribute("error.passwordConfirm", true);
        }
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.username", "Пользователь с введенным именем уже существует.");
//            model.addAttribute("error.username", true);
        }
        Invite inviteIsFree = userService.findInvite(user.getInvite());
        if (inviteIsFree == null || inviteIsFree.getUsername() != null) {
            bindingResult.rejectValue("invite", "error.invite", "Введенный пригласительный не действителен или уже занят.");
//            model.addAttribute("error.invite", true);
        }

        if (bindingResult.hasErrors()) {
            return "login/registration";
        } else {
            userService.saveUser(user);
            return "redirect:/";
        }
    }

}
