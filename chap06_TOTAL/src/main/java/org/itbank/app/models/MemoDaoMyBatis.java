package org.itbank.app.models;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemoDaoMyBatis {
	
	@Autowired
	SqlSessionTemplate template;
	
	public int sendOne(Map map) {
		return template.insert("memo.sendOne", map);
	}

}
