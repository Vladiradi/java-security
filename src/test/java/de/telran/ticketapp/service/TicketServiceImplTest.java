package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.Ticket;
import de.telran.ticketapp.exception.TicketNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
@Transactional
class TicketServiceImplTest {
    @Autowired
    private TicketService ticketService;
    private Ticket ticket;


    @Test
    void getAllAndCreateTest() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Concert");
        ticket.setPrice(80);

        ticketService.create(ticket);

        List<Ticket> tickets = ticketService.getAll();
        assertFalse(tickets.isEmpty());
        assertEquals("Concert", tickets.get(0).getTitle());

        boolean found = tickets.stream()
                .anyMatch(t -> "Concert".equals(t.getTitle()) && t.getPrice() == 80);
        assertTrue(found);
    }

    @Test
    void createTest() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Rammstein");
        ticket.setPrice(100);

        Ticket created = ticketService.create(ticket);

        assertNotNull(created);
        assertEquals("Rammstein", created.getTitle());
        assertEquals(100, created.getPrice());
        assertNotNull(created.getId());
    }

    @Test
    void getByIdTest() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Metallica");
        ticket.setPrice(120);

        Ticket created = ticketService.create(ticket);
        Ticket found = ticketService.getById(created.getId());

        assertNotNull(found);
        assertEquals("Metallica", found.getTitle());
        assertEquals(120, found.getPrice());
        assertNotNull(created.getId());
    }

    @Test
    void getByIdNotFoundTest() {
        Long invalidId = 999999L;
        assertThrows(TicketNotFoundException.class, () -> ticketService.getById(invalidId));
    }
}