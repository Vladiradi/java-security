package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
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
    }

    @Test
    void create() {
    }

    @Test
    void getById() {
    }
}