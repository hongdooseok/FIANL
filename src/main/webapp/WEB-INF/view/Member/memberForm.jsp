<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register" /></title>
</head>
<body>
	<form:form action="MemberJoinAction" name="frm" id="frm" method="post" commandName="memberCommand"><!-- 컨틑롤러로부터 날라온걸 폼에다가 주게되면 이 페이지가 열릴때 커맨드 객체에 이이름과 같은게 있다면 커맨드 객체이 있는것을 얘한테 준다. -->
		<table width= 600 align="center" border="1">
			<tr>
				<td width = "200"> <spring:message code="member.info" /> </td>
				<td width = "400">
					<form:input path="userId" size="12" maxlength="10" id="userId" />
					<input type="button" name="userConfirm" id="userConfirm" value="중복확인" />
					<form:errors path="userId" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> <spring:message code="password" /> </td>
				<td width = "400">
					<form:password path="userPw" id="userPw" size="12" maxlength="10" /><!-- 커맨드객체의 멤버필드명하고 무조건 같게 준다고 생각을 하자!!!! path="값"을 -->
					<form:errors path="userPw" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> <spring:message code="password.confirm" /> </td>
				<td width = "400">
					<form:password path="userPwCon" id="userPwCon" size="12" maxlength="10" />
					<form:errors path="userPwCon" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> <spring:message code="name" /> </td>
				<td width = "400">
					<form:input path="userName" id="userName" size="12" maxlength="10" />
					<form:errors path="userName" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> 생년월일 </td>
				<td width = "400">
					<form:input  path="userBirth" id="userBirth" size="12" maxlength="10" />
					<form:errors path="userBirth" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> 성별 </td>
				<td width = "400">
					남자 : <input type="radio" name="userGender" id="userGender" value="M" checked="checked" />
					여자 : <input type="radio" name="userGender" id="userGender" value="F"  />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> <spring:message code="email" /> </td>
				<td width = "400">
					<form:input path="userRmail" id="userRmail" size="30" maxlength="30" />
					<form:errors path="userRmail" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> 사용자 주소 </td>
				<td width = "400">
					<form:input path="userAddr" id="userAddr" size="30" maxlength="30" />
					<form:errors path="userAddr" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> 사용자 연락처1 </td>
				<td width = "400">
					<form:input path="userPh1" id="userPh1" size="30" maxlength="20" />
					<form:errors path="userPh1" />
				</td>
			</tr>
			
			<tr>
				<td width = "200"> 사용자 연락처2 </td>
				<td width = "400">
					<form:input path="userPh2" id="userPh2" size="30" maxlength="20" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="<spring:message code="register.btn" />" />
					<input type="reset" value="다시 입력" />
					<input type="button" value="가입 안함" />
				</td>
			</tr>
			
			
		</table>
	</form:form>
</body>
</html>