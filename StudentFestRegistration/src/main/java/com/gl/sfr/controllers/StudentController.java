package com.gl.sfr.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.sfr.entities.Student;
import com.gl.sfr.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	    @Autowired
        private StudentService studentService;
	    @RequestMapping("/list")
        public String listStudents(Model model)
        {
	    	List<Student> students=studentService.findAll();
	    	model.addAttribute("Students",students);
	    	return "list-Students";
        }
	    @RequestMapping("/showFormForAdd")
	    public String showFormForAdd(Model model)
	    {
	    	Student student=new Student();
	    	model.addAttribute("Student", student);
	    	return "Student-Form";
	    }
	    
	    @PostMapping("/save")
		public String saveStudent(@RequestParam("id") int id,
				@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("course") String course,@RequestParam("country") String country) {

			System.out.println(id);
			Student theStudent;
			if(id!=0)
			{
				theStudent=studentService.findById(id);
				theStudent.setFirstName(firstName);
				theStudent.setLastName(lastName);
				theStudent.setCourse(course);
				theStudent.setCountry(country);
			}
			else
				theStudent=new Student(firstName, lastName, course,country);
			// save the Student Record
			studentService.save(theStudent);


			// use a redirect to prevent duplicate submissions
			return "redirect:/student/list";

		}

	    @RequestMapping("/delete")
		public String delete(@RequestParam("studentId") int theId) {

			// delete the Student Record
			studentService.deleteById(theId);

			// redirect to /student/list
			return "redirect:/student/list";

		}
		@RequestMapping(value = "/403")
		public ModelAndView accesssDenied(Principal user) {

			ModelAndView model = new ModelAndView();

			if (user != null) {
				model.addObject("msg", "Hi " + user.getName() 
				+ ", you do not have permission to access this page!");
			} else {
				model.addObject("msg", 
				"You do not have permission to access this page!");
			}
			model.setViewName("403");
			return model;

		}

}
