<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	게시판 목록<br/><br/>
	
	제목 : ${ title } <br/>
	숫자 : ${ number } <br/>
	작성자 : ${ author } <br/>
<br/>

Employees<br/>
<c:forEach items="${allEmployees}" var="employee">
	<!-- &nbsp 한 칸 공백 -->
	ID : ${employee.employeeId}				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	FirstName : ${employee.firstName}		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	LastName : ${employee.lastName}		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	Email : ${employee.email}				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br/>
	Salary : ${employee.salary}				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	Commission : ${employee.commissionPct}	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	HireDate : ${employee.hireDate}		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	DepartmentName : ${employee.departmentName }
	<br/><br/>
</c:forEach>
</body>
</html>