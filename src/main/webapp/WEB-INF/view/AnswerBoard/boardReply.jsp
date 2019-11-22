<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>	<!-- 부모글에 대한 순서를 알기 위해서 히든을 썻다는데 이해 필요! -->
	<form:form action="answerBoardReplyPro" method="post" name="frm"  commandName="boardReplyCommand" enctype="multipart/form-data">

	
		<form:hidden path="boardNum" />
		<form:hidden path="boardReRef" />
		<form:hidden path="boardReLev" />
		<form:hidden path="boardReSeq" />
		
		<table board="1" cellpadding="0" cellspacing="0">
		
			<tr align="center" valign="middle">
				<td colspan="2"> 답글 </td>
			</tr>
			
			<tr>
				<td height="16" align="center"> 글쓴이 </td>
				<td> <form:input path="AnswerBoardCommand.boardName" /> <form:errors path="AnswerBoardCommand.boardName" /></td> <!-- boardReplyCommand요 커맨드에 있는 ///// // 이런식으로 하면 AnswerBoardCommand에 있는 boardName에 저장을 하겠다는 뜻이죠// -->
			</tr>
			
			<tr>
				<td height="16" align="center"> 제목 </td>
				<td> <form:input path="AnswerBoardCommand.boardSubject" size="50" /> <form:errors path="AnswerBoardCommand.boardSubject" /> </td> <!-- value는 커맨드로부터 받아와서 form을 쓰면 받아오지 않아서 여기에 안써도 됩니다. -->
			</tr>
			
			<tr>
				<td height="16" align="center"> 내용 </td>
				<td> <form:textarea path="AnswerBoardCommand.boardContent" cols="67" rows="15" /><form:errors path="AnswerBoardCommand.boardContent" /> </td>
			</tr>
			
			<tr>
				<td height="16" align="center"> 비밀번호 </td>
				<td> <form:password path="AnswerBoardCommand.boardPass"/> <form:errors path="AnswerBoardCommand.boardPass" /> </td>
			</tr>
			
			<tr>
				<td height="16" align="center"> 파일 </td>
				<td> <input type="file" name="reports" multiple="multiple"/></td>
			</tr>
			
			<tr align="center" valign="middle">
				<td colspan="2"> 
					<input type="submit" value="[등록]"/>&nbsp;&nbsp;
					<input type="button" value="[뒤로]" />
				</td>
			</tr>
			
		</table>
	</form:form >
</body>
</html>