package com.example.demo.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.user.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

//테이블 역활
@Entity //해당클래스가 JPA 엔티티 클래스라고 정의
@Setter
@Getter
@Table(name = "user_tb")
@ToString
public class UserEntity {
	
	
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) //unique 제약조건 추가
	private String userEmail;
	
	@Column
	private String userPassword;
	
	@Column
	private String userName;
	
	//변환
	public static UserEntity toUserEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		//DTO에 담긴걸 ENTITY 객체에 넘기는 작업
		userEntity.setUserEmail(userDTO.getUserEmail());
		userEntity.setUserName(userDTO.getUserName());
		userEntity.setUserPassword(userDTO.getUserPassword());
		return userEntity;
	}
	
	//id값 엔티티 추가 -> 업데이트용
	public static UserEntity toUpdateUserEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setUserEmail(userDTO.getUserEmail());
		userEntity.setUserName(userDTO.getUserName());
		userEntity.setUserPassword(userDTO.getUserPassword());
		return userEntity;
	}
	
}
