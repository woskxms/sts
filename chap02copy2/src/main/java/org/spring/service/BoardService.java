package org.spring.service;

import java.util.List;

import org.spring.domain.VO;

public interface BoardService extends GenericService<Integer, VO> {
    // BoardService에 특화된 메소드들을 필요에 따라 추가할 수 있습니다.
	public List<VO> listAll();
	
	void updateViewCnt(Integer key);
}
