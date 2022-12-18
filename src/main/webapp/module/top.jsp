<%@page import="category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="category.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>" />
<c:set var="categoryList" value="<%=CategoryDAO.getDao().getAllList() %>" />
<%-- top.jsp --%>
<!-- top -->
<div class="container-md">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom">
		<a class="navbar-brand" href="${ctxpath}/main/viewMain.do"><img src="${ctxpath}/static/images/logo.jpg" style="width:140px"></a>
		<div class="d-flex justify-content-between align-items-center w-100">
	        <form class="pt-3"action="${ctxpath}/item/totalSearch.do" onsubmit="return keywordCheck()">
		      <div class="p-1 bg-light rounded rounded-pill shadow-sm">
	            <div class="input-group align-self-center">
	              <input type="search" placeholder="전체 검색" name="keyword" id="keyword" aria-describedby="button-addon1" class="form-control border-0 bg-light">
	              <div class="input-group-append">
	                <button id="button-addon1" type="submit" class="btn btn-link text-warning"><i class="bi bi-search"></i></button>
	              </div>
	            </div>
	          </div>
	        </form>
	    	<div>
	            <c:if test="${empty sessionScope.member}">
	                <a class="btn btn-outline-warning" href="${ctxpath}/member/loginForm.do"><i class="bi bi-box-arrow-in-left"></i> 로그인</a>
	                <a class="btn btn-outline-warning" href="${ctxpath}/member/inputForm.do"><i class="bi bi-person-plus-fill"></i> 회원가입</a>
	            </c:if>
	            <c:if test="${!empty sessionScope.member}">
	                <a class="btn btn-outline-warning" href="${ctxpath}/member/logout.do"><i class="bi bi-box-arrow-right"></i> 로그아웃</a>
	                <a class="btn btn-outline-warning" href="${ctxpath}/item/insertForm.do"><i class="bi bi-pencil-square"></i> 글쓰기</a>
					<div class="btn-group">
					  <button type="button" class="btn btn-outline-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					  	<i class="bi bi-person-fill"></i>
					    MY 당근
					  </button>
					  <div class="dropdown-menu">
		                <a class="dropdown-item" href="${ctxpath}/member/infoForm.do"><i class="bi bi-person-lines-fill"></i> 내 정보</a>
		                <a class="dropdown-item" href="${ctxpath}/wishlist/wishListForm.do"><i class="bi bi-heart"></i> 위시리스트</a>
		                <a class="dropdown-item" href="${ctxpath}/item/saleForm.do"><i class="bi bi-bag-dash"></i> 판매내역</a>
		                <a class="dropdown-item" href="${ctxpath}/item/buyForm.do"><i class="bi bi-bag-plus"></i> 구매내역</a>
		                <a class="dropdown-item" href="${ctxpath}/msg/receiveListForm.do"><i class="bi bi-envelope-paper"></i> 받은 쪽지함</a>
		                <a class="dropdown-item" href="${ctxpath}/msg/sendListForm.do"><i class="bi bi-envelope"></i> 보낸 쪽지함</a>
					  </div>
					</div>	                
	            </c:if>
            </div>
        </div>
    </div>
    <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between">
            <c:forEach var="category" items="${categoryList}" varStatus="status">
                <a class="p-2 text-muted"
                    href="${ctxpath}/search/categorySearch.do?id=${category.id}">${category.type}</a>
            </c:forEach>
        </nav>
    </div>
</div>
<script>
	function keywordCheck() {
		if($('#keyword').val() == '') {
			alert('검색어를 입력해주세요');
			return false;
		}
	}
</script>
<!-- top end -->