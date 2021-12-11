package telran.b7a.student.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Builder;
import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired // if no NUll pointer exception
	StudentRepository studentRepository; // что их связывает!!!!!

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (studentRepository.findById(studentCredentialsDto.getId()) != null) {
			return false;
		}

//		Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(),
//				studentCredentialsDto.getPassword());
		
		Student student = Student.builder().id(studentCredentialsDto.getId()).name(studentCredentialsDto.getName())
				.password(studentCredentialsDto.getPassword()).scores(new HashMap<String, Integer>()).build();

		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id);
		if (student == null) {
			return null;
		}
		return StudentDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build();
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student victumStudent = studentRepository.deleteById(id);
		if (victumStudent == null) {
			return null;
		}
		return StudentDto.builder().id(victumStudent.getId()).name(victumStudent.getName())
				.scores(victumStudent.getScores()).build();

	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student studentForUpdate = studentRepository.findById(id);
		if (studentForUpdate == null) {
			return null;
		}
		studentForUpdate.setName(updateStudentDto.getName());
		studentForUpdate.setPassword(updateStudentDto.getPassword());
		return StudentCredentialsDto.builder().id(studentForUpdate.getId()).name(studentForUpdate.getName())
				.password(studentForUpdate.getPassword()).build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student studentForUpdate = studentRepository.findById(id);
		if (studentForUpdate == null) {
			return false;
		}
		return studentForUpdate.addScore(scoreDto.getExamName(), scoreDto.getScore());
	}

}
