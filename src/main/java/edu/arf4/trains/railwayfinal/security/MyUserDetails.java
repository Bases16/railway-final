package edu.arf4.trains.railwayfinal.security;

import edu.arf4.trains.railwayfinal.model.Role;
import edu.arf4.trains.railwayfinal.model.Status;
import edu.arf4.trains.railwayfinal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public MyUserDetails(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }
    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }
    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {

        return org.springframework.security.core.userdetails.User.builder()
                .username("admin")
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();


//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.ACTIVE),
//                user.getRole().getAuthorities()
//        );
    }
}
