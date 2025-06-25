package de.telran.ticketapp.service;

import de.telran.ticketapp.entity.LocalUser;
import de.telran.ticketapp.exception.LocalUserNotFoundException;
import de.telran.ticketapp.exception.NoUniqueLocalUserEmailException;
import de.telran.ticketapp.repository.LocalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalUserServiceImpl implements LocalUserService {

    private final LocalUserRepository userRepository;

    @Override
    public List<LocalUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public LocalUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new LocalUserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public LocalUser save(LocalUser localUser) {
        userRepository.findByEmail(localUser.getEmail())
                .ifPresent(existing -> {
                    throw new NoUniqueLocalUserEmailException("User with email " + localUser.getEmail() + " already exists");
                });
        return userRepository.save(localUser);
    }

    @Override
    public LocalUser update(Long id, LocalUser updatedUser) {
        LocalUser existing = findById(id);
        existing.setName(updatedUser.getName());
        existing.setSurname(updatedUser.getSurname());
        existing.setEmail(updatedUser.getEmail());
        existing.setPassword(updatedUser.getPassword());
        existing.setPostAddress(updatedUser.getPostAddress());
        return userRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new LocalUserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<LocalUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
