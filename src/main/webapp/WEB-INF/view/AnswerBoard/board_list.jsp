<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width=100% border="1" cellpadding="0" cellspacing="0" align="center">
		<c:if test="${!empty boards }">
			<tr align="center" valign="middle">
				<td align="center" colspan="4"> 답변 게시판 </td>
				<td align="right"> <font size=2> 글 개수 : ${totalCount} </font> </td>
			</tr>
			
			<tr align="center" valign="middle">
				<td width="8%" height="26"> 번호 </td>
				<td width="28%" height="26"> 제목 </td>
				<td width="8%" height="26"> 작성자 </td>
				<td width="8%" height="26"> 날짜 </td>
				<td width="8%" height="26"> 조회수 </td>
			</tr>
			
			<c:forEach var="board" items="${boards }" varStatus="status">
			<tr align="left" valign="middle">
				<td width="8%" height="26"> ${status.count } </td>
				<td width="28%" height="26">
						<c:forEach begin="1" end="${board.boardReLev }" step="1">
							->
						</c:forEach>
					<a href="answerBoardDetail?num=${board.boardNum }"> 
					${board.boardSubject } 
					<c:if test="${board.originalFilename != null }">
						<img src=""/>
					</c:if>		
				</a>
				</td>
				<td width="8%" height="26"> ${board.boardName } </td>
				<td width="8%" height="26"> <fmt:formatDate value="${board.boardDate }" pattern="yyyy-MM-dd"/> </td>
				<td width="8%" height="26"> ${board.boardReadcount } </td>
			</tr>
			</c:forEach>	
		</c:if>
	
		<c:if test="${empty boards }">
			<tr align="center" valign="middle">
				<td align="center" colspan="4"> 답변 게시판 </td>
				<td align="center"> <font size=2> 등록된 글이 없습니다. </font> </td>
			</tr>
		
		</c:if>
		
			<tr  align="right">
				<td colspan="5"> <a href="answerBoard">[글쓰기]</a> </td>
			</tr>
	</table>
	
	<!-- 페이징을 위한 부분 -->
	<table>
		<tr>
			<td>
				<c:if test="${page <= 1 }">
					 [이전]&nbsp;
				</c:if>
				
				<c:if test="${page > 1 }">
					 <a href="answerBoardList?page=${page -1 }">[이전]</a> 
				</c:if>
				
				<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
					<a href="answerBoardList?page=${i }">[${i}]</a>
				</c:forEach>
				
				<c:if test="${page >= maxPage }">
					[이후]&nbsp;
				</c:if>
				
				<c:if test="${page < maxPage }">
					<a href="answerBoardList?page=${page +1 }">[이후]</a> 
				</c:if>
			</td>
		</tr>
	</table>
	
</body>
</html>