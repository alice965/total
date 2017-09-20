package org.itbank.app.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int addProduct(Map map) {
		return template.insert("product.addProduct", map);
	}
	
	public List<Map> listProduct() {
		return template.selectList("product.listProduct");
	}
	public int countListPage() {
		return template.selectOne("product.countListPage");
	}

	public List<Map> searchSome(Map p) {
		return template.selectList("product.searchBySprice", p);
	}
	
	public Map readOne(String num) {
		return template.selectOne("product.readOne", num);
	}

}
