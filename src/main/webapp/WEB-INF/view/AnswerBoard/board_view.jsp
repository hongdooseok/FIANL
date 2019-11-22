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
<body>
	<table width=50% boarder=1 cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td style="font-size:12" height="16"> <spring:message code="board_title" /> </td>
			<td style="font-size:12" align="right"> 
				조회수 : ${board.boardReadcount } | 등록일 <fmt:formatDate value="${board.boardDate }" type="date"/>
			</td>
		</tr>
		
		<tr align="center" valign="middle">
			<td style="font-size:12" height="16"> 글쓴이  </td>
			<td style="font-size:12"> ${board.boardName }  </td>
		</tr>
		
		<tr align="center" valign="middle">
			<td style="font-size:12" height="16"> 제목  </td>
			<td style="font-size:12"> ${board.boardSubject }  </td>
		</tr>
		
		<tr align="center" valign="middle">
			<td style="font-size:12" height="16"> 내용  </td>
			<td style="font-size:12"> ${board.boardContent }  </td>
		</tr>
		
		<tr>
			<td style="font-size:12" height="16"> 파일  </td>
			<td style="font-size:12"> 
				<c:forEach var="store" items="${store }" varStatus="status" >
					<a href="../AnswerBoard/upload/${store1}"> ${original[status.index] } </a><br/> 
					<!--  /MVCSpringProject1/WEB-INF/view/AsswerBoard/upload/${store1} -->
					<!-- 이거는 ㄹㅇ 이해 필요 읽어봐야함  original 보옂는 이름 store는 실제 저장된 이름 그니까 실제 저장된곳을 링크 걸어야하잖아 upload / store << 이거는 파일명임 -->
				</c:forEach>
			</td>
		</tr>
		
			<tr align="center" valign="middle">
			<td colspan="2">
 				<a href="answerBoardReply?num=${board.boardNum }" >[답변]</a> |
 				<a href="answerBoardUpdate?num=${board.boardNum }" >[수정]</a> |
 				<a href="answerBoardDelete?num=${board.boardNum }" >[삭제]</a> |
 				<a href="answerBoardList" >[목록]</a>
			</td>
		</tr>
		
		
	</table>
</body>
</html>