package edu.arf4.trains.railwayfinal.security;

import edu.arf4.trains.railwayfinal.dao.UserDao;
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
        } else return MyUserDetails.fromUser(user);
    }
}
