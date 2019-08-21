package co.grandcircus.bepositive;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.bepositive.dao.CommentRepository;
import co.grandcircus.bepositive.dao.PostRepository;
import co.grandcircus.bepositive.dao.UserRepository;
import co.grandcircus.bepositive.entities.Comment;
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
	CommentRepository commentRepo;

	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public ModelAndView home() {

		return new ModelAndView("index");
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("userName") String userName) {

		ModelAndView modelAndView = null;
		User user = userRepo.findByName(userName);
		if (ObjectUtils.isEmpty(user)) {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("user", userName);
		} else {
			modelAndView = new ModelAndView("showposts");
			modelAndView.addObject("posts", postRepo.findAllByOrderByCreatedDesc());
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
		}
		return modelAndView;
	}

	@PostMapping("/showposts")
	public ModelAndView submitResponse(@RequestParam(value = "post", required = true) String text) {

		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("showposts");
		DocumentResponse response = apiService.search(text);
		List<Tone> tones = response.getDocTone().getTones();
		if (isNotAcceptableTone(tones) || WordFilter.badwordfinder(text)) {
			mv.addObject("postError", "It doesn't sound positive. Please post again.");
		} else {
			Post post = new Post();
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			Tone toneWithHighestScore = getToneWithHighestScore(tones);
			post.setMaxScore(toneWithHighestScore.getScore());
			post.setMaxTone(toneWithHighestScore.getToneName());
			postRepo.save(post);
		}
		mv.addObject("posts", postRepo.findAllByOrderByCreatedDesc());
		return mv;
	}

	@PostMapping("/showcomments")
	public ModelAndView submitCommentResponse(@RequestParam(value = "comment", required = true) String text,
			@RequestParam(value = "postId", required = true) Integer postId) {

		ModelAndView mv = new ModelAndView("showposts");
		DocumentResponse response = apiService.search(text);
		List<Tone> tones = response.getDocTone().getTones();
		if ((isNotAcceptableTone(tones)) || (WordFilter.badwordfinder(text) == true)) {
			mv.addObject("commentError", "It doesn't sound positive. Please comment again.");
		} else {
			Comment comment = new Comment();
			comment.setDescription(text); //
			comment.setCreated(new Date());
			Post post = new Post();
			post.setPostId(postId);
			comment.setPost(post);
			commentRepo.save(comment);
		}
		mv.addObject("posts", postRepo.findAllByOrderByCreatedDesc());
		return mv;
	}

	private boolean isNotAcceptableTone(List<Tone> tones) {

		boolean error = false;
		for (Tone tone : tones) {
			if (tone.getToneName().equalsIgnoreCase("anger")) {
				error = true;
				break;
			}
		}
		return error;
	}

	private Tone getToneWithHighestScore(List<Tone> tones) {

		// https://www.javatpoint.com/Comparator-interface-in-collection-framework
		// AgeComparator.java
		tones.sort(new Comparator<Tone>() {

			@Override
			public int compare(Tone o1, Tone o2) {

				if (o1.getScore() > o2.getScore()) {
					return -1;
				} else if (o1.getScore() < o2.getScore()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		return tones.get(0);
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {

		session.removeAttribute("user");
		return new ModelAndView("redirect:/");
	}
}
