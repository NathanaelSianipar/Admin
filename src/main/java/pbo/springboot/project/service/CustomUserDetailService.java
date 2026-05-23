package pbo.springboot.project.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import pbo.springboot.project.model.User;

import pbo.springboot.project.repository.UserRepository;

@Service
public class CustomUserDetailService
        implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        User user = repository.findByEmail(email)

                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User tidak ditemukan"
                        )
                );

        return new org.springframework.security.core.userdetails.User(

                user.getEmail(),

                user.getPassword(),

                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        );
    }
}