package org.itbank.app.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int createOne(Map map) {
		return template.insert("board.createOne", map);
	}
	
	public List<Map> readAll() {
		return template.selectList("board.readAll");
	}
	
	public Map readOne(String num) {
		return template.selectOne("board.readOne", num);
	}
	
	public List<Map> listPage(Map map){
		return template.selectList("board.listPage", map);
	}
	public int countListPage() {
		return template.selectOne("board.countListPage");
	}
	
	

}
