package de.telran.ticketapp.repository;

import de.telran.ticketapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // стандартные методы
    Ticket findByTitle(String title);

    List<Ticket> findAllByTitle(String title);

    // корректный native SQL-запрос
    @Query(value = """
        SELECT t.* FROM tickets t 
        LEFT JOIN local_users u ON t.local_user_id = u.id
        """, nativeQuery = true)
    List<Ticket> findAllWithUsers();
}