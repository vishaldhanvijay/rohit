package org.nlt.controller.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.nlt.database.DatabaseConnection;
import org.nlt.model.Students;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {
private Session ses;
public StudentServices()
{
	ses=DatabaseConnection.getOpenSession();
}

public Boolean submit(Students student)
{
	ses.beginTransaction();
	ses.save(student);
	ses.getTransaction().commit();
	return true;
}
public Boolean update(Students student)
{
	ses.beginTransaction();
	ses.update(student);
	ses.getTransaction().commit();
	return true;
}
public Boolean delete(Students student)
{
	ses.beginTransaction();
	ses.delete(student);
	ses.getTransaction().commit();
	return true;
}

public Students getStudentsObject(String id)
{
	   int pid = Integer.parseInt(id);
	   ses.beginTransaction();
	   Students obj=(Students)ses.get(Students.class,pid);
	   ses.getTransaction().commit();
	   return obj;
}

public List<Students> showStudentsList(){
	   ses.beginTransaction();
	   Query query = ses.createQuery("from Students where status=1 order by name");
	  List<Students> list = query.list();
	  ses.getTransaction().commit();
	  return list;
}
public List<Students> studentsList(){
	   ses.beginTransaction();
	   Query query = ses.createQuery("from Students where status=1");
	  List<Students> list = query.list();
	  ses.getTransaction().commit();
	  return list;
}

public List<Students>getStudentsList(String id){
	   ses.beginTransaction();
	   Query query = ses.createQuery("from Students where status=1 and name="+id+"  order by name");
	   List<Students> studentList = query.list();
	   ses.getTransaction().commit();
	   return studentList;
}



}
