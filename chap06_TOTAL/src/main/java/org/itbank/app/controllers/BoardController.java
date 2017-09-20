package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.itbank.app.controller.ws.AlertWSHandler;
import org.itbank.app.models.BoardDaoMyBatis;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDaoMyBatis bDAO;
	
	@Autowired
	AlertWSHandler aws;

	@RequestMapping("/list")
	public ModelAndView boardListHandle(@RequestParam(name="page", defaultValue="1" ) int page) throws SQLException {
		List<Map> listAll = bDAO.readAll();
		int psize = bDAO.countListPage();
		int size = psize/5;
			if(psize%5 >0)
				size++;
			
		Map p = new HashMap();
			p.put("start", (page-1)*5+1);
			p.put("end", page*5);
		
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "board/list");
		mav.addObject("list", bDAO.listPage(p));
		mav.addObject("listAll", listAll);
		mav.addObject("cnt", listAll.size());
		mav.addObject("size",size);
		return mav;
	}

	@GetMapping("/add")
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String boardAddGetHandle(Map map) {
		map.put("section", "board/add");
		return "t_expr";
	}

	@PostMapping("/add")
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String boardAddPostHandle(@RequestParam Map param, ModelMap map) throws SQLException {
		int rst = bDAO.createOne(param);
		if (rst == 1) {
			aws.sendMessage("게시판에 새로운 글이 등록되었습니다."); 
			aws.sendMessage("새로운 글이 등록되었습니다."); 
			map.put("section", "board/list");
			return "redirect:/board/list";
		}
		map.put("rst1", rst);

		return "/board/add_rst";
	}

	@RequestMapping(path = "/view/{num}")
	public ModelAndView boardAddPostHandle(@PathVariable String num) throws SQLException {
		ModelAndView mav = new ModelAndView("t_expr");
		Map one = bDAO.readOne(num);
		mav.addObject("one", one);
		mav.addObject("section", "board/view");
		return mav;
	}
}
