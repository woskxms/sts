package org.spring.mapper;

import java.util.List;

import org.spring.domain.VO;

import lombok.Data;

public interface BoardMapper {

	public void insert(VO vo);
	
	public List<VO> selectAll();
	
	public int update(VO vo);
	
	public int delete(int bno);
	
	public void updateViewCnt(int bno);
	
	public int maxBno();
}
