package de.telran.ticketapp.controller;
import de.telran.ticketapp.dto.CreateUserRequestDto;
import de.telran.ticketapp.dto.UserResponceDto;
import de.telran.ticketapp.entity.LocalUser;
import de.telran.ticketapp.service.LocalUserService;
import de.telran.ticketapp.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Component
@RestController
@RequestMapping("/users")
@Slf4j
public class LocalUserController {

    @Autowired
    private Converter<LocalUser, CreateUserRequestDto, UserResponceDto> converter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LocalUserService localUserService;

    @GetMapping
    List<LocalUser> getAll() {
        List<LocalUser> all = localUserService.getAll();
        log.debug("List all  {}", all);
        return all;
    }
    @PostMapping
    UserResponceDto create(@RequestBody CreateUserRequestDto userDto) {
        entity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        LocalUser user = converter.convert(userDto);
        LocalUser savedUser = localUserService.create(user);
        return dto;
    }
}