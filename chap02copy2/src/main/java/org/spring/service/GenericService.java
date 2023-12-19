package org.spring.service;

import org.spring.domain.VO;

public interface GenericService<K, V> {
	// pk로 특정 튜플 반환
	public VO get(K key);
	
	//릴레이션에 튜플 추가
	public int register(VO vo);
	
	//튜플 수정
	public int modify(VO vo);
	
	//튜플 삭제
	public int remove(K key);

	
	
}
