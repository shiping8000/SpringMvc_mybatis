<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
</head>
<body>

<hr>
用户名： ${user.userName}
<hr>
刷卡金额：${bill.amount }   <a href="<%=basePath%>/admin/getbilllist.action">刷卡详情</a>
<h1> 你好   。。。。小样  、、、</h1>

</body>
</html>