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
	<h3>나의 프로필</h3>
	
	<span><a href="${ctxpath}/item/saleForm.do">판매 물품</a></span>
	/
	<span><a href="${ctxpath}/review/review.do">거래 후기(${count})</a></span>
	
	<div>
	<h4>$내가 받은 후기글</h4>
	
	<c:if test="${count==0}">
	받은 후기글이 없습니다
	</c:if>
	
	<c:if test="${count>0}">
		<form name="recieveForm" method="post" >
			<div>
				<c:forEach var="dto" items="${rlist}">
					<div>
						<div>
						 ${dto.writer_id}님께 받은 후기입니다<br>
						</div>
						<div>
						${dto.content} / ${dto.created_at}
						<hr>
						</div>
					</div>
				</c:forEach>
			</div>
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
	</div>
</body>
</html>