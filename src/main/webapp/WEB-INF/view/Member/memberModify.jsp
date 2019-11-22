<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type ="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#userId").html($("#id1").val());
		$("#userName").html($("#name").val());
		$("#birth").html($("#userBirth").val() + " / " + $("#userGender").val());
		
		$("#memPw").click(function(){
			location.href="../edit/changePassword";
		});
		
	});
</script>

</head>
<body>

	<form:form name="frm" id="frm" method="post" action="memberModifyPro" commandName="memberCommand">
		<table border=1 width=600 align="center" cellpadding=3>
		
			<tr>
				
				<td colspan="2"> <spring:message code="userModify" /> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="userId.userPw" /> </td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td> <spring:message code="userModify" /> </td>
				<td> <form:hidden path="userId" id="id1"/> <div id = "userId"></div> </td>
				<!-- <input type="text" name="aaa"  readOnly /> readOnly는 수정만 못하는게 아니라 데이터도 서버에 안날라감.!! -->
			</tr>
			
			<tr>
				<td> <spring:message code="password" /> </td>
				<td> <input type="password" id="pw" name="userPw" /><br /> <form:errors path="userPw"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="defaultInfo" /> </td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td> <spring:message code="name" />  </td>
				<td> <div id="userName"></div> <form:hidden path="userName" id="name"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="birth.gender" />  </td>
				<td> <div id="birth"></div> <form:hidden path="userBirth" id="userBirth"/> <form:hidden path="userGender" id="userGender"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="email" /> </td>
				<td> <form:input type="text" id="rmail" path="userRmail" /><br /> <form:errors path="userRmail"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="userAddr" /> </td>
				<td> <form:input type="text" id="addr" path="userAddr" /><br /> <form:errors path="userAddr"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="ph1" /> </td>
				<td> <form:input type="text" id="memberPh1" path="userPh1" /><br /> <form:errors path="userPh1"/> </td>
			</tr>
			
			<tr>
				<td> <spring:message code="ph2" /> </td>
				<td> <form:input type="text" id="memberPh2" path="userPh2" /> </td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" name="modify" id="modify" value="수	정" />
					<input type="button" onclick="javascript:history.back();" value="취	소" />
					<input type="button" id="memPw" value="비밀번호 변경" />
				</td>
			</tr>
		
		</table>
	</form:form>

</body>
</html>