package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.Ticket;
import de.telran.ticketapp.exception.TicketNotFoundException;
import de.telran.ticketapp.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + id + " not found"));
    }

    @Override
    public Ticket findTicketByTitle(String title) {
        Ticket ticket = ticketRepository.findByTitle(title);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket with title '" + title + "' not found");
        }
        return ticket;
    }

    @Override
    public Ticket updateTicket(Long id, Ticket updated) {
        Ticket existing = getTicketById(id);
        existing.setTitle(updated.getTitle());
        existing.setPrice(updated.getPrice());
        // можно добавить обновление других полей
        return ticketRepository.save(existing);
    }

    @Override
    public void deleteTicketById(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException("Ticket with id " + id + " not found");
        }
        ticketRepository.deleteById(id);
    }
}
