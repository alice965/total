package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.itbank.app.models.MemberDaoMyBatis;

@Controller
public class IndexController {
	@Autowired
	MemberDaoMyBatis memberDao;

	@RequestMapping({ "/", "/index" })
	public String rootHandle() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String loginHandle() {
		return "t_login";
	}

	@RequestMapping("/session")
	public String sessionHandle(@RequestParam Map param, Model model, HttpSession session) throws SQLException {
		HashMap t = memberDao.readOne(param);
		if (t != null) {
			session.setAttribute("auth", t);
			return "redirect:/";
		} else {
			model.addAttribute("temp", param);
			return "login";
		}
	}
}
