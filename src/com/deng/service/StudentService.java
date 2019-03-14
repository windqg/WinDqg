package com.deng.service;

import java.util.List;

import com.deng.bean.Student;

/**
 * @ClassName: StudentService.java
 * @Description: 作为接口定义了添加，删除，修改，查找，获得所有学生信息的方法
 * @author:dengQingGui
 */
public interface StudentService {
	//欢迎新同学
	public void addStudent(Student s);
	//删除小伙伴
	public void delStudent(int id);
	//修改学生信息
	public void updateStudent(Student s, int id);
	//根据学生姓名查询学生信息，由于存在同名情况，故采用List
	public List<Student> findStudentByName(String name);
	//查询所有学生
	public List<Student> findAllStudent();
	//根据id查找学生
	public Student findStudentById(int id);
	
}
