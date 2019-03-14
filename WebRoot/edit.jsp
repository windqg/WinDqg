
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改学生信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/foundation/5.5.3/js/foundation.min.js"></script>
<script
	src="https://cdn.bootcss.com/foundation/5.5.3/js/vendor/modernizr.js"></script>
</head>
<body>
	<h1>修改页面</h1>
	<form action="studentServlet?do=edit" method="post">
		<fieldset>
			<legend>Legends Never Die</legend>
			<label>序号 <input type="number"  placeholder="id" name="id">
			</label>
			<label>姓名 <input type="text"  placeholder="name"  name="name">
			</label> 
			<label>性别 <input type="text"  placeholder="sex"  name="sex">
			</label> 
			<label>年龄 <input type="number"  placeholder="age" name="age">
			</label>
			<input type="submit" value="修改" class="button"> <input type="reset" class="button">
		</fieldset>
	</form>
</body>
</html>
