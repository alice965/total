package org.itbank.app.models;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoMyBatis {
	@Autowired
	SqlSessionTemplate sql;

	// �����ͺ��̽� ���
	public boolean addOne(Map map) {
		sql.insert("member.addBasic", map);
		sql.insert("member.addDetail", map);
		return true;
	}

	// id�� email�� �Ӱ�, pass�� ���� �����Ͱ� �ִ��� Ȯ���Ҷ�
	public int existOne(Map map) {
		return sql.selectOne("member.checkByIdmailAndPass", map);
	}

	// id�� email�� �Ӱ�,
	public HashMap readOneByIdOrEmail(String idmail) {
		return sql.selectOne("member.readOneByIdOrEmail", idmail);
	}

	// id�� email�� �Ӱ�, pass�� ���� �����Ͱ� �ִ��� Ȯ���Ҷ�
	public HashMap readOneById(String id) {
		return sql.selectOne("member.readOneById", id);
	}
}
