package mvc.calendar.service;

import mvc.calendar.model.Calendar;
import mvc.calendar.model.Event;

import java.util.List;

public interface CalendarService {

    public void saveNewEventToCalendar(Calendar calendar, Event event);

    public void saveNewEventToCalendar(Long calendarId, Event event);

    public List<Calendar> getAllCalendars();

    public List<Calendar> getUserCalendars(String owner);

}
