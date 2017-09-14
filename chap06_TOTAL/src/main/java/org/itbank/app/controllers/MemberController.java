package org.itbank.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my")
public class MemberController {
	@Autowired
	MemberDaoMyBatis mDAO;
	
	@GetMapping("/info")
	public String infoHandle(HttpSession session, ModelMap map ) {
		Map data = mDAO.getDetail(session);
		map.put("section", "member/info");
		map.put("map", data);
			
		return "t_expr";
	}

}
