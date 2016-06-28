<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="loginVO" method="post" action="/HelloMVC/doLogin" enctype="multipart/form-data">
		<input type="text" name="id" />
		<form:errors path="id" cssClass="errors"/><br/>
		<input type="text" name="password" />
		<form:errors path="password" cssClass="errors"/><br/>
		<input type="text" name="memberNumber" />
		<form:errors path="memberNumber" cssClass="errors"/><br/>
		<input type="checkbox" name="enableAutoLogin" value="true" />
		<input type="text" name="hobby" />
		<input type="text" name="hobby" />
		<input type="text" name="hobby" />
		<input type="file" name="uploadFile" />
		<input type="submit" value="제출"/>
	</form:form>
</body>
</html>