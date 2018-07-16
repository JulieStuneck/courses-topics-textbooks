package courses;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")//started with "/courses" in first two RequestMappings. Can take it out and put it here because of repetition
public class CourseRestController {
	
	@Resource
	private CourseRepository courseRepo;
	
	@Resource
	private TopicRepository topicRepo;
	
	@RequestMapping("")//still have to refer to /courses in url
	public Iterable<Course> findAllCourses() {
		return courseRepo.findAll();
	}
	
	@RequestMapping("/{id}")//need to refer to /courses/{id} in url
	public Optional<Course> findOneCourse(@PathVariable long id){
		return courseRepo.findById(id);	
	}
	
	@RequestMapping("/topics{topicName}")
	public Collection<Course> findAllCoursesByTopic(@PathVariable(value="topicName") String topicName){
		Topic topic = topicRepo.findByNameIgnoreCaseLike(topicName);
		return courseRepo.findByTopicsContains(topic);
	}
	
	
	
}
