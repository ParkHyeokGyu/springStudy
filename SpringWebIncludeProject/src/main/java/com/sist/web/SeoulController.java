package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.SeoulDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/list.do")
	public String seoul_list(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		Map map=new HashMap();
		map.put("pStart", start);
		map.put("pEnd", end);
		
		List<SeoulVO> list=dao.seoulListData(map);
		int totalpage=dao.seoulLocationTotalPage(map);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../seoul/list.jsp");
		return "main/main";
	}
}
