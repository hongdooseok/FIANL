<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="board_title"/></title>
</head>
<body>
<!-- 서브밋했을때 전송했는데 잘못되서 리턴됐잖아 그 내용이 다 날라가지않고 남기고 싶다 그럼 form:form을 써라 -->
	<form:form action="answerBoardWritePro" method="post" commandName="answerBoardCommand" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="2"> 답변형 자료실 </td>
			</tr> 
			
			<tr>
				<td style="font-size:12" height="16"> <div align="center"> 글쓴이 </div> </td> 
				<td> <form:input path="boardName" size="10" maxlength="10" /> </td>
			</tr>
			
			<tr>
				<td style="font-size:12" height="16"> <div align="center"> 비밀번호 </div> </td>  
				<td> <form:password path="boardPass" size="20" maxlength="20" /> </td>
			</tr>
			
			<tr>
				<td style="font-size:12" height="16"> <div align="center"> 제목 </div> </td>  
				<td> <form:input path="boardSubject" size="10" maxlength="10" /> </td>
			</tr>
			
			<tr>
				<td style="font-family:돋움; font-size:12"> <div align="center"> 내용  </div> </td>  
				<td> <form:textarea path="boardContent" cols="67" rows="15"/> </td>
			</tr>
			
			<tr>
				<td style="font-family:돋움; font-size:12"> <div align="center"> 파일  </div> </td>  
				<td> <input type="file" name="report" multiple="multiple"/> </td> 
				<!-- form 파일이 없는 이유는 커맨드로 받아올 수가 없으니까 -->
				<!-- 같은 폴더에 있으면  여러개 선택이 가능하지만.. 다른폴더에 있으면 새로 하나 만들어 줘야한다. -->
			</tr>
			
			<tr>
				<td colspan="2"> 
					<input type="submit" value="등록" />
					<input type="button" value="뒤로" onclick="javascript:history.back();"/>
				</td>
			</tr>
			
		
		</table>
	</form:form>		
</body>
</html>