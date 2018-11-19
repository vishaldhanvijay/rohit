package org.nlt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Value;
import org.nlt.controller.services.StudentServices;
import org.nlt.model.Students;
import org.nlt.util.StudentsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentRegistrationController {
	@Autowired
	private StudentServices studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		Map m = new HashMap<>();
		/*m.put("FormAction", "StudentSubmit");
		m.put("Button", "SUBMIT");
		
		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);*/
		
		return new ModelAndView("home", m);
	}
	@RequestMapping(value = "/studentHomePage", method = RequestMethod.GET)
	public ModelAndView homePage2() {
		Map m = new HashMap<>();
		/*m.put("FormAction", "StudentSubmit");
		m.put("Button", "SUBMIT");
		
		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);*/
		
		return new ModelAndView("home", m);
	}
	@RequestMapping(value = "/studentRegistrationPage", method = RequestMethod.GET)
	public ModelAndView home() {
		Map m = new HashMap<>();
		m.put("FormAction", "StudentSubmit");
		m.put("Button", "SUBMIT");

		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);

		return new ModelAndView("index", m);
	}

	@RequestMapping(value = "/StudentSubmit", method = RequestMethod.POST)

	public ModelAndView submitData(HttpServletRequest req, HttpServletResponse res) {
		Map m = new HashMap<>();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String phone = req.getParameter("phone");

		m.put("NameValue", name);
		m.put("AgeValue", age);
		m.put("PhoneValue", phone);

		Students studentObject = new Students();
		try {

			studentObject.setName(name);
			studentObject.setAge(Integer.parseInt(age));
			studentObject.setPhone(phone);
			studentObject.setStatus(1);

			if (StudentsUtil.validate(studentObject)) {
				m.put("msg", "Invalid Entry");

			} else {

				studentService.submit(studentObject);
				m.put("msg", "Record Submitted Successfully");
			}

		} catch (Exception ex) {
			m.put("msg", "Invalid Entry");
		}

		m.put("FormAction", "StudentSubmit");
		m.put("Button", "SUBMIT");

		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);

		return new ModelAndView("index", m);
	}

	@RequestMapping(value = "GetUpdateStudent", method = RequestMethod.GET)
	public ModelAndView getUpdate(HttpServletRequest req, HttpServletResponse res) {
		Map m = new HashMap();
		String id = req.getParameter("id");
		m.put("FormAction", "UpdateStudent");
		m.put("Button", "UPDATE");
		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);

		Students studentsObject = studentService.getStudentsObject(id);
		m.put("StudentId", studentsObject.getId());
		m.put("NameValue", studentsObject.getName());
		m.put("AgeValue", studentsObject.getAge());
		m.put("PhoneValue", studentsObject.getPhone());

	

		return new ModelAndView("index", m);

	}

	@RequestMapping(value = "/UpdateStudent", method = RequestMethod.POST)
	public ModelAndView updateStudent(HttpServletRequest req, HttpServletResponse res) {
		Map m = new HashMap<>();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String phone = req.getParameter("phone");
		Students studentObject = studentService.getStudentsObject(id);

		try {
			studentObject.setName(name);
			studentObject.setAge(Integer.parseInt(age));
			studentObject.setPhone(phone);
			

			if (StudentsUtil.validate(studentObject)) {
				m.put("msg", "Invalid Entry");

			} else {
				studentService.submit(studentObject);
				m.put("msg", "Record Updated Successfully");
			}
			m.put("FormAction", "StudentSubmit");
			m.put("Button", "SUBMIT");
			List<Students> list = studentService.showStudentsList();
			m.put("StudentList", list);

		} catch (Exception ex) {
			System.out.println(ex);
		}

		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);

		return new ModelAndView("index", m);

	}
	@RequestMapping(value="GetDeleteStudent", method=RequestMethod.GET)
	public ModelAndView getDelete(HttpServletRequest req,HttpServletResponse res)
	{
		Map m=new HashMap<>();
		String id = req.getParameter("id");
		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);
		m.put("FormAction", "studentDelete");
		m.put("Button", "DELETE");
      Students studentsObject = studentService.getStudentsObject(id);
      m.put("StudentId", studentsObject.getId());
      m.put("NameValue", studentsObject.getName());
      m.put("AgeValue", studentsObject.getAge());
      m.put("PhoneValue", studentsObject.getPhone());
	
return new ModelAndView("index",m);
	}
	
	@RequestMapping(value="/studentDelete", method=RequestMethod.POST)
	public ModelAndView DeleteStudent(HttpServletRequest req,HttpServletResponse res)
	{
		
		Map m=new HashMap<>();
		String id = req.getParameter("id");
		System.out.println("======================="+id);
		Students studentsObject = studentService.getStudentsObject(id);
		studentsObject.setStatus(0);
		studentService.update(studentsObject);
		m.put("msg", "Record Deleted Successfully");

		List<Students> list = studentService.showStudentsList();
		m.put("StudentList", list);

		m.put("FormAction", "studentDelete");
		m.put("Button", "DELETE");
		
		return new ModelAndView("index",m);

	}
	
}
