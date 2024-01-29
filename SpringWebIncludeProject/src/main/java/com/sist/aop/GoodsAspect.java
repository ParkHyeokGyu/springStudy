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

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Aspect
@Component
public class GoodsAspect {
	@Autowired
	private GoodsDAO dao;
	
	@After("execution(* com.sist.web.GoodsController.goods_list(..))")
	public void cookieSend() {
		HttpServletRequest request=(
				(ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())
		).getRequest();
		Cookie[] cookies=request.getCookies();
		List<GoodsVO> cList=new ArrayList<GoodsVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("goods_")) {
					String no=cookies[i].getValue();
					GoodsVO vo=dao.goodsCookieData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cookiecount", cList.size());
		request.setAttribute("cList", cList);
	}
}
