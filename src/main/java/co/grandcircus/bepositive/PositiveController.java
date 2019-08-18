package co.grandcircus.bepositive;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bepositive.dao.PostRepository;
import co.grandcircus.bepositive.dao.UserRepository;
import co.grandcircus.bepositive.entities.Post;
import co.grandcircus.bepositive.entities.User;
import co.grandcircus.bepositive.pojos.DocumentResponse;
import co.grandcircus.bepositive.pojos.Tone;

@Controller
public class PositiveController {

	@Autowired
	ApiService apiService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	PostRepository postRepo;

	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public ModelAndView home() {

		return new ModelAndView("index");
	}

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("userName") String userName) {

		ModelAndView modelAndView = null;
		User user = userRepo.findByName(userName);
		if (ObjectUtils.isEmpty(user)) {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("user", userName);
		} else {
			modelAndView = new ModelAndView("showposts");
			modelAndView.addObject("posts", postRepo.findAll());
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
		}
		return modelAndView;
	}

	@RequestMapping("/showposts")
	public ModelAndView submitResponse(@RequestParam(value = "post", required = true) String text) {

		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("showposts");
		DocumentResponse response = apiService.search(text);
		System.out.println(response);
		if (isNotAcceptableTone(response.getDocTone().getTones())) {
			mv.addObject("error", "It doesn't sound positive.Please post again.");
		} else {
			Post post = new Post();
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			postRepo.save(post);
		}
		mv.addObject("posts", postRepo.findAll());
		return mv;
	}

	private boolean isNotAcceptableTone(List<Tone> tones) {

		boolean error = false;
		for (Tone tone : tones) {
			if (tone.getToneName().equals("anger")) {
				System.out.println(tone.getToneName());
				error = true;
				break;
			}
		}
		return error;
	}
}
