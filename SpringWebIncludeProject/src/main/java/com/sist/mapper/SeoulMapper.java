package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.SeoulVO;

public interface SeoulMapper {
	/*
	 	CREATE OR REPLACE PROCEDURE seoulLocationData(
		    pStart NUMBER,
		    pEnd NUMBER,
		    pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		    OPEN pResult FOR
		        SELECT no,title,poster,msg,address,hit,num
		        FROM (SELECT no,title,poster,msg,address,hit,rownum as num
		        FROM (SELECT no,title,poster,msg,address,hit 
		        FROM seoul_location
		        ORDER BY no))
		        WHERE num BETWEEN pStart AND pEnd;
		END;
		/
	 */
	@Select(value = 
			"{CALL seoulLocationData("
			+ "#{pStart,mode=IN,javaType=java.lang.Integer},"
			+ "#{pEnd,mode=IN,javaType=java.lang.Integer},"
			+ "#{pResult,mode=OUT,jdbcType=CURSOR,resultMap=seoulMap}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	/*
	 	CREATE OR REPLACE PROCEDURE seoulLocationTotalPage(
		    pTotal OUT NUMBER
		)
		IS
		BEGIN
		    SELECT CEIL(COUNT(*)/12)
		    INTO pTotal
		    FROM seoul_location;
		END;
		/
	 */
	// 값을 넣어주는 변수에는 javaType
	// 값을 받는 변수에는 jdbcType
	// int가 아니라 Integer로 받아야한다
	@Select(value = 
			"{CALL seoulLocationTotalPage("
			+ "#{pTotal,mode=OUT,jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulLocationTotalPage(Map map);
	
	/*
	 	<select id="seoulLocationTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12)
			FROM seoul_location
		</select>
	 */
//	public int seoulLocationTotalPage();
}
