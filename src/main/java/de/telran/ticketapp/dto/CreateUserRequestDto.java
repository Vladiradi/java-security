package de.telran.ticketapp.dto;

public record CreateUserRequestDto (String name,
                                   String surname,
                                   String email,
                                   String password) {
}
