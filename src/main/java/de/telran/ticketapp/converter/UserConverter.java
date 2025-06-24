package de.telran.ticketapp.converter;

import de.telran.ticketapp.dto.CreateUserRequestDto;
import de.telran.ticketapp.dto.UserResponseDto;
import de.telran.ticketapp.entity.LocalUser;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<LocalUser, CreateUserRequestDto, UserResponseDto> {

    @Override
    public LocalUser toEntity(CreateUserRequestDto dto) {
        LocalUser user = new LocalUser();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword()); // пароль будет зашифрован в контроллере
        return user;
    }

    @Override
    public UserResponseDto toDto(LocalUser user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
}