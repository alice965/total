package org.itbank.app.controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import org.itbank.app.models.MarketDaoMyBatis;


@Controller
@RequestMapping("/market")
public class MarketController {
	
	@Autowired
	MarketDaoMyBatis mDAO;
	
	@GetMapping("/add")
	public String addGetHandle(Map map) {
		map.put("section", "market/add");
		return "t_m_expr";
	}
	
	@PostMapping("/add")
	public String addPostHandle(@RequestParam Map param, ModelMap map, HttpSession session) {
		param.put("id", session.getAttribute("auth_id"));
		int rst = mDAO.addProduct(param);
		if(rst== 1) {
			map.put("section", "market/list");
			return "redirect:/market/list";
		}
		map.put("rst", rst);
		return "market/list";
	}
	
	@RequestMapping("/list")
	   public ModelAndView readAllList(HttpSession session){
		  List<Map> list = mDAO.listProduct();
		  int psize = mDAO.countListPage();
			int size = psize/5;
				if(psize%5 >0)
					size++;
		  
		  
	      ModelAndView mav = new ModelAndView("t_m_expr");
	      mav.addObject("list", list);
	      mav.addObject("cnt", list.size());
	      mav.addObject("section", "market/list");
	      mav.addObject("size",size);
	      return mav;
	   }
	
	@GetMapping("/search")
	public ModelAndView allHandle(@RequestParam Map param) {
		System.out.println(param);
		ModelAndView mav = new ModelAndView("market/list");
		mav.addObject("list", mDAO.searchSome(param));
		return mav;
	}
	@RequestMapping(path = "/view/{num}")
	public ModelAndView boardAddPostHandle(@PathVariable String num) throws SQLException {
		ModelAndView mav = new ModelAndView("t_m_expr");	
		Map pmap = mDAO.readOne(num);
		mav.addObject("pmap", pmap);
		mav.addObject("section", "market/view");
		return mav;
}
}
