package org.itbank.app.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public List<Map> readOnesFriend(String id){
		return template.selectList("friend.readOnesFriend", id);
	}
	
	public int addFriend(Map map) {
		return template.insert("friend.createOne", map);
	}

}
