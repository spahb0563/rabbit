<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>
<%-- saleForm.jsp --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h2>내가 쓴 후기글</h2>
	
	
	<span><a href="${ctxpath}/item/buyForm.do">구매 물품</a></span>
	/
	<span><a href="${ctxpath}/review/sentForm.do">거래 후기(${count})</a></span>
	
	<br>
	<c:if test="${count==0}">
	내가 쓴 후기글이 없습니다 (<a href="${ctxpath}/item/buyForm.do">후기글 쓰러 가기</a>)
	</c:if>
	
	<c:if test="${count>0}">
		<form name="sentForm" method="post" >
			<table>
				<c:forEach var="dto" items="${slist}">
					<tr>
						<td>
						${dto.target}님께 보낸 후기입니다<br>
						${dto.content} / ${dto.updated_at}
						</td>
						<hr>
					</tr>
				</c:forEach>
			</table>
			<%-- 블럭,페이지처리 --%>
			<table class="wid">
			<tr>
			   <td align="center">
			   <c:if test="${count>0}">
			   <%-- 에러방지 --%>
			   <c:if test="${endPage>pageCount}">
			   <c:set var="endPage" value="${pageCount}"></c:set>
			   </c:if>
			   
			   <%-- 이전블럭 --%>
			   <c:if test="${startPage>10}">
			   <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${startPage-10}">[이전블럭]</a>
			   </c:if>
			   
			   <%-- 페이지 처리 --%>
			   <c:forEach var="i" begin="${startPage}" end="${endPage}">
			   <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${i}">[${i}]</a>
			   </c:forEach>
			   <%-- 다음 블럭 --%>
			   <c:if test="${endPage<pageCount}">
			   <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${startPage+10}">[다음블럭]</a>
			   </c:if>
			   
			   </c:if>
			   </td>
			</tr>
			</table>
		</form>
	</c:if>
</body>
</html>