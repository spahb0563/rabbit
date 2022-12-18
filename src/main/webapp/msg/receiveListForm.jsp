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
        <h2>받은 쪽지함</h2>
        <c:if test="${count==0}">
            받은 쪽지가 없습니다.
        </c:if>
        <c:if test="${count>0}">
			<div class="border-top pt-5">
                <div class="px-3 mb-5">
                    <ul class="list-group list-group-flush border-bottom">
                    	<c:forEach var="msg" items="${list}">
	                        <li class="list-group-item pb-1 mb-1 px-0">
	                            <div class="mt-1" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
	                                <span>
                      					<fmt:parseDate value="${msg.created_at}" pattern="yyyy-MM-dd'T'HH:mm" var="created_at" type="both"/>
										<fmt:formatDate value="${created_at}" pattern="yy/MM/dd"/>
	                                </span>
	                            </div>
	                            <div class="mt-1" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
	                                <a class="text-decoration-none <c:if test="${msg.status == 0}">text-body</c:if> <c:if test="${msg.status == 1}">text-muted</c:if>" href="${ctxpath}/msg/contentForm.do?id=${msg.id}"><b>${msg.sender_nickname}님이 보낸 메세지</b></a>
	                            </div>
	                        </li><!-- 알림 반복문 -->
                        </c:forEach>
                    </ul><!-- 알림 목록 -->
                </div>
            	<div>
		 			<nav aria-label="Page navigation example">
                        <ul class="pagination pagination justify-content-center">
                            <c:if test="${endPage>pageCount}">
                                <c:set var="endPage" value="${pageCount}"></c:set>
                            </c:if>
                            <c:if test="${startPage>5}">
                                <li class="page-item">
                                    <button class="page-link" value="${startPage-1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </button>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                    <button class="page-link" value="${i}">${i}</button></li>
                            </c:forEach>
                            <c:if test="${endPage<pageCount}">
                                <li class="page-item">
                                    <button class="page-link" value="${endPage+1}"
                                    aria-label=" Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </button>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
	            </div>
              </div>            
        </c:if>
    </div>
</div>
</html>