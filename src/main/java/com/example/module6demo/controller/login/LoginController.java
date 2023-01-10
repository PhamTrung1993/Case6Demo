package com.example.module6demo.controller.login;

import com.example.module6demo.model.DTO.UserRegisterDTO;
import com.example.module6demo.model.JwtResponse;
import com.example.module6demo.model.User;
import com.example.module6demo.service.jwt.JwtService;
import com.example.module6demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = jwtService.createToken(authentication);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                User currentUser = userService.findByUsername(user.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
            } catch (Exception e) {
                return ResponseEntity.ok("Not Found User");
            }
        } catch (Exception e) {
            return ResponseEntity.ok("Not Found User");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO user) {
        if (userService.checkDoubleUser(user.getUserName()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.changeDTO(user),HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<>("ROLE_ADMIN", HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("ROLE_USER", HttpStatus.OK);
    }
    @PutMapping("/editPassword/{id}")
    public ResponseEntity<User> updatePassWord(@PathVariable Long id,@RequestBody String password){
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.changePassword(userOptional, password), HttpStatus.OK);
    }
}
