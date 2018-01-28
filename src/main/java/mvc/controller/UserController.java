package mvc.controller;

import mvc.calendar.model.Calendar;
import mvc.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    // ----------------------
    // --- КАЛЕНДАРИ
    // ----------------------

    @GetMapping(value = "/calendar?{id}")
    public String getCalendars(@RequestParam(name = "id") Long id) {
        return "user/calendar";
    }

    @GetMapping(value = "/calendar/add")
    public String addCalendar(Model model) {
        model.addAttribute("calendar", new Calendar());
        return "user/calendar-add";
    }

    @PostMapping(value = "/calendar/add")
    public String addCalendar(@Valid Calendar calendar, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "user/calendar-add";
        } else {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            calendar.setOwner(username);
            calendarService.saveNewCalendar(calendar);
            return "redirect:/user";
        }
    }
}
