<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<html>
    <div class="album pt-3 pb-1">
        <div class="container">
    	<h3 class="mb-5">검색 결과(${count})</h3>
            <div class="row">
                <c:forEach var="item" items="${itemlist}">
                    <div class="col-md-3">
                    	<a class="text-decoration-none text-dark" href="${ctxpath}/item/detail.do?id=${item.item_id}">
	                        <div class="mb-4 shadow-sm">
	                            <c:if test="${item.savedname == null}">
	                                <img class="bd-placeholder-img card-img-top" src="${ctxpath}/static/images/carrot.png"
	                                    width="100%" height="200" />
	                            </c:if>
	                            <c:if test="${item.savedname != null}">
	                                <img class="bd-placeholder-img card-img-top" src="/img/${item.savedname}" width="100%"
	                                    height="200"/>
	                            </c:if>
	                            <div class="card-body">
	                                <p class="card-text h5" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis">${item.title}</p>
	                                <p class="card-text font-weight-bold"><fmt:formatNumber value="${item.price}" type="number"/>원</p1>
	                                <p class="card-text my-0">${item.address}</p>
	                                <div class="d-flex justify-content-between align-items-center">
	                                    <small class="text-muted">${item.created_at}</small><!-- 몇분전 게시글로 수정 -->
	                                    <small class="text-muted"><i class="bi bi-heart"></i> ${item.like_count} <i class="bi bi-eye"></i> ${item.view_count}</small>
	                                </div>
	                            </div>
	                        </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination justify-content-center">
                        <c:if test="${endPage>pageCount}">
                            <c:set var="endPage" value="${pageCount}"></c:set>
                        </c:if>
                        <c:if test="${startPage>5}">
                            <li class="page-item">
                                <button class="page-link" value="${startPage-1}"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </button>
                            </li>
                        </c:if>
                        <c:forEach var="i" begin="${startPage}" end="${endPage}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <button class="page-link"
                                    value="${i}"
                                    >${i}</button></li>
                        </c:forEach>
                        <c:if test="${endPage<pageCount}">
                            <li class="page-item">
                                <button class="page-link" value="${endPage+1}"
                                    aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </button>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</html>