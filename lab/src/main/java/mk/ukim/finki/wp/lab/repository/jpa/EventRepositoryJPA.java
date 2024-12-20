package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepositoryJPA extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword%")
    List<Event> searchEvents(@Param("keyword") String keyword);
}
