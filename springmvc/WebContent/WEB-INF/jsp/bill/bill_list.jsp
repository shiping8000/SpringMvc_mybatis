<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>交易记录</title>
</head>
<body>

<a href="<%=basePath%>/admin/toAddBill.action">新增账单</a>
<a href="<%=basePath%>/admin/login.action">首页</a>
<br>
<br>

<table>
<tr><td>自增主键</td> <td>消费金额 单位元</td><td>手续费用 单位（元）</td><td>终端号</td><td>终端名称</td><td>银行卡号</td><td>商户Id</td><td>代理商ID</td><td>状态</td><td>创建时间</td> </tr>
<c:forEach items="${billList}" var="billInfo" >
<tr><td>${billInfo.id} </td> <td>${billInfo.amount}</td><td>${billInfo.ltlExp}</td><td>${billInfo.terminalNo }</td><td>${billInfo.terminalName}</td><td>${billInfo.cardNo}</td><td>${billInfo.merchantId }</td><td>${billInfo.agentId }</td><td>${billInfo.status}</td><td>${billInfo.createTime}</td> </tr>
</c:forEach>


</table>


<!-- `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `amount` decimal(10,0) DEFAULT '0' COMMENT '消费金额 单位元',
  `ltl_exp` decimal(10,0) DEFAULT '0' COMMENT '手续费用 单位（元）',
  `terminal_no` varchar(16) DEFAULT NULL COMMENT '终端号',
  `terminal_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '终端名称',
  `card_no` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `merchant_id` varchar(30) DEFAULT NULL COMMENT '商户Id',
  `agent_id` varchar(30) DEFAULT NULL COMMENT '代理商ID',
  `status` char(1) DEFAULT '0' COMMENT '状态 0 初始化  1 成功  2 失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间', -->


</body>
</html>