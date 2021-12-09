package telran.b7a.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired //if no NUll pointer exception
	StudentRepository studentRepository; // что их связывает!!!!!

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (studentRepository.findById(studentCredentialsDto.getId()) != null) {
			return false;
		}
		Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(),
				studentCredentialsDto.getPassword());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		// TODO Auto-generated method stub
		return false;
	}

}
