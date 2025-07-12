package com.StackIt.StackIt.Security;

import com.StackIt.StackIt.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User.UserBuilder builder = User.builder();

        if(userName == null){
            return builder.username("dkkek").password("dkmemdm").roles("GUEST").build();
        }

        if (userRepo.existsById(userName)){
        com.StackIt.StackIt.models.User user = userRepo.findById(userName).get();
        builder.username(user.getMail()).password(user.getPassword()).roles("USER");
        }else{
            throw new RuntimeException("failed");
        }
        return builder.build();
    }
}
