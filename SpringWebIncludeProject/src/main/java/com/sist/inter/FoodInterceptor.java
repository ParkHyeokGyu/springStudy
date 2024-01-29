package com.sist.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
// <bean> 이용 -> 로그인 자동처리
public class FoodInterceptor extends HandlerInterceptorAdapter {
	/*
	 	              FrontController : 요청 -> 응답
	 	main.do ===== DispatcherServlet ===== HandlerMapping
	 										  | @GetMapping/@PostMapping
	 				  preHandle	                -> Model을 찾아준다
	 				  -> HandlerMapping이           
	 				     Model을 찾기전에 수행      -> @GetMapping("main.do")
	 				                                 |
	 				                              return "main/main"
	 				                                 | -> postHandle
	 				                              viewResolver
	 				                                 | request
	 				                                 | -> afterCompletion(권한 처리)
	 				                                JSP
	 	Model(Controller,Action)
	 	1) VO
	 	2) DAO
	 	3) Manager
	 	4) Service
	 	-> 유지보수,역할분담			                                                        
	 */
	// main.do 찾기전(Model 수행전) -> 자동 로그인 처리,ID 저장
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===== preHandle() Call =====");
		return super.preHandle(request, response, handler);
	}
	// viewResolver로 넘어가기전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===== postHandle() Call =====");
		super.postHandle(request, response, handler, modelAndView);
	}
	// JSP로 넘어가기전
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===== afterCompletion() Call =====");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
