package org.itbank.app.controllers;


import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.itbank.app.models.ReplyDaoMyBatis;


@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	ObjectMapper mapper;

	@Autowired
	ReplyDaoMyBatis replyDao;

	@RequestMapping(path = "/add", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String replyAddHandle(@RequestBody String body, 
			HttpServletResponse resp, 
			@CookieValue(name = "limit",required=false) String val)
			throws Exception {
		System.out.println(body);
		int code = 0 ;
		if(val != null) {	// 쿠키가 있다는 상황
			code = -1;
		}else {
			Map map = mapper.readValue(body, Map.class);
			int r = replyDao.createOne(map);
			if(r == 1) {
				code =1;
				Cookie c = new Cookie("limit", "on");
				c.setPath("/");
				c.setMaxAge(30);
				resp.addCookie(c);
			}
		}
		return "{\"result\": " +code +" }";
	}

	@RequestMapping(path = "/list/{parent}", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String replyListHandle(@PathVariable String parent) throws Exception {
		List<Map> r = replyDao.readSome(parent);
		return mapper.writeValueAsString(r);
	}
}




