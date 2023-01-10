package com.example.module6demo.service.user;

import com.example.module6demo.model.DTO.UserRegisterDTO;
import com.example.module6demo.model.User;
import com.example.module6demo.model.UserPrinciple;
import com.example.module6demo.repository.IUserRepository;
import com.example.module6demo.service.image.IImageService;
import com.example.module6demo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IImageService imageService;
    @Autowired
    private RoleService roleService;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            return UserPrinciple.build(userOptional.get());
        }
        return null;
    }
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @Override
    public Optional<User> checkDoubleUser(String username) {
        return userRepository.checkDoubleUser(username);
    }

    public User changeDTO(UserRegisterDTO user){

        User users = new User();
        users.setUsername(user.getUserName());
        users.setPassword(user.getPassword());
        users.setPhoneNumber(user.getPhone());
        users.setEmail(user.getEmail());
        users.setAvatar(imageService.findById(Long.parseLong("1")).get().getImageName());
        String role = "1";
        Long role1 = Long.parseLong(role);
        users.setRole(roleService.findById(role1).get());
        return save(users);
    }

    public User changePassword(Optional<User> userOptional, String password){
        User users = userOptional.get();
        users.setPassword(password);
        return save(users);
    }

}
