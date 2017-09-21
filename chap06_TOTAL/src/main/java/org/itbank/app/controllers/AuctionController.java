package org.itbank.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itbank.app.controller.ws.LoginUserWsHandler;
import org.itbank.app.models.AuctionDaoMyBatis;
import org.itbank.app.models.MarketDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	ObjectMapper mapper;
	@Autowired
	LoginUserWsHandler lws;
	@Autowired
	AuctionDaoMyBatis aDAO;
	
	@RequestMapping(path = "/add", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String replyAddAuctionHandle(@RequestBody String body, 
			HttpServletResponse resp, ModelMap data, HttpSession session,
			@CookieValue(name = "bidlimit",required=false) String val)
			throws Exception {
		System.out.println(body);
		int code = 0 ;
		if(val != null) {	// 비딩제한 쿠키가 있을 때
			code = -1;		// 뷰에서 result 읽어서 쓰기 제한
		}else {				// 비딩제한 쿠키가 없을 때
			Map map = mapper.readValue(body, Map.class);
				map.put("bid", session.getAttribute("auth_id"));
				System.out.println("bid map : " + map);
			int r = aDAO.addAuction(map);
			data.put("rst", r);
			String msg = String.format("{\"mode\":\"auction\",\"bidder\":\"%s\"}", session.getAttribute("auth_id"));
			lws.sendMessageToUser((String) map.get("bid"), msg);
			if(r == 1) {
				code =1;
				Cookie c = new Cookie("bidlimit", "on");
				c.setPath("/");
				c.setMaxAge(30);
				resp.addCookie(c);
			}
		}
		return "{\"result\": " +code +" }";
	}
	
	@RequestMapping(path = "/list/{pno}", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String replyListHandle(@PathVariable String pno) throws Exception {
		List<Map> r = aDAO.listAuction(pno);
		return mapper.writeValueAsString(r);
	}
}
