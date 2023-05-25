package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.model.Student;
import com.project.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Student setStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public Page<Student> getStudenci(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Page<Student> findByNrIndeksu(String nrIndeksu, Pageable pageable) {
        return studentRepository.findByNrIndeksuStartsWith(nrIndeksu, pageable);
    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

	@Override
	public Object FindByNrIndeksu(String nr_indeksu, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
