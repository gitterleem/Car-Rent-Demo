package at.gitterleem.Flex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	@GetMapping("/index")
	public String showIndex() {
		return "index";
	}

}
