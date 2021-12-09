package telran.b7a.student.model;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //for class reflection
@Getter
@EqualsAndHashCode(of = "id")
public class Student {
	int id; //primitives must here null not stores
	@Setter
	String name;
	@Setter String password; //another style
	Map<String, Integer> scores;
	
	
	public Student(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		scores = new HashMap<>();
	}
	
	public boolean addScore(String exam, int score) {
		return scores.put(exam, score) == null;  //first or not first score
	}
	
}
