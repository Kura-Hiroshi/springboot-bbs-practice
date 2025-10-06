package com.example.demo;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class BbsConfiguration {

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		http
			.authorizeExchange((authorize) -> authorize
				
				.anyExchange().authenticated()
			)
			.formLogin(withDefaults());
		// @formatter:on
		return http.build();
	}

//	@Bean
//    MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//            .password(passwordEncoder.encode("password"))
//            .roles("USER")
//            .build();
//
//        return new MapReactiveUserDetailsService(user);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//    	return new BCryptPasswordEncoder(); // 推奨エンコーダー
//    }
}
