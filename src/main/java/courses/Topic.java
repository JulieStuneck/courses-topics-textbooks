package courses;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Topic {

	@Id
	@GeneratedValue // JPA determines id - we don't need to put it in constructor or populator
	private long id;

	private String name;
		
	@ManyToMany(mappedBy = "topics")
	private Collection<Course> course;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Course> getCourse() {
		return course;
	}
	
	// default, no args constructor required by JPA, so it can hit on the this table
	public Topic() {
	}

	public Topic(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
