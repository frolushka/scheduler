package mvc.controller;

import mvc.calendar.model.Calendar;
import mvc.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public String userRoot(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        List<Calendar> calendarList = calendarService.getUserCalendars(username);
        model.addAttribute("calendars", calendarList);
        return "user/index";
    }

}
