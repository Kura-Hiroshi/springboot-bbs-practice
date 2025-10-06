package com.example.demo;

import org.springframework.stereotype.Service;

import com.example.demo.repositoies.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    // ユーザー追加
//    public Mono<UserEntity> createUser(String username, String password) {
//        UserEntity user = new UserEntity(username, "{noop}" + password, true);
//        return userRepository.save(user);
//    }
//
//    // ユーザー情報更新
//    public Mono<UserEntity> updateUser(UserEntity user) {
//        return userRepository.save(user);
//    }
//
//    // ユーザー削除
//    public Mono<Void> deleteUser(String username) {
//        return userRepository.deleteById(username);
//    }
//
//    // ユーザー取得
//    public Mono<UserEntity> findByUsername(String username) {
//        return userRepository.findById(username);
//    }
}
