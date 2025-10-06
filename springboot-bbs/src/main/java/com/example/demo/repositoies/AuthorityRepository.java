package com.example.demo.repositoies;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AuthorityEntity;

import reactor.core.publisher.Flux;

@Repository
public interface AuthorityRepository extends ReactiveCrudRepository<AuthorityEntity, Long> {
	
	Flux<AuthorityEntity> findByUserId(Long userId);

}
