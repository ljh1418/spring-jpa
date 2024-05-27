package com.example.demo.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	//@Autowired
	//private UserService userService;
	
	@GetMapping("/user/insertPage")
	public String insertPage() {
		return "user/insert";
	}
	
//	@PostMapping("/user/insert")
//	public String insert(@RequestParam("userEmail") String userEmail, 
//			@RequestParam("userPassword") String userPassword, 
//			@RequestParam("userName") String userName) {
//		System.out.println("userEmail:::" + userEmail);
//		System.out.println("userPassword:::" + userPassword);
//		System.out.println("userName:::" + userName);
//		return "home";
//	}
	
	//회원가입
	@PostMapping("/user/insert")
	public String insert(@ModelAttribute UserDTO userDTO) {
		System.out.println("userDTO:::" + userDTO);
		
		userService.insert(userDTO);
		return "user/login";
	}
	
	//로그인페이지 이동
	@GetMapping("/user/login")
	public String loginForm() {
		return "user/login";
	}
	
	//로그인 처리
	@PostMapping("/user/login")
	public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
		UserDTO loginResult =  userService.login(userDTO);
		System.out.println("loginResult ::: " + loginResult);
		if(loginResult != null) {
			//로그인성공
			//loginEmail에 loginResut.getUserEmail() 담기
			session.setAttribute("loginEmail", loginResult.getUserEmail());
			return "main";
		}else{
			//로그인실패
			return "login";
		}
	}
	
	
	@GetMapping("/user/list")
	public String findAll(Model model) {
		List<UserDTO> userDTOList = userService.findAll();
		// html로 가져갈 데이터가 있다면 model을 사용
		model.addAttribute("userList",userDTOList);
		return "user/list";
	}
	
	//경로상에있는 /user/{id} ->{id}를 @PathVariable 어노테이션을 통해 받음
	@GetMapping("/user/{id}")
	public String findById(@PathVariable Long id, Model model) {
		UserDTO userDTO = userService.findById(id);
		System.out.println("controller userDTO ::: " + userDTO);
		model.addAttribute("user",userDTO);
		return "user/detail";
	}
	
	//상세정보 페이지
	@GetMapping("/user/update")
	public String updateForm(HttpSession session, Model model) {
		//session.getAttribute return 타입이 Object라서 강제 형번환
		String myEmail = (String) session.getAttribute("loginEmail");
		UserDTO userDTO = userService.update(myEmail);
		model.addAttribute("updateUser",userDTO);
		return "user/update";
	}
	
	@PostMapping("/user/update")
	public String update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
		return "redirect:/user/" + userDTO.getId();
	}
	
}
