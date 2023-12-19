package org.spring.service;

import java.util.List;

import org.spring.domain.VO;
import org.spring.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	
	private final BoardMapper mapper; // 수정된 부분
	
	
	@Override
	public List<VO> listAll(){
		log.info("listAll()");
		return mapper.selectAll();
	}
	
	@Override
	public VO get(Integer PK) {
		
		return null;
	}

	@Override
	public int register(VO vo) {
		mapper.insert(vo);
		log.info(vo);
		return mapper.maxBno();
	}

	@Override
	public int modify(VO vo) {
		if(mapper.update(vo) != 0)return 1;
		else return 0;
	}

	@Override
	public int remove(Integer PK) {
		if(mapper.delete(PK) != 0)return 1;
		else return 0;
	}
	
	@Override
	public void updateViewCnt(Integer PK) {
        mapper.updateViewCnt(PK);
    }
}	
