<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<%-- buyForm.jsp --%>
<html>
<div class="row">
    <jsp:include page="/module/left.jsp" flush="false" />
    <div class="col-md-9">
        <h2>구매자 선택</h2>
        <c:if test="${list==null}">
            쪽지를 주고 받은 회원이 없습니다.
        </c:if>
        <form style="min-height: 300px" action="${ctxpath}/item/businessPro.do">
	        <c:if test="${list!=null}">
	        	<ul class="list-group">
		           	<c:forEach var="business" items="${list}">
					<li class="list-group-item list-group-item action d-flex justify-content-betweenalign-items-center">
						<input class="mr-3" type="radio" aria-label="Radio button for following text input" name="buyer_id" value="${business.id}">
						<a class="text-warning" href="${ctxpath}/item/usersaleList.do?seller_id=${business.id}">${business.nickname}</a>님을 구매자로 선택합니다.
					</li>
					</c:forEach>
				</ul>
	        <input type="hidden" name="item_id" value="${item_id}">
	        <div class="col text-right mt-5 px-0">
				<input type="submit" class="btn btn-warning" value="거래 완료">
	        </div>
	        </c:if>
        </form>
    </div>
</div>
</html>