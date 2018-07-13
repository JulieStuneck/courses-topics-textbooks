package courses;

import org.springframework.data.repository.CrudRepository;

public interface TextbookRepository extends CrudRepository<Textbook, Long> {

	Textbook findByName(String textbookName);
	
	//Textbook save(Textbook newTextbook);
	
}
