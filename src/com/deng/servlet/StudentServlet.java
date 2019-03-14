package com.deng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.deng.bean.Student;
import com.deng.service.StudentService;
import com.deng.serviceImpl.StudentServiceImpl;

public class StudentServlet extends HttpServlet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//����session�������ڱ��淵�ظ��ͻ��˵�����
		HttpSession session = request.getSession();
		//������������������
		PrintWriter out = response.getWriter();

		StudentService ss = new StudentServiceImpl();
		// ���do����
		String dos = request.getParameter("do");
		
		if (dos == null || dos.equals("")) {
			dos = "index";
			
		}
		// ��ҳ
		if (dos.equals("index")) {
			List<Student> list = ss.findAllStudent();	
			session.setAttribute("list", list);
			response.sendRedirect("index.jsp");
			return;
		}
		if (dos.equals("add")) {

			//���ֻ�ȡ
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			//�����ȡ
			String ages = request.getParameter("age");
			int age = Integer.parseInt(ages);
			//�������Ĺ��췽������һ��Student����
			Student s = new Student(name, sex, age);
			//����addStudent()�����Ѵ����Ķ���u��ӵ����ݿ�
			ss.addStudent(s);
			// ��ӳɹ�����תҳ��
			out.print("<script>alert('�����ɹ���');window.location='studentServlet?do=index';</script>");
			

		}
		if (dos.equals("del")) {
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			ss.delStudent(id);
			out.print("<script>alert('ɾ���ɹ���');window.location='studentServlet?do=index';</script>");
			return;
		}
		if (dos.equals("editbefore")) {
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			Student s = ss.findStudentById(id);
			session.setAttribute("edituser", s);
			response.sendRedirect("edit.jsp");
			
			return;
		}
		if (dos.equals("edit")) {

			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String ages = request.getParameter("age");
			int age = Integer.parseInt(ages);
			Student s = new Student(name, sex, age);
			ss.updateStudent(s, id);
			out.print("<script>alert('�޸ĳɹ���');window.location='studentServlet?do=index';</script>");

			return;
		}
		if(dos.equals("findByName")) {
			String name = request.getParameter("name");
			List<Student> list = ss.findStudentByName(name);
			session.setAttribute("list", list);
			response.sendRedirect("index.jsp");
			return;
		}
		
	}
}
