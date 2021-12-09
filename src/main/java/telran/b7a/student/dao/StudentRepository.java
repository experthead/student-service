package telran.b7a.student.dao;

import telran.b7a.student.model.Student;

public interface StudentRepository { // CRUD Create Read Update Delete Find
	Student save(Student student);

	Student findById(int id);

	Student deleteById(int id);

}
