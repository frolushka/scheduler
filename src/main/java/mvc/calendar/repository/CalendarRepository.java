package mvc.calendar.repository;

import mvc.calendar.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Calendar findByName(String name);

    Calendar findByOwnerId(Long ownerId);

    Calendar findById(Long id);

}
