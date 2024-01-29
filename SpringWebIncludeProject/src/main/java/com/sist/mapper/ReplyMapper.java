package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ReplyVO;

public interface ReplyMapper {
	// 추가
	// before=true -> insert문 수행하기전에 먼저 수행
	@SelectKey(keyProperty = "no",resultType = int.class,before = true,
			statement = "SELECT NVL(MAX(no)+1,1) as no FROM springReply")
	// #{no}에 증가된 no값이 첨부된다
	@Insert("INSERT INTO springReply VALUES "
			+ "(#{no},#{fno},#{id},#{name},#{msg},SYSDATE,#{typeno})")
	public void replyInsert(ReplyVO vo);
	// 목록
	@Select("SELECT no,fno,typeno,id,name,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,msg "
			+ "FROM springReply "
			+ "WHERE fno=#{fno} AND typeno=#{typeno} "
			+ "ORDER BY no DESC")
	public List<ReplyVO> replyListData(@Param("fno") int fno,@Param("typeno") int typeno);
	// 수정
	@Update("UPDATE springReply SET "
			+ "msg=#{msg} "
			+ "WHERE no=#{no} AND typeno=#{typeno}")
	public void replyUpdate(ReplyVO vo);
	// 삭제
	@Delete("DELETE FROM springReply "
			+ "WHERE no=#{no} AND typeno=#{typeno}")
	public void replyDelete(@Param("no") int no,@Param("typeno") int typeno);
}
