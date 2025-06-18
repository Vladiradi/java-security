package de.telran.ticketapp.entity;

import jakarta.persistence.Cache;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;




@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "local_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LocalUser {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // задает автогенерацию значения поля
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String postAddress;

    @OneToMany(cascade = cascadeType.ALL)
    @JoinColumn(name = "local_user_id")
    @ToString.Exclude
    private Set<Ticket> ticket = new HashSet<>();
}
