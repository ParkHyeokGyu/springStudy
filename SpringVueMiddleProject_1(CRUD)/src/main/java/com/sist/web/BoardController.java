package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// 화면 변경 -> forward(request전송)/sendRedirect(재호출)
//            경로명/파일명           redirect:.do -> request 초기화
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.BoardDAO;
@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list.do")
	public String board_list() {
		return "board/list";
	}
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	@GetMapping("board/detail.do")
	public String board_detail(int no,Model model) {
		model.addAttribute("no", no);
		return "board/detail";
	}
	@GetMapping("board/update.do")
	public String board_update(int no,Model model) {
		model.addAttribute("no", no);
		return "board/update";
	}
}
