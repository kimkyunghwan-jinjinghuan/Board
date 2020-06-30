package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.board.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	
	//이메일 정보로 아이디 조회
	public User findByEmail(String email);  //SQL문 SELECT  FROM   WHERE

	public User findByEmailAndPwd(String email, String pwd);

	
	
}


/*
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String username);
}

List<Member> member = memberRepository.findByName("hello")

// 실행된 SQL
SELECT * FROM MEMBER M WHERE M.NAME = "hello"


*/