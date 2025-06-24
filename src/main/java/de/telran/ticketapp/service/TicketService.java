package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.Ticket;
import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();               // Получить все тикеты

    Ticket createTicket(Ticket ticket);         // Создать тикет

    Ticket getTicketById(long id);              // Получить по ID

    Ticket getTicketByTitle(String title);      // Найти по заголовку

    Ticket updateTicket(long id, Ticket ticket); // Обновить тикет по ID

    void deleteTicketById(long id);             // Удалить тикет
}