package com.sist.aop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
// DisptcherServlet을 통해 데이터를 매개변수로 받을 수 있는 클래스
// @Controller,@RestController -> Model에서만 받아볼 수 있다
@Aspect
@Component
public class FoodAspect {
	@Autowired
	private FoodDAO dao;
	
	@After("execution(* com.sist.web.MainController.main_main(..))")
	public void cookieSend() {
		HttpServletRequest request=(
				(ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())
		).getRequest();
		Cookie[] cookies=request.getCookies();
		List<FoodVO> cList=new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String fno=cookies[i].getValue();
					FoodVO vo=dao.foodCookieData(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cookiecount", cList.size());
		request.setAttribute("cList", cList);
	}
}
