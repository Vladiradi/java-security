package de.telran.ticketapp.repository;

import de.telran.ticketapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTitle(String title);
    List<Ticket> findAllByTitle(String title);

    @Query (value = "SELECT * FROM Ticket t LEFT JOIN LocalUser u ON u.id = t.id")
    List<Ticket> getAllWithUsers();

    @Query (value = "SELECT t.* FROM ticket as t", nativeQuery = true)
    List<Ticket> getAllWithAllUsers();
}
