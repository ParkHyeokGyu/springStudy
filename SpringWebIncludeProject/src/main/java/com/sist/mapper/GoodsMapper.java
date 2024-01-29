package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_poster,goods_price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(goods_all ga_no_pk)*/no,goods_name,goods_poster,goods_price "
			+ "FROM goods_all)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(@Param("start") int start,@Param("end") int end);
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM goods_all")
	public int goodsTotalPage();
	@Update("UPDATE goods_all SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no,goods_name,goods_sub,goods_price,goods_discount,goods_first_price,goods_delivery,goods_poster,hit "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	@Select("SELECT no,goods_name,goods_poster,goods_price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(goods_all ga_no_pk)*/no,goods_name,goods_poster,goods_price "
			+ "FROM goods_all "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%')) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsFindData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12) "
			+ "FROM goods_all "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%'")
	public int goodsFindTotalPage(Map map);
	
	
}
