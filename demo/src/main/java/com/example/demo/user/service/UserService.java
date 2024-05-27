package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	
	//@Autowired
	//private UserRepository userRepository;
	private final UserRepository userRepository;
	
	
	public void insert(UserDTO userDTO) {
		// repository의 save메서드 호출 (조건 -> entity객체를 넘겨줘야함)
		// 1. dto -> entity 변환
		// 2. repository의 save 메서드 호출
		
		//호출 이후 변환된 entity 객체 가져오기
		UserEntity userEntity =  UserEntity.toUserEntity(userDTO);
		System.out.println("userEntity:::" + userEntity);
		//save -> jpa 제공하는 메서드
		userRepository.save(userEntity);
		
	}


	public UserDTO login(UserDTO userDTO) {
		// 회원이 입력한 이메일로 DB에서 조회
		// DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
		
		// optional 객체로 받기
		Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());
		System.out.println("byUserEmail:::"+byUserEmail);
		log.info("byUserEmail:::"+byUserEmail);
	
		if(byUserEmail.isPresent()) {
			// 조회 결과가 있다
			UserEntity userEntity = byUserEmail.get(); //객체를 가져옴
			if(userEntity.getUserPassword().equals(userDTO.getUserPassword())) {
				// 비밀번호 일치
				// entity -> dto 객체로 변환 후 리턴
				UserDTO dto = UserDTO.toUserDTO(userEntity);
				return dto;
			}else {
				// 비밀번호	불일치
				return null;
			}
			
		}else {
			// 조회 결과가 없다
			return null;
		}
	}


	public List<UserDTO> findAll() {
		List<UserEntity> userEntityList = userRepository.findAll();
		// entity -> dto 객체의 형태로 컨트롤한테
		List<UserDTO> userDTOList = new ArrayList<>();
		for(UserEntity userEntity:userEntityList) {
			userDTOList.add(UserDTO.toUserDTO(userEntity));
			
			/*
			userDTOList.add(UserDTO.toUserDTO(userEntity)); -> 2줄로 하면 
			UserDTO userDTO = UserDTO.toUserDTO(userEntity);
			userDTOList.add(userDTO);
			*/
		}
		return userDTOList;
	}


	public UserDTO findById(Long id) {
		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
		System.out.println("optionalUserEntity ::: " + optionalUserEntity);
		if(optionalUserEntity.isPresent()) {
			//UserEntity userEntity = optionalUserEntity.get();
			//UserDTO userDTO = UserDTO.toUserDTO(userEntity);
			//return userDTO;
			System.out.println("optionalUserEntity.get():::"+optionalUserEntity.get());
			return UserDTO.toUserDTO(optionalUserEntity.get());
		}else {
			return null;
		}
	}


	public UserDTO update(String myEmail) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmail(myEmail);
		if(optionalUserEntity.isPresent()) {
			return UserDTO.toUserDTO(optionalUserEntity.get());
		}else {
			return null;
		}
	}


	public void update(UserDTO userDTO) {
		// id가 없으면 insert 쿼리를 실행
		// id가 있으면 update 쿼리를 실행
		userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
		
	}
}
