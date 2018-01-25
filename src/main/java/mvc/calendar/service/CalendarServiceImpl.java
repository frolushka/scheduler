package mvc.calendar.service;

import mvc.calendar.model.Calendar;
import mvc.calendar.model.Event;
import mvc.calendar.repository.CalendarRepository;
import mvc.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private EventRepository eventRepository;

    public void saveNewEventToCalendar(Calendar calendar, Event event) {
        event.setCalendar(calendar);
        eventRepository.save(event);
    }

    public void saveNewEventToCalendar(Long calendarId, Event event) {
        saveNewEventToCalendar(calendarRepository.findById(calendarId), event);
    }

    public List<Calendar> getAllCalendars() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(calendarRepository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .collect(toList());
    }

    public List<Calendar> getUserCalendars(String owner) {
        return calendarRepository.findByOwner(owner);
    }

}
