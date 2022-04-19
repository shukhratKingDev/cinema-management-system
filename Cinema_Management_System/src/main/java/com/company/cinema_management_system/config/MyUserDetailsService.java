package com.company.cinema_management_system.config;

import com.company.cinema_management_system.entity.Manager;
import com.company.cinema_management_system.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
//@Service

public class MyUserDetailsService implements UserDetailsService {
    ManagerRepository managerRepository;
@Autowired
    public MyUserDetailsService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Manager> user = managerRepository.findByUsername(username);
       user.orElseThrow(()->new UsernameNotFoundException("Not found"+username));
        return user.map(MyUserDetails::new).get();
    }
}
