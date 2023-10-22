package com.gl.sfr.services;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gl.sfr.entities.Student;
import com.gl.sfr.repositories.StudentRepository;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {

		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
	
		return studentRepository.findById(id).get();
	}

	@Override
	public void save(Student theStudent) {

		studentRepository.save(theStudent);

	}

	@Override
	public void deleteById(int id) {

		studentRepository.deleteById(id);

	}

	@Override
	public List<Student> searchBy(String name) {
		
		return studentRepository.findByNameContainsAllIgnoreCase(name);
	}

}
