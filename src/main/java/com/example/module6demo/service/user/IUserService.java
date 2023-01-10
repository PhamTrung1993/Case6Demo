package com.example.module6demo.service.user;

import com.example.module6demo.model.DTO.UserRegisterDTO;
import com.example.module6demo.model.JwtResponse;
import com.example.module6demo.model.User;
import com.example.module6demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    Optional<User> checkDoubleUser(String username);

    User changeDTO(UserRegisterDTO user);
    public User changePassword(Optional<User> user, String password);
}
