package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.dao.ReplyDAO;
import com.sist.vo.GoodsVO;
import com.sist.vo.ReplyVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	@Autowired
	private ReplyDAO rDao;
	
	@GetMapping("goods/list.do")
	public String goods_list(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		List<GoodsVO> list=dao.goodsListData(start, end);
		int totalpage=dao.goodsTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("main_jsp", "../goods/list.jsp");
		return "main/main";
	}
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookie=new Cookie("goods_"+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("no", no);
		return "redirect:../goods/detail.do";
	}
	@GetMapping("goods/detail.do")
	public String goods_detail(int no,Model model) {
		GoodsVO vo=dao.goodsDetailData(no);
		List<ReplyVO> rList=rDao.replyListData(no, 1);
		model.addAttribute("vo", vo);
		model.addAttribute("rList", rList);
		model.addAttribute("main_jsp", "../goods/detail.jsp");
		return "main/main";
	}
	@RequestMapping("goods/find.do")
	public String goods_find(String page,String colname,String ss,Model model) {
		if(page==null) page="1";
		if(colname==null) colname="goods_name";
		if(ss==null) ss="";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		Map map=new HashMap();
		map.put("col_name", colname);
		map.put("ss", ss);
		map.put("start", start);
		map.put("end", end);
		List<GoodsVO> list=dao.goodsFindData(map);
		int totalpage=dao.goodsFindTotalPage(map);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("colname", colname);
		model.addAttribute("ss", ss);
		model.addAttribute("main_jsp", "../goods/find.jsp");
		return "main/main";
	}
}
