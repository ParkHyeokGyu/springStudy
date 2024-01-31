package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

// 자바스크립트와 연동 -> 데이터를 받아 처리후 결과값 전송
// @ResponseBody -> 변경
// RestFul -> GET/POST/PUT/DELETE
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value = "board/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_list(int page) throws Exception {
		int rowSize=10;
		int start=(page*rowSize)-(rowSize-1);
		int end=page*rowSize;
		
		List<BoardVO> list=dao.boardListData(start, end);
		// jackson -> json을 자동으로 생성해주는 라이브러리
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	@GetMapping(value = "board/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_page(int page) throws Exception {
		Map map=new HashMap();
		int totalpage=dao.boardTotalPage();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	@PostMapping("board/insert_ok.do")
	public void board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
	}
	@GetMapping(value = "board/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_detail(int no) throws Exception {
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	@GetMapping(value = "board/update_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_update(int no) throws Exception {
		BoardVO vo=dao.boardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo) {
		String result=dao.boardUpdate(vo);
		return result;
	}
	@GetMapping("board/delete_vue.do")
	public String board_delete(int no,String pwd) {
		System.out.println(no);
		System.out.println(pwd);
		String result=dao.boardDelete(no, pwd);
		return result;
	}
}
