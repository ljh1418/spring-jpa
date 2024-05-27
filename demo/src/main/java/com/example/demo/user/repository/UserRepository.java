package com.example.demo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.user.entity.UserEntity;


//JpaRepository<UserEntity, Long> -> 엔티티클래스의 PK가 어떤 타입인지 @Id 어노테이션 타입
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	//이메일로 회원 정보 조회 -> SELECT * FROM USER_TB
	Optional<UserEntity> findByUserEmail(String userEmail);
	
	
	
	
}
