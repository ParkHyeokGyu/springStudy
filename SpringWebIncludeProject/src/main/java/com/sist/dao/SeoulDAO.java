package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulMapper;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		mapper.seoulListData(map);
		return (List<SeoulVO>) map.get("pResult");
	}
	public int seoulLocationTotalPage(Map map) {
		mapper.seoulLocationTotalPage(map);
		return (Integer) map.get("pTotal");
	}
//	public int seoulLocationTotalPage() {
//		return (Integer)mapper.seoulLocationTotalPage();
//	}
}
