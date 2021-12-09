package telran.b7a.student.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor


public class StudentDto {
	Integer id;
	String name;
	Map<String, Integer> scores;
}
