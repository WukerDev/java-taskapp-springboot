package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Student;

public interface StudentService {

	Optional<Student> getStudent(Integer studentId);
	Student setStudent(Student student);
	void deleteStudent(Integer studentId);
	Page<Student> getStudents(Pageable pageable);
	List<Student> getAllStudents();
	Object FindByNrIndeksu(String nr_indeksu, Pageable pageable);

}
