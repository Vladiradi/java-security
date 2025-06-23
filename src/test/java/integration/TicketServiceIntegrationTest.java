package integration;


import de.telran.ticketapp.entity.Ticket;
import de.telran.ticketapp.exception.TicketNotFoundException;
import de.telran.ticketapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void testCreateAndGetAll() {
        Ticket ticket = Ticket.builder()
                .title("Test Movie")
                .price(99.99)
                .build();

        ticketService.create(ticket);

        List<Ticket> tickets = ticketService.getAll();

        assertFalse(tickets.isEmpty(), "Ticket list should not be empty");

        boolean exists = tickets.stream()
                .anyMatch(t -> "Test Movie".equals(t.getTitle()));
        assertTrue(exists, "Created ticket should exist in list");
    }

    @Test
    void testGetById() {
        Ticket ticket = Ticket.builder()
                .title("Test Concert")
                .price(150.0)
                .build();

        Ticket saved = ticketService.create(ticket);

        Ticket found = ticketService.getById(saved.getId());

        assertNotNull(found);
        assertEquals("Test Concert", found.getTitle());
        assertEquals(150.0, found.getPrice());
    }

    @Test
    void testGetById_NotFound() {
        Long invalidId = -100L;
        assertThrows(TicketNotFoundException.class,
                () -> ticketService.getById(invalidId));
    }
}