package telran.b7a.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.service.StudentService;

@RestController
public class StudentController {

	StudentService studentService; // methods include here ---> StudentService.java

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/student")
	public boolean studentRegister(@RequestBody StudentCredentialsDto studentCredentialsDto) {
		return studentService.addStudent(studentCredentialsDto);

	}

	@GetMapping("/student/{id}")
	public StudentDto findStudentById(@PathVariable("id") Integer studentid) {
		return studentService.findStudent(studentid);

	}

	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentCredentialsDto editStudent(@PathVariable Integer id, @RequestBody UpdateStudentDto updateStudentDto) {
		return studentService.updateStudent(id, updateStudentDto);

	}

	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);

	}

	@GetMapping("students/name/{name}")
	public List<StudentDto> findStudentByName(@PathVariable String name) {
		return studentService.findStudentsByName(name);

	}

	@PostMapping("quantity/students")
	public long studentsNamesQuantity(@RequestBody List<String> names) {
		return studentService.getStudentsNameQuantity(names);

	}

	@GetMapping("students/exam/{exam}/minscore/{score}")
	public List<StudentDto> studentsByExamScore(@PathVariable String exam, @PathVariable int score) {
		return studentService.getStudentsByExamScore(exam, score);

	}
	
	

}
