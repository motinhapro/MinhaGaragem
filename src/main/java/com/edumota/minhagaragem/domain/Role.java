package com.edumota.minhagaragem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    private Long id;

    private String name;

    @Getter
    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        Long roleId;

        Values(Long roleId) {
            this.roleId = roleId;
        }
    }
}
