<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>"/>
<%-- buyForm.jsp --%>
<html>
<div class="row">
	<jsp:include page="/module/left.jsp" flush="false"/>
	<div class="col-md-9">
		<h2>구매내역</h2>
		
		<c:if test="${count==0}">
		구매한 내역이 없습니다
		</c:if>
		
		<c:if test="${count>0}">
			<form name="buyForm" method="post" action="${ctxpath}/item/buyPro.do">
				<table>
					<c:forEach var="dto" items="${list}">
						<tr>
							<td><img src="semple.png">img</td>
							<td>
							${dto.status} / ${dto.title}<br>
							${dto.price} / ${dto.updated_at}
							</td>
							<td>
							<input type="button" value="수정" onclick="location.href='${ctxpath}/item/detail.jsp'">
							</td>
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
				   <a href="${ctxpath}/item/buyForm.do?buyer_id=1&pageNum=${startPage-10}">[이전블럭]</a>
				   </c:if>
				   
				   <%-- 페이지 처리 --%>
				   <c:forEach var="i" begin="${startPage}" end="${endPage}">
				   <a href="${ctxpath}/item/buyForm.do?buyer_id=1&pageNum=${i}">[${i}]</a>
				   </c:forEach>
				   <%-- 다음 블럭 --%>
				   <c:if test="${endPage<pageCount}">
				   <a href="${ctxpath}/item/buyForm.do?buyer_id=1&pageNum=${startPage+10}">[다음블럭]</a>
				   </c:if>
				   
				   </c:if>
				   </td>
				</tr>
				</table>
			</form>
		</c:if>
	</div>
</div>
</html>