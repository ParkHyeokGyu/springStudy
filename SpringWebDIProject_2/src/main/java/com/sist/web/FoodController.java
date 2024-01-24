package com.sist.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO fDao;
	// model : JSP에 데이터를 보내주는 전송 객체 <-> request와 비슷한 기능
	// request와 response가 사라지고 이 일련의 작업들을 DispatcherServlet가 해준다
	// 왜 request를 사용하지 않는가? -> 보안문제(IP)
	// request를 사용하지않기에 한글변환에 문제점이 생긴다 -> web.xml에 filter 등록
	// 예외 -> 쿠키 사용할시 필요 -> 쿠키 Annotation으로 바꿀수도 있다
	@RequestMapping("food/list.do")
	public String food_list(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		List<FoodVO> list=fDao.foodListData(start, end);
		int totalpage=fDao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "food/list";
	}
	@RequestMapping("food/detail.do")
	public String food_detail(int fno,Model model) {
		FoodVO vo=fDao.foodDetailData(fno);
		model.addAttribute("vo",vo);
		/*
		 	class Model{
		 		private HttpServletRequest request;
		 		
		 		public Model(HttpServletRequest request){
		 			this.request=request;
		 		}
		 		
		 		public void addAttribute(String key,Object obj){
		 			request.setAttribute(key,obj);
		 		}
		 	}
		 */
		return "food/detail";
	}
	@RequestMapping("food/find.do")
	public String food_find() {
		return "food/find";
	}
}
