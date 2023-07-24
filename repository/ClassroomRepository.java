package com.bms.schoolmanagementsystem.repository;

import com.bms.schoolmanagementsystem.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}