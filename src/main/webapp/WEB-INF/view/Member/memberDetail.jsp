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
			location.href="memberModify";
		});
	});
	
	
</script>
<!-- 왜 주려고하냐 get방식으로 우리가 주소를 치고 들어가면 되요 ? 안되요? 그럼 안되죠!! -->
<c:if test="${empty authInfo}">
	<script type="text/javascript">

		location.href="../main";
		//현주소 http://localhost:8080/MVCSpringProject1/member/memberDetail
		// 가고 싶은 곳은 ?http://localhost:8080/MVCSpringProject1/main
		// 그러려면 member이 없어져야 하잖아 그러니까 ..을 쳐준거다.!!
	
	</script>
</c:if>

</head>
<body>
	<!-- meber.은 어디서"? memberDetilController의 model에 저장한값..(DTO????) -->
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