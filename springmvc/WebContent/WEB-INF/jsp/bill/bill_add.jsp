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
<title>新增账单</title>
</head>
<body>

<br> 新增账单详情
<form action="<%=basePath%>/admin/doAddBill.action" method="post">
<br>
消费金额 单位元:<input type="text" name="bill.amount" value="" ><br>
手续费用 单位（元）:<input type="text" name="bill.ltlExp" value="" ><br>
终端号:<input type="text" name="bill.terminalNo" value="" ><br>
终端名称:<input type="text" name="bill.terminalName" value="" ><br>
银行卡号:<input type="text" name="bill.cardNo" value="" ><br>
商户Id:<input type="text" name="bill.merchantId" value="" ><br>
代理商ID:<input type="text" name="bill.agentId" value="" ><br>
状态:<select name="bill.status">
<option value="0">初始化</option>
<option value="1">成功</option>
<option value="2">失败</option>
</select><br>
<input type="submit" value="保存">
<br><br>
<br>
<a href="<%=basePath%>/admin/getbilllist.action">返回</a>
</form>

<!-- `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `amount` decimal(10,0) DEFAULT '0' COMMENT '消费金额 单位元',
  `ltl_exp` decimal(10,0) DEFAULT '0' COMMENT '手续费用 单位（元）',
  `terminal_no` varchar(16) DEFAULT NULL COMMENT '终端号',
  `terminal_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '终端名称',
  `card_no` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `merchant_id` varchar(30) DEFAULT NULL COMMENT '商户Id',
  `agent_id` varchar(30) DEFAULT NULL COMMENT '',
  `status` char(1) DEFAULT '0' COMMENT '状态 0 初始化  1 成功  2 失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间', -->



</body>
</html>