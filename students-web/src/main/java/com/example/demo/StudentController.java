package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.Students;

@Controller
public class StudentController {
	@PostMapping("/student")
	public ModelAndView student(@RequestBody String name)
	{
		Students db = new Students();
		db.doConnection();
		String[][] solution = db.getStudentData(name.split("=")[1]);
		db.closeConnection();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student");        
		mav.addObject("data", solution);  
		return mav;
	}
	@PostMapping("/subject")
	public ModelAndView subject(@RequestBody String name)
	{
		Students db = new Students();
		db.doConnection();
		String[][] solution = db.getSubjectData(name.split("=")[1].replace("+", " "));
		db.closeConnection();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("subject");        
		mav.addObject("data", solution);  
		return mav;
	}
}
