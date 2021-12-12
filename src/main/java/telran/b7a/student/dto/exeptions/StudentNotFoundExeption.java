package telran.b7a.student.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND) //reason wins!!!!!
public class StudentNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1904954019350523134L;
	public StudentNotFoundExeption(int id) {
		super("Student with id " + id + " not found!");
	}
}
