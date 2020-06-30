package com.example.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.model.Board;
import com.example.board.model.User;
import com.example.board.repository.BoardRepository;

@RequestMapping("/board") // 하나의 주소로 만들어주는거 /board랑 /write합쳐서 => /board/write로 주소가 지정된다
@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	HttpSession session;

	@GetMapping("/write") // board패키지 만들어주고 "/board/wrtie
	public String boardWrite() {
		return "board/write";
	}

	@PostMapping("/write")
	public String boardWritePost(@ModelAttribute Board board) {
		System.out.println(board);
		User user = (User) session.getAttribute("user_info"); //session에 값이 있는지 여부 확인
		
		System.out.println(user);
		
		if(user == null) {
			return "alert/writeAfterSign";
		} else if (board.getTitle() == null || board.getContent()==null) {
			return "alert/writeTitleContentNull";
		} else {
			String userId = user.getEmail();
			board.setUserId(userId);
			boardRepository.save(board);
			return "board/write";
		}
		
		
	}
	// 로그인없이 게시판 사용할때 로그인부터 하고 오세요라고 한다 (session에 user_info가 없으니
	// NullPointerException나오고)
	// 인터셉터를 쓰던가 null인경우 loginError.html로 보내서 alert창을 띄워주기

	@GetMapping("/board")
	public String board(Model model) {
		List<Board> list = boardRepository.findAll();
		model.addAttribute("list", list);
		return "board/list";
	}

}