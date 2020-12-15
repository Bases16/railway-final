package edu.arf4.trains.railwayfinal.service;


import edu.arf4.trains.railwayfinal.dao.UserDao;
import edu.arf4.trains.railwayfinal.dto.RegisterUserDto;
import edu.arf4.trains.railwayfinal.model.Role;
import edu.arf4.trains.railwayfinal.model.Status;
import edu.arf4.trains.railwayfinal.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterUserDto dto) {

        if (userDao.getUserById(dto.getEmail()) != null) {
            throw new RuntimeException("USER ALREADY EXISTS"); // TODO
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userDao.addUser(user);
    }


}
