<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 환영</title>
</head>
<body>
	<p><spring:message code="register.done" arguments="${memberCommand.userName}, ${memberCommand.userRmail}"/></p>
	<p><a href="<c:url value='/main' />">[<spring:message code="go.main" />]</a><!-- c태그를 이용하면 컨택스트 패스 이후의 경로임 ! 결국 /를 붙여줘야한다? -->
</body>
</html>