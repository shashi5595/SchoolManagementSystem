package com.bms.schoolmanagementsystem.controller;

import com.bms.schoolmanagementsystem.dto.TeacherDto;
import com.bms.schoolmanagementsystem.dto.request.teacher.CreateTeacherRequest;
import com.bms.schoolmanagementsystem.dto.request.teacher.UpdateTeacherRequest;
import com.bms.schoolmanagementsystem.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")

public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping
    public ResponseEntity<Void> createTeacher(@Valid CreateTeacherRequest request) {
        teacherService.createTeacher(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable String id,
                                              @Valid UpdateTeacherRequest request) {
        teacherService.updateTeacher(id, request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable String id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }


    @GetMapping
    public ResponseEntity<List<TeacherDto>> findAllTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }
}
