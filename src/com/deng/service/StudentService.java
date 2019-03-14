package com.deng.service;

import java.util.List;

import com.deng.bean.Student;

/**
 * @ClassName: StudentService.java
 * @Description: ��Ϊ�ӿڶ�������ӣ�ɾ�����޸ģ����ң��������ѧ����Ϣ�ķ���
 * @author:dengQingGui
 */
public interface StudentService {
	//��ӭ��ͬѧ
	public void addStudent(Student s);
	//ɾ��С���
	public void delStudent(int id);
	//�޸�ѧ����Ϣ
	public void updateStudent(Student s, int id);
	//����ѧ��������ѯѧ����Ϣ�����ڴ���ͬ��������ʲ���List
	public List<Student> findStudentByName(String name);
	//��ѯ����ѧ��
	public List<Student> findAllStudent();
	//����id����ѧ��
	public Student findStudentById(int id);
	
}
