<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>学生信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
	<link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/foundation/5.5.3/js/foundation.min.js"></script>
<script
	src="https://cdn.bootcss.com/foundation/5.5.3/js/vendor/modernizr.js"></script>
<style type="text/css">
table {
	margin: auto;
}

td {
	text-align: center;
}

h1 {
	margin-left: 40%;
}

a#add {
	margin-left: 45%;
}
</style>
</head>
<body style="padding:20px;">
	<h2 align = "left">当前登录的用户是：邓庆贵</h2>
	<a href="studentServlet?do=index"><span class="btn btn-danger">学生主页</span></a>
	<a id="add" href="add.jsp"><span class="btn btn-danger">欢迎新同学</span></a>
	<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 10px 0 5px 0;" method="post" action="studentServlet?do=findByName">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入学生姓名" name="name" id="name"/">
                                <span style="font-size:10px"><button type="submit" id="select" >查询</button> </span>
                                
                            </div>
                        </form>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>操作</th>
			</tr>
		</thead>
		<%--<c:forEach/>标签遍历List--%>
		<c:forEach items="${list}" var="student">
			<tbody>
				<tr>
					<td>${student.id }</td>
					<td>${student.name }</td>
					<td>${student.sex }</td>
					<td>${student.age }</td>
					<td><a href="studentServlet?do=editbefore&id=${student.id }">修改</a>
						<a href="studentServlet?do=del&id=${student.id }"
						onclick="javascript:return confirm('确认删除吗？');">删除</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
$(function () {
        $("#name").change(function() {
            var a=$("#name").val().trim();
            if(null == a || a.length == 0){
                $("#select").attr('disabled','disabled');

            }else{
                $("#select").removeAttr("disabled");
            }

        });
    });
</script>
</html>