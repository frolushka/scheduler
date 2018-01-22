//package mvc.model.calendar;
//
//import mvc.model.calendar.events.Events;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "calendars")
//public class Calendar {
//
//    private Long id;
//    private String name;
//
//    private Events events;
//
//    protected Calendar() {}
//
//    public Calendar(String name) {
//        this.name = name;
//
//        events = new Events(this);
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "calendars", cascade = CascadeType.ALL)
//    public Events getEvents() {
//        return events;
//    }
//
//}
