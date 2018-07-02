package courses;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

	@Resource
	CourseRepository courseRepo;

	@Resource
	TopicRepository topicRepo;
	
	@Resource
	TextbookRepository textbookRepo;

	@RequestMapping("/course")
	public String findOneCourse(@RequestParam(value = "id") long id, Model model) throws CourseNotFoundException {
		Optional<Course> course = courseRepo.findById(id);

		if (course.isPresent()) {
			model.addAttribute("courses", course.get());
			return "course";
		}
		throw new CourseNotFoundException(); // default is return null. Created this Class to be more helpful

	}

	@RequestMapping("/show-courses") // name of endpoint
	public String findAllCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAll());// name of model in thymleaf
		return ("courses");// name of template
	}

	@RequestMapping("/topic")
	public String findOneTopic(@RequestParam(value = "id") long id, Model model) throws TopicNotFoundException {
		Optional<Topic> topic = topicRepo.findById(id);

		if (topic.isPresent()) {
			model.addAttribute("topics", topic.get());
			model.addAttribute("courses", courseRepo.findByTopicsContains(topic.get()));
			return "topic";
		}
		throw new TopicNotFoundException();
	}

	@RequestMapping("/show-topics")
	public String findAllTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return ("topics");		
	}

	@RequestMapping("/textbook")
	public String findOneTextbook(@RequestParam(value = "id") long id, Model model) throws TextbookNotFoundException {
		Optional<Textbook> textbook = textbookRepo.findById(id);
		
		if (textbook.isPresent()) {
			model.addAttribute("textbooks", textbook.get());
			model.addAttribute("courses", courseRepo.findByTextbooksContains(textbook.get()));
			return "textbook";
		}
		throw new TextbookNotFoundException();

	}

	@RequestMapping("/show-textbooks")
	public String findAllTextbooks(Model model) {
		model.addAttribute("textbooks", textbookRepo.findAll());
		return ("textbook");
	}

}
