package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
/*
 	1. MVC동작
 	   web.xml에 DisptcherServlet을 등록
 	   -> HandlerMapping(Model 찾기),WebApplicationContext(클래스 관리) -> 자동 생성
 	                               -----------------------
 	                               DisptcherServlet안에 내장되어있다
 	                               -> 주입시 getBean()보다 @Autowired사용
 	                                  -> 끌어와서 사용하는것보다 Annotation사용
 	2. 공통 예외처리 : @ControllerAdvice
 	3. 인터셉터 : preHandle(),afterCompletion()
 	4. 메모리 할당 : Annotation
 	5. AOP
 	6. Cookie/Session
 	7. RestController -> JSON
 	--------------------------------------------------------------------------
 	8. 고급
 	   Validation,WebSocket,Security,Task(Betch)
 	   Spring-Data
 */
@Controller
public class MainController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("main/main.do")
	public String main_main(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		List<FoodVO> list=dao.foodListData(start, end);
		int totalpage=dao.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		// 쿠키를 AOP로
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "home.jsp");
		return "main/main";
	}
}
