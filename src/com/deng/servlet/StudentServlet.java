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
		//创建session对象，用于保存返回给客户端的数据
		HttpSession session = request.getSession();
		//创建输出对象，用于输出
		PrintWriter out = response.getWriter();

		StudentService ss = new StudentServiceImpl();
		// 获得do属性
		String dos = request.getParameter("do");
		
		if (dos == null || dos.equals("")) {
			dos = "index";
			
		}
		// 主页
		if (dos.equals("index")) {
			List<Student> list = ss.findAllStudent();	
			session.setAttribute("list", list);
			response.sendRedirect("index.jsp");
			return;
		}
		if (dos.equals("add")) {

			//名字获取
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			//年龄获取
			String ages = request.getParameter("age");
			int age = Integer.parseInt(ages);
			//带参数的构造方法创建一个Student对象
			Student s = new Student(name, sex, age);
			//调用addStudent()方法把创建的对象u添加到数据库
			ss.addStudent(s);
			// 添加成功并跳转页面
			out.print("<script>alert('新增成功！');window.location='studentServlet?do=index';</script>");
			

		}
		if (dos.equals("del")) {
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			ss.delStudent(id);
			out.print("<script>alert('删除成功！');window.location='studentServlet?do=index';</script>");
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
			out.print("<script>alert('修改成功！');window.location='studentServlet?do=index';</script>");

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
