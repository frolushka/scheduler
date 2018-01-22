//package mvc.model.calendar.events;
//
//import mvc.model.calendar.Calendar;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "calendar_events")
//public class Events {
//
//    private final Calendar calendar;
//
//    private Long id;
//    private Set<Event> events;
//
//    public Events(Calendar calendar) {
//        events = new HashSet<>();
//        this.calendar = calendar;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public Long getId() {
//        return id;
//    }
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar_events", cascade = CascadeType.ALL)
//    public Set<Event> get() {
//        return events;
//    }
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    public Calendar getCalendar() {
//        return calendar;
//    }
//
//}
