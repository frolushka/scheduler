//package mvc.model.calendar.events;
//
//import mvc.model.calendar.Calendar;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "events")
//public class Event {
//
//    private final Calendar calendar;
//
//    private Long id;
//
//    private String name;
//    private String description;
//
//    public Event(Calendar calendar, String name, String description) {
//        this.calendar = calendar;
//        this.name = name;
//        this.description = description;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "calendar_events_id")
//    public Calendar getCalendar() {
//        return calendar;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//}
