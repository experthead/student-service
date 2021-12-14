package telran.b7a.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dao.StudentsMongoRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.dto.exeptions.StudentNotFoundExeption;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	
	StudentsMongoRepository studentRepository; // что их связывает!!!!!
	ModelMapper modelMapper;
	
	
	@Autowired
	public StudentServiceImpl(StudentsMongoRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
		
	}

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) { // ????
			return false;
		}

		Student student = modelMapper.map(studentCredentialsDto, Student.class);

//		Student student = new Student(studentCredentialsDto.getId(), studentCredentialsDto.getName(),
//				studentCredentialsDto.getPassword());

//		Student student = Student.builder().id(studentCredentialsDto.getId()).name(studentCredentialsDto.getName())
//				.password(studentCredentialsDto.getPassword()).scores(new HashMap<String, Integer>()).build();

		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));

//		return StudentDto.builder()
//							.id(student.getId())
//							.name(student.getName())
//							.scores(student.getScores())
//							.build();
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto deleteStudent(Integer id) { // use ternary operators !!!!!!
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));
		studentRepository.deleteById(id);

		return modelMapper.map(student, StudentDto.class);

	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student studentForUpdate = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));

		studentForUpdate.setName(updateStudentDto.getName());
		studentForUpdate.setPassword(updateStudentDto.getPassword());
		return modelMapper.map(studentForUpdate, StudentCredentialsDto.class);
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student studentForUpdate = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption(id));

		boolean res = studentForUpdate.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(studentForUpdate);
		return res;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {

		return studentRepository.findByNameIgnoreCase(name.toLowerCase()) //look for docs https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNameQuantity(List<String> names) {
		return studentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int score) {
		return studentRepository.findByExamAndScoreGreaterEqualsThan(exam, score) //look for docs https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

}
