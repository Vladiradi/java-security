package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.Ticket;
import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();                // Получить все тикеты

    Ticket getTicketById(Long id);               // Получить по ID

    Ticket findTicketByTitle(String title);      // Найти по заголовку

    Ticket createTicket(Ticket ticket);          // Создать тикет

    Ticket updateTicket(Long id, Ticket ticket); // Обновить тикет по ID

    void deleteTicketById(Long id);              // Удалить тикет
}
