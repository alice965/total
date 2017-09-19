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
	public boolean addProfile(Map map) {
		System.out.println(map);
		sql.insert("member.addProfile", map);
		return true;
	}
	public Map latestProfile(String id) {
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
	
	public List<Map> list(){
		return sql.selectList("member.list");
	}
	
	public List<Map> listPage(Map map){
		return sql.selectList("member.listPage", map);
	}
	public int countListPage() {
		return sql.selectOne("member.countListPage");
	}
}
