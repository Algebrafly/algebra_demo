<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入struts2 的标签库--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Origin SSH测试</title>
</head>
<body>
<h2>Hello World!</h2>
<%-- 获取值栈中的user对象的uname的值--%>
<div>用户名： <s:property value="admin.admName"></s:property></div>
</body>
</html>
