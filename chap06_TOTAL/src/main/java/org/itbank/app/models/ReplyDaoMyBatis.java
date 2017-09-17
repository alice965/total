package org.itbank.app.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int createOne(Map map) {
		return template.insert("reply.createOne", map);
	}
	
	public List<Map> readSome(String parent) {
		return template.selectList("reply.readSome", parent);
	}
}
