package mvc.calendar.model;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;

    protected Event() {
    }

    public Event(Calendar calendar, String name, String description) {
        this.calendar = calendar;
        this.name = name;
        this.description = description;
    }

    public Event(Event copy) {
        this(copy.getCalendar(), copy.getName(), copy.getDescription());
        id = copy.getId();
    }

    public Long getId() {
        return id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return (int)(long)(id % Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Event) && this.toString().equals(((Event)obj).toString());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
