<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here 비밀번호 수정이욧!!</title>

<c:if test="${empty authInfo}">
	<script type="text/javascript">

		location.href="../main";

	</script>
</c:if>

</head>
<body>
	<form:form action="../edit/changePassword" method="post" commandName="changePwdCommand">
		<spring:message code="password" />
		<form:password path="currentPassword"/>
		<form:errors path="currentPassword"/>
		<input type="submit" value="확	인">
	</form:form>
</body>
</html>