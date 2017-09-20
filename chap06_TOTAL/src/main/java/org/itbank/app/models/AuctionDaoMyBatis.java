package org.itbank.app.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int addAuction(Map map) {
		return template.insert("auction.addAuction", map);
	}
	
	public List<Map> listAuction(String pno) {
		return template.selectList("auction.listAuction", pno);
	}

}
