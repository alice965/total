package org.itbank.app.models;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoMyBatis {
	
	@Autowired
	SqlSessionTemplate template;
	
	public boolean addOne(Map map) {
		template.insert("member.addBasic", map);
		template.insert("member.addDetail", map);
		return true;
	}
	
	public int existOne(Map map) {
		return template.selectOne("member.checkByIdmailAndPass", map);
	}
	
	public HashMap readOne(Map map) {
		return template.selectOne("member.readOne", map);
	}
	public HashMap getDetail(HttpSession session) {
		return template.selectOne("member.getDetail", session);
	}
	//public int checkValid(Map map) {
	//	return template.insert("member.checkValid", map);
//	}

}
