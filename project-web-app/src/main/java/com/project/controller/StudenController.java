package com.project.controller;

import org.springframework.data.domain.Pageable;
import java.net.URI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.model.Student;
import com.project.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/students")
public class StudenController {

    private StudentService studentService;

    @Autowired
    public StudenController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer studentId) {
        java.util.Optional<Student> student = studentService.getStudent(studentId);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.setStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{studentId}").buildAndExpand(createdStudent.getStudentId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student,@PathVariable Integer studentId) {
    	return studentService.getStudent(studentId).map(s -> {
                    studentService.setStudent(student);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer studentId) {
        return studentService.getStudent(studentId)
                .map(s -> {
                    studentService.deleteStudent(studentId);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getStudenci(pageable));
    }

    @GetMapping(params = "nr_indeksu")
    public ResponseEntity<Object> getStudentsByNrIndeksu(@RequestParam String nr_indeksu,Pageable pageable) {
        return ResponseEntity.ok(studentService.FindByNrIndeksu(nr_indeksu, pageable));
    }
}

