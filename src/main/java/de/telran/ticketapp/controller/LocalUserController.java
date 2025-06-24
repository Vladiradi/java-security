package de.telran.ticketapp.controller;

import de.telran.ticketapp.dto.CreateUserRequestDto;
import de.telran.ticketapp.dto.UserResponceDto;
import de.telran.ticketapp.entity.LocalUser;
import de.telran.ticketapp.exception.LocalUserNotFoundException;
import de.telran.ticketapp.service.LocalUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class LocalUserController {

    private final LocalUserService localUserService;
    private final PasswordEncoder passwordEncoder;
    private final Converter<LocalUser, CreateUserRequestDto, UserResponceDto> converter;

    @GetMapping
    public List<UserResponceDto> getAllUsers() {
        List<LocalUser> users = localUserService.getAll();
        log.debug("Retrieved users: {}", users);
        return users.stream().map(converter::convert).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponceDto> getUserById(@PathVariable Long id) {
        LocalUser user = localUserService.findById(id);
        return ResponseEntity.ok(converter.convert(user));
    }

    @PostMapping
    public ResponseEntity<UserResponceDto> createUser(@RequestBody CreateUserRequestDto userDto) {
        LocalUser user = converter.convert(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        LocalUser savedUser = localUserService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convert(savedUser));
    }

    @PutMapping
    public ResponseEntity<UserResponceDto> updateUser(@RequestBody CreateUserRequestDto userDto) {
        LocalUser user = converter.convert(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        LocalUser updatedUser = localUserService.update(user);
        return ResponseEntity.ok(converter.convert(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        localUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(LocalUserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(LocalUserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + ex.getMessage());
    }
}