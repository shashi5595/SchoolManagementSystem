package com.bms.schoolmanagementsystem.controller;

import com.bms.schoolmanagementsystem.dto.StudentDto;
import com.bms.schoolmanagementsystem.dto.request.student.CreateStudentRequest;
import com.bms.schoolmanagementsystem.dto.request.student.UpdateStudentRequest;
import com.bms.schoolmanagementsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid CreateStudentRequest request) {
        studentService.createStudent(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable String id,
                                              @Valid UpdateStudentRequest request) {
        studentService.updateStudent(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/classroom/{classroomId}")
    public ResponseEntity<Void> addStudentToClassroom(@PathVariable String id, @PathVariable String classroomId) {
        studentService.addStudentToClassroom(id, classroomId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/classroom/remove")
    public ResponseEntity<Void> removeStudentFromClassroom(@PathVariable String id) {
        studentService.removeStudentFromClassroom(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }
}
