package com.spaneos.dhi.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	@GetMapping("/hello")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Check your luck");
		model.addObject("message", "Check your luck");
		model.setViewName("hello");
		return model;

	}
}
