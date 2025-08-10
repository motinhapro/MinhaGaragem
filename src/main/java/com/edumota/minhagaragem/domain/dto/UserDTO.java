package com.edumota.minhagaragem.domain.dto;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.User;

public record UserDTO(String username) {

    public UserDTO(User user) {
        this(user.getUsername());
    }
}
