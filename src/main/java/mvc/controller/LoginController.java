package mvc.controller;

import mvc.user.model.Invite;
import mvc.user.model.User;
import mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String openLogin() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors("password") && !user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "error.password", "*Введенные пароли не совпадают.");
        }
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.username", "*Пользователь с введенным именем уже существует.");
        }
        if (!bindingResult.hasFieldErrors("username") && !user.getUsername().matches("^(?=.*[a-zA-Z0-9])(?=\\S+$)$")) {
            bindingResult.rejectValue("username", "error.username", "*Имя пользователя должно состоять только из букв" +
                    " латинского алфавита и цифр.");
        }
        Invite inviteIsFree = userService.findInvite(user.getInvite());
        if (!bindingResult.hasFieldErrors("invite") && (inviteIsFree == null || inviteIsFree.getUsername() != null)) {
            bindingResult.rejectValue("invite", "error.invite", "*Введенный пригласительный не действителен или уже занят.");
        }
        if (!bindingResult.hasFieldErrors("password")
                && !user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$")) {
            bindingResult.rejectValue("password", "error.password", "*Пароль должен быть не меньше 6 символов в длину," +
                    " содержать 1 цифру, 1 строчную и 1 заглавную буквы латинского алфавита.");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            return "redirect:/";
        }
    }

}
