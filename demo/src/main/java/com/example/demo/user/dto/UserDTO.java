package com.example.demo.user.dto;

import com.example.demo.user.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@NoArgsConstructor //기본생성자 자동으로 만들어줌
@AllArgsConstructor //필드를 매개변수로 하는 생성자를 만들어줌
@ToString
public class UserDTO {
	private Long id;
	private String userEmail;
	private String userPassword;
	private String userName;
	
	//entitiy >- dto 객체로 변환
	public static UserDTO toUserDTO(UserEntity userEntyti) {
		System.out.println("userEntyti ::: " + userEntyti);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntyti.getId());
		userDTO.setUserEmail(userEntyti.getUserEmail());
		userDTO.setUserName(userEntyti.getUserName());
		userDTO.setUserPassword(userEntyti.getUserPassword());
		
		return userDTO;
	}
	
/*	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNmae() {
		return userNmae;
	}
	public void setUserNmae(String userNmae) {
		this.userNmae = userNmae;
	}
*/	
	
}
