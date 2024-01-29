package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ReplyMapper;
import com.sist.vo.ReplyVO;
/*
 	*.do ======= DispatcherServlet
 	             | -> preHandle() -> 자동 로그인,ID 저장
 	             | HandlerMapping
 	             @Controller/@RestController
 	             | -> postHandle()
 	             | Model=request -> ViewResolver
 	             | -> afterCompletion() -> 권한
 	             JSP
 	AOP
 	void aaa(); -> Before
 	void bbb(); -> AfterThrowing
 	void ccc(); -> After
 	void ddd(); -> AfterReturning
 	
 	public String display(){
 		aaa();
 		try{
 			
 		}catch(Exception e){
 			bbb();
 		}finally{
 			ccc();
 		}
 		return "";
 		ddd();
 	}
 	
 	JOINPOINT : 호출위치 지정
 	POINTCUT : 대상 메소드
 	===================== ADVICE -> 여러개 ASPECT
 	
 	MVC
 	-> DI,AOP -> Annotation,XML
 	   ========================
 	   Annotation : 개발자 마다
 	   XML : 공통으로 사용하는 클래스,라이브러리
 */
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	public List<ReplyVO> replyListData(int fno,int typeno){
		return mapper.replyListData(fno,typeno);
	}
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	public void replyDelete(int no,int typeno) {
		mapper.replyDelete(no,typeno);
	}
}
