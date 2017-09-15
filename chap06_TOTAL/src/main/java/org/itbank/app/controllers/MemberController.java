package org.itbank.app.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.itbank.app.models.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MemberController {
	@Autowired
	MemberDaoMyBatis memberDao;
	@Autowired
	ServletContext application;
	@Autowired
	SimpleDateFormat sdf;

	@GetMapping("/profile")
	public ModelAndView profileHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		

		mav.addObject("section", "my/profile");
		return mav;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/profile")
	public ModelAndView profilePostHandle(@RequestParam(name="profile") MultipartFile f, 
																			HttpSession session) 
					throws InterruptedException {
		System.out.println(application.getRealPath("/profiles"));
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		String fmt = sdf.format(System.currentTimeMillis());
		String fileName = id+"_"+fmt;
		File dst = new File(application.getRealPath("/profiles"), fileName);
		boolean rst = false;
		try {
			f.transferTo(dst);
			rst = !rst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/my/profile");
		mav.addObject("rst", rst);
		return mav;
	}

	@GetMapping("/info")
	public String infoHandle(Model model, HttpSession session) {
		Map map = (Map) session.getAttribute("auth");
		model.addAttribute("section", "my/info");
		return "t_expr";
	}

	@PostMapping("/info")
	public String infoPostHandle() {
		return "t_expr";
	}

}
