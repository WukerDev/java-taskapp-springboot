package com.project.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Projekt;
import com.project.model.Student;

public interface StudentService {
	Optional<Student> getStudent(Integer studentId);
	Student setStudent(Student student);
	void deleteStudent(Integer studentId);
	Page<Student> getStudenci(java.awt.print.Pageable pageable);

	Page<Student> FindByNrIndeksu(String nr_indeksu, java.awt.print.Pageable pageable);
	Object getAllStudents();
	Page<Student> getStudenci(Pageable pageable);
	Page<Student> FindByNrIndeksu(String nr_indeksu, Pageable pageable);
	Optional<Projekt> getProjekt(Map<String, ?> map);
	Page<Projekt> searchByNazwa(String nazwa, Pageable pageable);
	Optional<Projekt> getProjekt(Integer projektId2);
	Optional<Student> findByNrIndeksu(String nrIndeksu, Pageable pageable);
	Page<Student> findByNrIndeksuStartsWith(String nrIndeksu, Pageable pageable);
	Page<Student> findByNazwiskoStartsWithIgnoreCase(String nazwisko, Pageable pageable);
	Page<Student> findByProjekt(Projekt projekt, Pageable pageable);
}
