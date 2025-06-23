package de.telran.ticketapp.converter;

import de.telran.ticketapp.dto.CreateUserRequestDto;
import de.telran.ticketapp.dto.UserResponseDto;
import de.telran.ticketapp.entity.LocalUser;

public class UserConverter implements Converter<LocalUser, CreateUserRequestDto,
        UserResponseDto> {

    @Override
    public LocalUser toEntity(CreateUserRequestDto createUserRequestDto) {
        return null;
    }
    @Override
    public UserResponseDto toDto(LocalUser localUser) {
        return null;
    }
}
