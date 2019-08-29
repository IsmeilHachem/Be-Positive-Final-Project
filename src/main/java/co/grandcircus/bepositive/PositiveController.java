package co.grandcircus.bepositive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;

import co.grandcircus.bepositive.dao.CommentRepository;
import co.grandcircus.bepositive.dao.PostRepository;
import co.grandcircus.bepositive.dao.UserRepository;
import co.grandcircus.bepositive.dto.DocumentResponse;
import co.grandcircus.bepositive.dto.QuoteOfDay;
import co.grandcircus.bepositive.dto.Tone;
import co.grandcircus.bepositive.entities.Comment;
import co.grandcircus.bepositive.entities.Post;
import co.grandcircus.bepositive.entities.User;

@Controller
public class PositiveController {

	@Value("${cloudinaryKey}")
	private String cloudinaryKey;

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

	@RequestMapping("/signupUser")
	public ModelAndView showSignup() {

		return new ModelAndView("signup");
	}

	@PostMapping("/submitsignup")
	public ModelAndView submitSignup(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "firstname", required = true) String firstName,
			@RequestParam(value = "lastname", required = true) String lastName,
			@RequestParam(value = "password", required = true) String password) {

		ModelAndView modelAndView = null;
		// 1. Add to database
		if (name == "") {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("error", "Please enter a username.");
		} else if (firstName == "" || lastName == "") {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("error", "Please enter your first and last name.");
		} else if (userRepo.findByName(name) != null) {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("error", "Username already exists.");
		} else if (password == "") {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("error", "Please enter password");
		} else {
			User user = new User();
			user.setName(name);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			userRepo.save(user);
			// 2. Add to session
			session.setAttribute("user", user);
			modelAndView = new ModelAndView("signupcomplete");
		}
		return modelAndView;
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session, @SessionAttribute(name = "quote", required = false) QuoteOfDay quote) {

		ModelAndView modelAndView = null;
		User user1 = userRepo.findByName(userName);
		User user = userRepo.findByNameAndPassword(userName, password);
		if (ObjectUtils.isEmpty(user1)) {
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("error", "Invalid user");
			return mv;
		}

		else if (ObjectUtils.isEmpty(user)) {
			// modelAndView = new ModelAndView("index");
			// modelAndView.addObject("user", userName);
			// } else if (user == null) {
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("error", "Invalid password");
			return mv;
		} else {
			modelAndView = new ModelAndView("showposts");
			loadPage(modelAndView, user, quote);
			modelAndView.addObject("user", user);
			session.setAttribute("user", user);
		}
		return modelAndView;
	}

	@RequestMapping("/posts")
	public ModelAndView showResponse(@SessionAttribute(name = "quote", required = false) QuoteOfDay quote) {

		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("showposts");
		loadPage(mv, user, quote);
		return mv;
	}

	@PostMapping("/createposts")
	public ModelAndView submitResponse(@RequestParam(value = "post") String text, RedirectAttributes redir) {

		Post post = new Post();
		User user = (User) session.getAttribute("user");
		DocumentResponse response = apiService.search(text);
		List<Tone> tones = response.getDocTone().getTones();
		if (isNotAcceptableTone(tones) || WordFilter.badwordfinder(text)) {
			redir.addFlashAttribute("postError", "It doesn't sound positive. Please post again.");
		} else if (tones.isEmpty()) {
			post.setMaxScore(0.5);
			post.setMaxTone("Tentative");
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			post.setRating(0);
			postRepo.save(post);
		} else {
			Tone toneWithHighestScore = getToneWithHighestScore(tones);
			post.setMaxScore(toneWithHighestScore.getScore());
			post.setMaxTone(toneWithHighestScore.getToneName());
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			post.setRating(0);
			postRepo.save(post);
		}
		session.setAttribute("user", userRepo.findByName(user.getName()));
		return new ModelAndView("redirect:/posts");
	}

	private void loadPage(ModelAndView mv, User user, QuoteOfDay quote) {

		// loading quote
		
		if (quote == null) {
			session.setAttribute("quote", apiService.getQuote());
		}
		
		// ---------------------------------------------//
		// loading not followed users
		user = userRepo.findByName(user.getName());
		List<User> otherUsers = userRepo.findAll();
		otherUsers.remove(user);
		otherUsers.removeAll(user.getFollows());
		mv.addObject("otherUsers", otherUsers);
		// ---------------------------------------------//
		// loading who I am following
		mv.addObject("follows", user.getFollows());
		// --------------------------------------------//
		// loading posts for followed users and self
		List<Post> postsToDisplay = postRepo.findAllByOrderByCreatedDesc();
		List<Post> postsToRemove = new ArrayList<>();
		for (Post post : postsToDisplay) {
			if (!post.getUser().equals(user) && !user.getFollows().contains(post.getUser())) {
				postsToRemove.add(post);
			}
		}
		postsToDisplay.removeAll(postsToRemove);
		mv.addObject("posts", postsToDisplay);
		// --------------------------------------------//
		// loading tone analysis report for self
		List<Post> posts = postRepo.findByUser(user);
		Map<String, ToneSummary> toneSummaryMap = new HashMap<>();
		for (Post post : posts) {
			ToneSummary toneSummary = null;
			if (toneSummaryMap.containsKey(post.getMaxTone())) {
				toneSummary = toneSummaryMap.get(post.getMaxTone());
			} else {
				toneSummary = new ToneSummary(post.getMaxTone());
			}
			toneSummary.incrementCount();
			toneSummary.addToScore(post.getMaxScore());
			toneSummaryMap.put(post.getMaxTone(), toneSummary);
		}
		mv.addObject("toneSummaries", toneSummaryMap.values());
	}

	@PostMapping("/createcomments")
	public ModelAndView submitCommentResponse(@RequestParam(value = "comment", required = true) String text,
			@RequestParam(value = "postId", required = true) Integer postId, RedirectAttributes redir) {

		User user = (User) session.getAttribute("user");
		DocumentResponse response = apiService.search(text);
		List<Tone> tones = response.getDocTone().getTones();
		if (isNotAcceptableTone(tones) || WordFilter.badwordfinder(text)) {
			redir.addFlashAttribute("commentError", "It doesn't sound positive. Please comment again.");
		} else {
			Comment comment = new Comment();
			comment.setDescription(text);
			comment.setCreated(new Date());
			Post post = new Post();
			post.setPostId(postId);
			comment.setPost(post);
			commentRepo.save(comment);
		}
		return new ModelAndView("redirect:/posts");
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

	@RequestMapping("/createposts/upvote")
	public ModelAndView upvote(@RequestParam("id") Integer postId, @SessionAttribute("user") User user) {

//		ModelAndView modelAndView = new ModelAndView("showposts");
		Post posts = postRepo.findById(postId).get();
		posts.setRating(posts.getRating() + 1);
		postRepo.save(posts);
		return new ModelAndView("redirect:/posts");
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {

		session.removeAttribute("user");
		session.removeAttribute("quote");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/deletepost")
	public ModelAndView remove(@RequestParam("id") Integer postId) {

		postRepo.deleteById(postId);
		return new ModelAndView("redirect:/posts");
	}

	@RequestMapping("/deletecomment")
	public ModelAndView removeComment(@RequestParam("id") Integer commentId) {

		commentRepo.deleteById(commentId);
		return new ModelAndView("redirect:/posts");
	}

	@RequestMapping("/editpost")
	public ModelAndView showEdit(@RequestParam("id") Integer postId) {

		ModelAndView mv = new ModelAndView("editpost");
		mv.addObject("post", postRepo.findById(postId).orElse(null));
		mv.addObject("title", "Edit post");
		return mv;
	}

	@PostMapping("/editpost")
	public ModelAndView edit(@RequestParam("postId") Integer postId, @RequestParam("post") String text,
			RedirectAttributes redir) {

		User user = (User) session.getAttribute("user");
		DocumentResponse response = apiService.search(text);
		List<Tone> tones = response.getDocTone().getTones();
		Post post = postRepo.findByPostId(postId);
		if (isNotAcceptableTone(tones) || WordFilter.badwordfinder(text)) {
			ModelAndView mv = new ModelAndView("editpost");
			mv.addObject("postError", "It doesn't sound positive. Please post again.");
			mv.addObject("post", postRepo.findById(postId).orElse(null));
			return mv;
		} else if (tones.isEmpty() || (tones == null)) {
			post.setMaxScore(0.5);
			post.setMaxTone("Tentative");
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			post.setRating(0);
			postRepo.save(post);
		} else {
			Tone toneWithHighestScore = getToneWithHighestScore(tones);
			post.setMaxScore(toneWithHighestScore.getScore());
			post.setMaxTone(toneWithHighestScore.getToneName());
			post.setDescription(text);
			post.setUser(user);
			post.setCreated(new Date());
			post.setRating(0);
			postRepo.save(post);
		}
		return new ModelAndView("redirect:/posts");
	}

	@RequestMapping("/follow")
	public ModelAndView follow(@RequestParam("followUserId") Integer followUserId,
			@SessionAttribute("user") User user) {

		return followUnfollow(followUserId, user, true);
	}

	@RequestMapping("/unfollow")
	public ModelAndView unfollow(@RequestParam("followUserId") Integer followUserId,
			@SessionAttribute("user") User user) {

		return followUnfollow(followUserId, user, false);
	}

	private ModelAndView followUnfollow(Integer followUserId, User user, boolean follow) {

		User currentUser = userRepo.findByUserId(user.getUserId());
		User followUser = userRepo.findByUserId(followUserId);
		Set<User> follows = currentUser.getFollows();
		if (follow) {
			follows.add(followUser);
		} else {
			follows.remove(followUser);
		}
		currentUser.setFollows(follows);
		userRepo.save(currentUser);
		return new ModelAndView("redirect:/posts");
	}

	// This will run one time when the application starts up to configure
	// cloudinary.
	@PostConstruct
	public void init() {

		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", "bepositive");
		config.put("api_key", "449879741459621");
		config.put("api_secret", cloudinaryKey);
		Cloudinary cloudinary = new Cloudinary(config);
		Singleton.registerCloudinary(cloudinary);
	}

	@RequestMapping("/uploadphoto")
	public ModelAndView upload() {

		return new ModelAndView("cloudinary/direct_upload_form");
	}

	@PostMapping("/uploadphoto")
	public ModelAndView showPhoto(RedirectAttributes redir, String imageId, String version,
			@RequestParam("preloadedFile") String preloadedFile) throws Exception {

		Post post = new Post();
		User user = (User) session.getAttribute("user");
		post.setUser(user);
		post.setCreated(new Date());
		Cloudinary cloudinary = Singleton.getCloudinary();
		System.out.println(post);
		post.getImageId();
		post.getVersion();
		System.out.println("preloadedFile is " + preloadedFile);
		String[] array = preloadedFile.split("/");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		String[] id = array[3].split("#");
		System.out.println(id[0]);
		imageId = id[0];
		version = array[2];
		if (imageId != null || version != null) {
			post.setImageId(imageId);
			post.setVersion(version);
			post.setMaxScore(0.5);
			post.setMaxTone("Tentative");
		}
		
		postRepo.save(post);
		return new ModelAndView("redirect:/posts");
	}
}