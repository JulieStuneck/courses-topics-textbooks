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

	@RequestMapping("/course")
	public String findOneCourse(@RequestParam(value="id")long id, Model model) throws CourseNotFoundException {
		Optional<Course> course = courseRepo.findById(id);
		
		if(course.isPresent()) {
			model.addAttribute("courses", course.get());
			return "course";
		}
		throw new CourseNotFoundException(); //default is return null. Created this Class to be more helpful
		
	}

	@RequestMapping("/show-courses")//name of endpoint
	public String findAllCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAll());//name of model in thymleaf
		return ("courses");//name of template
	}

}
