package com.gl.sfr.services;

import java.util.List;

import com.gl.sfr.entities.Student;

public interface StudentService {
	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student thestudent);

	public void deleteById(int theId);

	List<Student> searchBy( String name);
}
