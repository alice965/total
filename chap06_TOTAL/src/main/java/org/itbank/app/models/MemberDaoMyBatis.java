package org.itbank.app.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoMyBatis {
	@Autowired
	SqlSessionTemplate sql;

	public boolean addOne(Map map) {
		sql.insert("member.addBasic", map);
		sql.insert("member.addDetail", map);
		return true;
	}
	public boolean addProfile(String id) {
		sql.insert("member.addProfile", id);
		return true;
	}
	public int latestProfile(String id) {
		return sql.selectOne("member.latestProfile", id);
	}
	public List<Map> listProfile(String id){
		return sql.selectList("member.listProfile", id);
	}
	

	public int existOne(Map map) {
		return sql.selectOne("member.checkByIdmailAndPass", map);
	}

	public HashMap readOneByIdOrEmail(String idmail) {
		return sql.selectOne("member.readOneByIdOrEmail", idmail);
	}

	public HashMap readOneById(String id) {
		return sql.selectOne("member.readOneById", id);
	}
	
}
