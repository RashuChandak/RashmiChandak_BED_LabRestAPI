package com.gl.sfr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.sfr.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>  {
        List<Student> findByNameContainsAllIgnoreCase(String name);
}
