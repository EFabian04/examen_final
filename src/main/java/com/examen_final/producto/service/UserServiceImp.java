package com.examen_final.producto.service;

import com.examen_final.producto.models.User;
import com.examen_final.producto.repository.UserRepository;
import com.examen_final.producto.utils.Constants;
import com.examen_final.producto.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public User updateUser(Long id, User user) {
        User userBD = userRepository.findById(id).get();
        userBD.setFirstName(user.getFirstName());
        userBD.setLastName(user.getLastName());
        userBD.setAddress(user.getAddress());
        userBD.setBirthday(user.getBirthday());
        userBD.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userBD);
    }

    @Override
    public String login(User user) {
        Optional<User> userBd = userRepository.findByEmail(user.getEmail());
        if (userBd.isEmpty()) {
            throw new RuntimeException(Constants.USER_NOT_FOUND);
        }
        if(!passwordEncoder.matches(user.getPassword(),userBd.get().getPassword())){
            throw new RuntimeException(Constants.PASSWORD_INVALID);
        }
        return jwtUtil.create(String.valueOf(userBd.get().getId()),
                String.valueOf(userBd.get().getEmail()));
    }
}
