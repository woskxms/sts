package org.spring.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory ssf;
	
	@Test
	public void test1() {
		try {
			Connection con = ds.getConnection();
			log.info(con);
		} catch (Exception e) {
			log.error(e);
		}
	}
	@Test
	public void test2() {
		try {
			SqlSession session = ssf.openSession(); 
			Connection con=session.getConnection();
			log.info(session);
			log.info(con);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
