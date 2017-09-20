package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoginController {
	@Autowired
	MemberDaoMyBatis memberDao;
	
	@GetMapping("/login")
	public String loginHandle(Model model) {
		model.addAttribute("section", "login");
		return "t_expr";
	}
	
	@PostMapping("/login")
	public ModelAndView sessionHandle(@RequestParam Map param, HttpSession session) throws SQLException {
		ModelAndView mav = new ModelAndView();
		int t = memberDao.existOne(param);
		if (t == 1) {
			HashMap u = memberDao.readOneByIdOrEmail((String)param.get("idmail"));
			System.out.println(t);
			session.setAttribute("auth", u);
			session.setAttribute("auth_id", u.get("ID"));
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("t_expr");
			mav.addObject("section", "login");
			mav.addObject("temp", "temp");
			/*
			mav.setViewName("redirewct:/login");
			mav.addObject("mode", "f");
			*/
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutHandle(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		
		Cookie[] ar= request.getCookies();
		Map<String, String> ckmap = new HashMap<>();
		if(ar != null) {
			 for(Cookie c:ar) {
				 ckmap.put(c.getName(), c.getValue());
			 }
			 
			 if(ckmap.containsKey("auth")) {
				Cookie c = new Cookie("auth","1");
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
			 }
		}else {
			 System.out.println("ƒÌ≈∞ªË¡¶æ»µ ....");
		}
		
		ModelAndView mav = new ModelAndView("redirect:/");
		//mav.addObject("rst", rst);
		return mav;
	}
}





