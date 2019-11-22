<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type ="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

	$(function(){
		$("#modify").click(function(){
			location.href="../memberModify";
		});
	});
	
	//이렇게 써버리면 주소가 뒤에 하나 더있음 => member/memberInfo/123123 << 이거지 원래는 member/memberInfo << 이거지 그래서 memberInfo는 파일명이였잖아
	//근데 이렇게 써버리면 member/memberInfo << 이거까지가 디렉토리 내용이고  뒤에 123123이 파일명으로 되버리니까 안되잖아 
	// 바뀌는건 무조건 member/이후가 바뀌는거잖아 그러니까 ..을 붙여서 ㅎㅎ
</script>
</head>
<body>

	이름 : ${member.userName} <br />
	아이디 : ${member.userId} <br />
	이메일 : ${member.userRmail} <br />
	생년월일 : ${member.userBirth} <br />
	
	성별 : <c:choose>
				<c:when test="${member.userGender == 'M'}"> 남자 </c:when>
				<c:when test="${member.userGender == 'F'}"> 여자 </c:when>
		 </c:choose> <br />
		 
	연락처 1 : ${member.userPh1} <br />
	연락처 2 : ${member.userPh2} <br />
	등록일 : ${member.userRegist} <br />
	주소 : ${member.userAddr} <br />
	
	<input type="button" name="modify" id="modify" value="수	정" />
	<input type="button" onclick="javascript:history.back();" value="취	소" />
	<input type="button" name="memDel" id="memDel" value="탈	퇴" /> 
	
</body>
</html>