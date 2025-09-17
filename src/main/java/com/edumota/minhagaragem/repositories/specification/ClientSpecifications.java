package com.edumota.minhagaragem.repositories.specification;

import com.edumota.minhagaragem.domain.entities.Client;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ClientSpecifications {

    public static Specification<Client> belongsToUser(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }

    public static Specification<Client> withNameLike(String name) {
        return (root, query, criteriaBuilder) -> {

            if(name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
