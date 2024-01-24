package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

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
		int count=dao.foodTotalCount();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		count=count-((curpage*rowSize)-rowSize);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "home.jsp");
		return "main/main";
	}
}
