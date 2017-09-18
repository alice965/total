package org.itbank.app.controllers;

import java.sql.SQLException;
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

import org.itbank.app.models.BoardDaoMyBatis;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDaoMyBatis bDAO;

	@RequestMapping("/list")
	public ModelAndView boardListHandle() throws SQLException {
		List<Map> list = bDAO.readAll();
		ModelAndView mav = new ModelAndView("t_expr");
		// mav.setViewName("t_board_list");
		// mav.setViewName("tw_°Ô½ÃÆÇ/board/list");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
		mav.addObject("cnt", list.size());
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
