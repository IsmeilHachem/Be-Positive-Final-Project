package co.grandcircus.bepositive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bepositive.pojos.DocumentResponse;

@Controller
public class PositiveController {

	@Autowired
	ApiService apiService;

	@RequestMapping("/")
	public ModelAndView showHome() {

		return new ModelAndView("redirect:/index");
	}

	@RequestMapping("/index")
	public ModelAndView showIndex() {

		return new ModelAndView("index");
	}

	@RequestMapping("/mainpage-show")
	public ModelAndView showMain() {

		return new ModelAndView("mainpage");
	}

	@RequestMapping("/api")
	public ModelAndView submitResponse(@RequestParam(value = "post", required = true) String text) {

		ModelAndView mv = new ModelAndView("API");
		DocumentResponse response = apiService.search(text);
		System.out.println(response);
		if (response != null) {
			mv.addObject("responses", response.getDocTone().getTones());
		} else {
			mv.addObject("error", "Sorry, no messages for you.");
		}
		return mv;
	}
}
