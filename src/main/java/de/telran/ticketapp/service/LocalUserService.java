package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.LocalUser;

import java.util.List;
import java.util.Optional;

public interface LocalUserService {

    List<LocalUser> findAll();

    LocalUser findById(Long id);

    LocalUser save(LocalUser localUser);

    LocalUser update(Long id, LocalUser localUser);

    void deleteById(Long id);

    Optional<LocalUser> findByEmail(String email);
}
