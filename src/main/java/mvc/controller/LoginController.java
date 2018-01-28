package mvc.controller;

import mvc.user.model.Invite;
import mvc.user.model.User;
import mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @GetMapping(value = "/login")
    public String openLogin() {
        return "login";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (!bindingResult.hasFieldErrors("password") && !user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "error.password", "*Введенные пароли не совпадают.");
        }
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.username", "*Пользователь с введенным именем уже существует.");
        }
        if (!bindingResult.hasFieldErrors("username") && !user.getUsername().matches("^[a-zA-Z0-9]+$")) {
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
            authenticateUserAndSetSession(user, request);
            return "redirect:/";
        }
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
