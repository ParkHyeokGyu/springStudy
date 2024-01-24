package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
/*
 	Annotation의 역할 : 구분자
 	1. 메모리 할당 요청(선택적 Annotation)
 	   -> @Component
 	           | @ControllerAdvice @RestControllerAdvice
 	      -------------------------------------
 	      |            |         |            |
 	      @Repository  @Service  @Controller  @RestController
 	      -> DAO       -> BI     -> Model     -> Vue/React
 	2. DI(주입)
 	   -> @Autowired : 자동 주입(스프링에서 자동으로 주소값을 찾아서 주입해준다)
 	      @Inject
 	3. AOP(공통 모듈)
 	   -> @Aspect @Before @After...
 */
@Repository("fDao")
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(int start,int end){
		return mapper.foodListData(start, end);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
