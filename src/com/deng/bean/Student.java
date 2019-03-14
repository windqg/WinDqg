package com.deng.bean;

/**
 * @ClassName: Student.java
 * @Description: Studentʵ����
 * @author dengQingGui 
 */
public class Student  {
	
	//private static final long serialVersionUID = -7476381137287496245L;
	//���
	private int id;
	//����
	private String name;
	//�Ա�
	private String sex;
	//����
	private int age;
	
	public Student() {
		
	}
	
	public Student(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public Student(int id, String name, String sex, int age) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
		
}
