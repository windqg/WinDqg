package com.deng.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deng.bean.Student;
import com.deng.jdbcUtils.JdbcUtil;
import com.deng.service.StudentService;

/**
 * @ClassName: StudentServiceImpl.java
 * @Description: ʵ����StudentService�ӿ�����ӣ�ɾ�����޸ģ����ң��������ѧ����Ϣ�ķ���
 * @author: dengQingGui
 * 
 */
public class StudentServiceImpl implements StudentService {
	private JdbcUtil ju;
	ResultSet rs = null;
	/**
	 * ��ӭ��ͬѧ 
	 *
	 */
	@Override
	public void addStudent(Student s) {
		String sql = "insert into student(name, sex, age) values('" + s.getName() + "','" + s.getSex() + "','" +s.getAge()+"')";
		ju = new JdbcUtil();
		ju.executeUpdate(sql);
		ju.release();
	}
	
	/**
	 * ɾ��С���
	 */
	@Override
	public void delStudent(int id) {
		String sql = "delete from student where id = " + id;
		ju = new JdbcUtil();
		ju.executeUpdate(sql);
		ju.release();
	}
	
	/**
	 * ��ѯ����ѧ��
	 */
	@Override
	public List<Student> findAllStudent() {
		ju = new JdbcUtil();
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		rs = ju.executeQuery(sql);
		try {
			while(rs != null && rs.next()) {
				Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ju.release();
		return list;
	}
	
	/**
	 * ����id��С���
	 */
	@Override
	public Student findStudentById(int id) {
		Student s = null;
		ju = new JdbcUtil();
		String sql = "select * from student where id=" + id;
		rs = ju.executeQuery(sql);
		try {
			if (rs.next()) {
				s = new Student(rs.getString(2), rs.getString(3),
						rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ju.release();
		return s;
	}
	
	/**
	 * ����ѧ��������ѯѧ����Ϣ�����ڴ���ͬ��������ʲ���List
	 */
	@Override
	public List<Student> findStudentByName(String name) {
		ju = new JdbcUtil();
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where name = " + "'" + name + "'";
		ResultSet rs = ju.executeQuery(sql);
		try {
			while(rs != null &&rs.next()) {
				Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ju.release();
		return list;
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 */
	@Override
	public void updateStudent(Student s, int id) {
		ju = new JdbcUtil();
		String sql = "update student set name = " + "'" + s.getName() + "'" + ", age = " + "'" + s.getAge() + "'" 
		+ ", sex = " + "'" + s.getSex()+ "'" + "where id = "+id;
		ju.executeUpdate(sql);
		ju.release();
	}

}
