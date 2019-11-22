<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="term" /></title> <!-- 사용 방법은 label.properties에 있는 내용을 적으면 되요!! -->
</head>
<body>
	<h2><spring:message code="term" /></h2>
	<p>약관 내용</p>
	<form action="regist" method="post">
		<label>
			<input type="checkbox" name="agree" value="true"  /> <spring:message code="term.agree" />
		</label>
		<input type="submit" value="<spring:message code="next.btn" />"  />
	</form>

</body>
</html>