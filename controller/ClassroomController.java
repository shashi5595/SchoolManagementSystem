package com.bms.schoolmanagementsystem.controller;

import com.bms.schoolmanagementsystem.dto.ClassroomDto;
import com.bms.schoolmanagementsystem.dto.request.classroom.CreateClassroomRequest;
import com.bms.schoolmanagementsystem.dto.request.classroom.UpdateClassroomRequest;
import com.bms.schoolmanagementsystem.service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<Void> createClassroom(@Valid CreateClassroomRequest request) {
        classroomService.createClassroom(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClassroom(@PathVariable String id,
                                                @Valid UpdateClassroomRequest request) {
        classroomService.updateClassroom(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable String id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> findClassroomById(@PathVariable String id) {
        return ResponseEntity.ok(classroomService.findClassroomById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> findAllClassrooms() {
        return ResponseEntity.ok(classroomService.findAllClassrooms());
    }
}
