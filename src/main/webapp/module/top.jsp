<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>" />
<%-- top.jsp --%>
<!-- top -->
<div class="container">
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<a class="my-0 mr-md-auto font-weight-normal text-dark h5"
			href="${ctxpath}/main/viewMain.do">Rabbit</a>
		<nav class="my-2 my-md-0 mr-md-3">
			<c:if test="${empty sessionScope.id}">
				<a class="btn btn-outline-primary"
					href="${ctxpath}/member/loginForm.do">로그인</a>
				<a class="btn btn-outline-primary"
					href="${ctxpath}/member/inputForm.do">회원가입</a>
			</c:if>
			<c:if test="${!empty sessionScope.id}">
				<a class="btn btn-outline-primary"
					href="${ctxpath}/item/saleForm.do?seller_id=${sessionScope.id}">판매내역</a>
				<%--seller_id=${id} --%>
				<a class="btn btn-outline-primary"
					href="${ctxpath}/item/buyForm.do?buyer_id=${sessionScope.id}">구매내역</a>
				<a class="btn btn-outline-primary"
					href="${ctxpath}/member/infoForm.do?id=${sessionScope.id}">my 당근</a>
			</c:if>
		</nav>
	</div>
	<div class="nav-scroller py-1 mb-2">
		<nav class="nav d-flex justify-content-between">
			<a class="p-2 text-muted" href="#">World</a> <a
				class="p-2 text-muted" href="#">U.S.</a> <a class="p-2 text-muted"
				href="#">Technology</a> <a class="p-2 text-muted" href="#">Design</a>
			<a class="p-2 text-muted" href="#">Culture</a> <a
				class="p-2 text-muted" href="#">Business</a> <a
				class="p-2 text-muted" href="#">Politics</a> <a
				class="p-2 text-muted" href="#">Opinion</a> <a
				class="p-2 text-muted" href="#">Science</a> <a
				class="p-2 text-muted" href="#">Health</a> <a class="p-2 text-muted"
				href="#">Style</a> <a class="p-2 text-muted" href="#">Travel</a>
		</nav>
	</div>
</div>
<!-- top end -->


