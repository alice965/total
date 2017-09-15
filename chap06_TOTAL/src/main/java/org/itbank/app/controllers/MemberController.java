package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberDaoMyBatis mDAO;
	
	@Autowired
	ServletContext application;
	
	@GetMapping("/info")
	public String infoHandle(HttpSession session, ModelMap map ) {
		Map data = mDAO.getDetail(session);
		map.put("section", "member/info");
		map.put("map", data);
			
		return "t_expr";
	}
	@PostMapping("/info")
	@RequestMapping(path = "/info", method = RequestMethod.POST)
	public ModelAndView profilePostHandle(@RequestParam Map param, 
			@RequestParam(name="profile", required=false) MultipartFile f, HttpServletRequest request) throws InterruptedException {
		
		ModelAndView mav = new ModelAndView("t_expr");
		System.out.println("profilePostHandle.."+param);
		System.out.println("application: " + application.getRealPath("/temp"));
		Thread.sleep(10000);
		
		System.out.println("========파일정보===========");

		System.out.println("nick :  "+request.getParameter("nick"));
		System.out.println("toString : "+f.toString());
		System.out.println("isEmpty : "+f.isEmpty());
		System.out.println("getContentType : "+ f.getContentType());
		System.out.println("getName : " + f.getName());
		System.out.println("getOriginalFilename : " +f.getOriginalFilename());
		System.out.println("getSize : " + f.getSize());
		
		//transferTo(File f)...실제 업로드..getInputStream
		
		mav.addObject("section", "member/info");
		return mav;
	}

}
