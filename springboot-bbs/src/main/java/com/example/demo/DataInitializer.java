package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import com.example.demo.entity.AuthorityEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repositoies.AuthorityRepository;
import com.example.demo.repositoies.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner init(DatabaseClient databaseClient,
                                  UserRepository userRepository,
                                  AuthorityRepository authorityRepository) {
        return args -> {
            // 1. テーブルを作成
            databaseClient.sql("CREATE TABLE IF NOT EXISTS users (" +
            		"id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "enabled BOOLEAN NOT NULL)")
                .then()
                .then(databaseClient.sql("CREATE TABLE IF NOT EXISTS authorities (" +
                    "id IDENTITY PRIMARY KEY, " +
                	"userId BIGINT NOT NULL," +
                    "authority VARCHAR(50) NOT NULL, " +
                    "CONSTRAINT fk_auth_users FOREIGN KEY(userid) REFERENCES users(id))")
                    .then())
                .subscribe();

            // 2. 初期データ投入
            userRepository.save(new UserEntity("user", "{noop}password", true))
            .flatMap(savedUser -> {
                // 保存後の savedUser には id が入っている
                AuthorityEntity authority = new AuthorityEntity(
                    null,                     // id（自動採番される）
                    savedUser.getId(),        // users.id をセット
                    "ROLE_USER"               // 権限
                );
                return authorityRepository.save(authority);
            })
            .subscribe();
        };
    }
}
