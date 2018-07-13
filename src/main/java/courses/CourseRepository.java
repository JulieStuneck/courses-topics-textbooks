package courses;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

	Collection<Course> findByTopicsContains(Topic topic);
	
	Collection<Course> findByTopicsId(Long id);

	Collection<Course> findByTextbooksContains(Textbook textbook);

	Course findByName(String courseName);

	Collection<Course> findAllByOrderByNameAsc();

	//Course save(Textbook newTextbook); //created from shouldAddAdditionalTextbooksToModel Test -don't think is correct
	

}
