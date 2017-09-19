package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	@Autowired
	MemberDaoMyBatis memberDao;

	@RequestMapping({ "/", "/index" })
	public ModelAndView rootHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
			mav.addObject("section", "index");
		return mav;
	}
	
	@RequestMapping({ "/chat" })
	public ModelAndView chatHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
			mav.addObject("section", "chat");
		return mav;
	}
} 
