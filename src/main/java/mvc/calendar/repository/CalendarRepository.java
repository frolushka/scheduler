package mvc.calendar.repository;

import mvc.calendar.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findByName(String name);

    List<Calendar> findByOwner(String owner);

    Calendar findById(Long id);

}
