<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<%-- saleForm.jsp --%>
<html>
<div class="row justify-content-md-center mt-3" style="float: none; margin:100 auto; width:500px">
	<div class="w-100">
		<h3>${member.nickname}님의 프로필</h3>
		<span><a class="text-warning" href="${ctxpath}/item/usersaleList.do?seller_id=${member.id}">판매 물품</a>
			/
			<a class="text-warning" href="${ctxpath}/review/recieveForm.do?seller_id=${member.id}">거래 후기</a></span>
		<c:if test="${count==0}">
			판매한 내역이 없습니다
		</c:if>
		<c:if test="${count>0}">
			<div class="border-top pt-3 text-left">
				<div class="mb-5">
					<ul class="list-group list-group-flush">
						<c:forEach var="item" items="${slist}">
							<li class="list-group-item border-0 py-1 px-0">
								<a class="h-3 btn btn-light btn-block"
									href="${ctxpath}/item/detail.do?id=${item.item_id}">
									<div class="d-flex">
										<div>
											<img style="width: 100px; height: 100px" src="/img/${item.savedname}">
										</div>
										<div class="w-100 ml-3">
											<span class="d-block h2 text-left"
												style="word-wrap:break-word;">${item.title}</span>
											<span class="d-block text-left" style="word-wrap:break-word;">
												${item.created_at}
											</span>
											<span class="d-block text-left" style="word-wrap:break-word;"><span
													class="badge badge-pill badge-warning">${item.status}</span>
												<fmt:formatNumber value="${item.price}" type="number" />원</span>
										</div>
									</div>
								</a>
							</li>
						</c:forEach><!-- 게시물 반복문 -->
					</ul>
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
									<button class="page-link" value="${endPage+1}" aria-label=" Next">
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