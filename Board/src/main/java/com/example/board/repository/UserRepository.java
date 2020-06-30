package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.board.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	
	//이메일 정보로 아이디 조회
	public User findByEmail(String email);

	public User findByEmailAndPwd(String email, String pwd);
	
	
}