package com.example.module6demo.service.user;

import com.example.module6demo.model.User;
import com.example.module6demo.service.IGeneralService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    Optional<User> checkDoubleUser(String username);
}
