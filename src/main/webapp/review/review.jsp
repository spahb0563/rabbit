<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>"/>
<%-- saleForm.jsp --%>
<html>
<div class="row">
	<div class="col-md-9">
	<h3>${member.nickname}님의 프로필</h3>
	
	<span><a href="${ctxpath}/item/usersaleList.do?seller_id=${member.id}">판매 물품(${count})</a></span>
	/
	<span><a href="${ctxpath}/review/review.do?seller_id=${member.id}">거래 후기</a></span>
   
   <c:if test="${count==0}">
   	판매한 내역이 없습니다
   </c:if>
   
  
	</div>
</div>
</html>