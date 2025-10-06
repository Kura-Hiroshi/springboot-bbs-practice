package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("authorities")
public class AuthorityEntity {
    @Id
    private Long id; // PK用
    
    @Column("userid")
    private Long userId;
    //private String username;
    private String authority;
    
    
	//コンストラクタ
	public AuthorityEntity() {
		super();
	}
	
	
	public AuthorityEntity(Long userId, String authority) {
		super();
		this.userId = userId;
		this.authority = authority;
	}

	
	public AuthorityEntity(Long id, Long userId, String authority) {
		super();
		this.id = id;
		this.userId = userId;
		this.authority = authority;
	}



	//ゲッターとセッター
	
	
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

    
}