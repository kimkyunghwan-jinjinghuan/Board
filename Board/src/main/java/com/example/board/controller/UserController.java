package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.model.User;
import com.example.board.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpSession session;
	
	

	@GetMapping("/signup")
	public String signup() {
		return "signup";  
	}

	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		System.out.println(user);
		// 중복아이디 가입불가를 위해서 가입여부 확인, 이메일를 통해 id을 알아낼 수 있게
		User findUser = userRepository.findByEmail(user.getEmail()); // 모델어트리뷰터에서 원하는 거 꺼내서
		if (findUser == null) {
			userRepository.save(user);
			return "alert/index";
		} else {
			return "alert/signDuplicationID"; //
		}
		
	}

	
	
	

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user) {
		User dbUser = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		if (dbUser != null) {
			session.setAttribute("user_info", dbUser); // 있으면 세션에 인자들이 담기고, 서버측에서는 session정보 언제든지 참조가능
		} 
			return "redirect:/signin";
		
	}
	
	
	@GetMapping("/signout")
	public String signout() {
		session.removeAttribute("user_info"); //저장된 세션값만 삭제 
//		session.invalidate(); //세션의 모든정보 삭제
		return "redirect:/";
	}
	
	
	
	
	
	
	
}
