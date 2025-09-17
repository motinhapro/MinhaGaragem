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

    public Client(String name, String email, String number, User user) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.user = user;
    }
}
