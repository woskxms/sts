package org.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class VO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
}
