package com.rtt.ecr.rttcryptocurrency.service;

import com.rtt.ecr.rttcryptocurrency.entity.Role;
import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import com.rtt.ecr.rttcryptocurrency.repo.UserPersonalDetailsRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserPersonalDetailsRepo userRepository;

    public CustomUserDetailsService(UserPersonalDetailsRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
      //old  UserPersonalDetailsEntity user = userRepository.findByUserNameOrUserEmailId(usernameOrEmail, usernameOrEmail)
        UserPersonalDetailsEntity user = userRepository.findByUserEmailId(userEmailId);
               /* .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid Username and Password:" + userEmailId));*/
        return new User(user.getUserEmailId(),
                user.getUserPassword(), mapRolesToAuthorities(user
                .getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
