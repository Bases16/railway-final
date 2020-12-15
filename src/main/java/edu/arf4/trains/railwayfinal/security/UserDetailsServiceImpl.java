package edu.arf4.trains.railwayfinal.security;

import edu.arf4.trains.railwayfinal.dao.UserDao;
import edu.arf4.trains.railwayfinal.model.Status;
import edu.arf4.trains.railwayfinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserById(email);
        if (user == null) {
            throw new UsernameNotFoundException("there wasn't found user with email: " + email);
        } else return userDetailsFromUserEntity(user);
    }

    private static UserDetails userDetailsFromUserEntity(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .accountExpired(!user.getStatus().equals(Status.ACTIVE))
                .credentialsExpired(!user.getStatus().equals(Status.ACTIVE))
                .accountLocked(!user.getStatus().equals(Status.ACTIVE))
                .disabled(!user.getStatus().equals(Status.ACTIVE))
                .build();
    }
}
