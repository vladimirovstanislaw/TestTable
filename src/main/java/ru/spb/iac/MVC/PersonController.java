package ru.spb.iac.MVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {

	@RequestMapping(path="/home")
	public String goHome() {
		return "index";
	}
}
