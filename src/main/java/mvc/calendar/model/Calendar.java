package mvc.calendar.model;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "calendars")
public class Calendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_id")
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "owner")
    private String owner;

    @OneToMany(mappedBy = "calendar")
    private Set<Event> events;

    protected Calendar() {}

    public Calendar(String name, String description, String owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOwnerId() {
        return owner;
    }

    public void setOwnerId(String ownerId) {
        this.owner = ownerId;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        return (int)(long)(id % Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Calendar) && this.toString().equals(((Calendar)obj).toString());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
