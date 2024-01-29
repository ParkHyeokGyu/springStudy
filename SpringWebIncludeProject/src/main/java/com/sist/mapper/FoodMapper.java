package com.sist.mapper;
/*
 	1. web.xml
 	   DispatcherServlet 등록
 	   한글 변환
 	   보안등록
 	   에러처리
 	2. 클래스 제작 -> 메모리 할당,주입
 	3. Spring -> xml 설정
 	4. mapper -> dao -> model
 	5. jsp
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FoodVO;

public interface FoodMapper {
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_menu_house "
			+ "ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM food_menu_house")
	public int foodTotalPage();
	@Update("UPDATE food_menu_house SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void hitIncrement(int fno);
	@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat,content "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	// 검색 -> 업체명,주소,음식종류
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_menu_house "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%' "
			+ "ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM food_menu_house "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%'")
	public int foodFindTotalPage(Map map);
}
