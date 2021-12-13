package telran.b7a.student.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //for class reflection
@Getter
@EqualsAndHashCode(of = "id")
//@Document(collection = "Name for collection in MongoDB")  //now use class Name

@AllArgsConstructor
@Builder

public class Student {
	@Id //-> in MongoDB _id
	int id; //primitives must here null not stores
	@Setter
	String name;
	@Setter String password; //another style
	Map<String, Integer> scores = new HashMap<>();
	
	
//	public Student(int id, String name, String password) {
//		this.id = id;
//		this.name = name;
//		this.password = password;
//		scores = new HashMap<>();
//	}
	
	public boolean addScore(String exam, int score) {
		return scores.put(exam, score) == null;  //first or not first score
	}
	
}
