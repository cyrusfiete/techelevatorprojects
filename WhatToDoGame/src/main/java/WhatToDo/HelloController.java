package WhatToDo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class HelloController {

	@RequestMapping("/")
	public String runGame() {
		
		return "whatToDo";
	}
}
