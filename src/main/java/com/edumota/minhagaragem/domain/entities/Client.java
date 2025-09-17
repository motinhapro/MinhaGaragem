package com.edumota.minhagaragem.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Client(User user, String number, String email, String name) {
        this.user = user;
        this.number = number;
        this.email = email;
        this.name = name;
    }
}
