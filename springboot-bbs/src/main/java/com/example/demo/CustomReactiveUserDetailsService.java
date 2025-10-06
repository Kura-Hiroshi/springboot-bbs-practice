package com.example.demo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositoies.AuthorityRepository;
import com.example.demo.repositoies.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public CustomReactiveUserDetailsService(UserRepository userRepository,AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
        	.switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)))
            .flatMap(user ->
                authorityRepository.findByUserId(user.getId())
                    .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                    .collectList()
                    .map(authorities ->
                        User.withUsername(user.getUsername())
                            .password(user.getPassword())
                            .authorities(authorities) // ← DBの権限を反映
                            .build()
                    )
            );
    }

}

