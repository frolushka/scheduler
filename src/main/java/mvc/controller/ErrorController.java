package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping(value = "error/{errorCode}")
    public String renderErrorPage(@PathVariable String errorCode) {
        return "error/" + errorCode;
    }

}
