package org.itbank.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.controller.ws.LoginUserWsHandler;
import org.itbank.app.models.MemoDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/memo")
public class MemoController {
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MemoDaoMyBatis memoDao;
	@Autowired
	LoginUserWsHandler lws;

	@GetMapping("/send")
	public String addGetHanle(Map map) {
		map.put("section", "memo/send");
		return "t_expr";
	}

	@PostMapping("/send")
	public String addPost(@RequestParam Map param, Map model, HttpSession session) {
		try {
			param.put("sender", session.getAttribute("auth_id"));
			int r = memoDao.sendOne(param);
			model.put("rst", r);
			String msg = String.format("{\"mode\":\"memo\",\"sender\":\"%s\"}", session.getAttribute("auth_id"));
			lws.sendMessageToUser((String) param.get("sender"), msg);
		} catch (Exception e) {
			model.put("rst", -1);
		}
		model.put("section", "memo/send");
		return "t_expr";
	}
	
}
