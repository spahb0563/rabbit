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
        <h2>구매내역</h2>
        <c:if test="${count==0}">
            구매한 내역이 없습니다
        </c:if>
        <c:if test="${count>0}">
            <div class="border-top pt-3 text-left">
                <div class="px-3 mb-5">
                    <ul class="list-group list-group-flush">
                        <c:forEach var="item" items="${list}">
                            <li class="d-flex list-group-item border-0 py-1">
                               <a class="h-3 btn btn-light btn-block" href="${ctxpath}/item/detail.do?id=${item.item_id}">
		                            <div class="d-flex">
		                                <div>
		                                    <img style="width: 100px; height: 100px" src="/img/${item.savedname}">
		                                </div>
		                                <div class="w-100 ml-3">
		                                    <span class="d-block h4 text-left" style="word-wrap:break-word;">${item.title}</span>
		                                    <span class="d-block text-left" style="word-wrap:break-word;">
		                                    ${item.created_at}
		                                    </span>
		                                    <span class="d-block text-left" style="word-wrap:break-word;"><span class="badge badge-pill badge-warning">${item.status}</span><fmt:formatNumber value="${item.price}" type="number"/>원</span>
		                                </div>
		                            </div>
			                    </a>
                            </li>
                            <div class="px-3 row">
		                    	<div class="col text-right mx-1">
			                    	<button type="button" class="btn btn-warning write_review" value="${item.seller_id}" data-toggle="modal" data-target="#review">
			                    	후기쓰기
			                    	</button>
								</div>
							</div>		
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
<div class="modal fade" id="review" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">거래후기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" id="target_id">
                        <input type="hidden" id="writer_id" value="${sessionScope.member.id}">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">거래후기:</label>
                        <textarea class="form-control" id="content" rows="2"></textarea>
                    </div>
                    <div class="form-group">
                       	<select class="form-control" id="selectContent">
							 <option value="" selected>직접입력</option>
							 <option value="친절하고 매너가 좋아요" >친절하고 매너가 좋아요</option>
							 <option value="응답이 빨라요">응답이 빨라요</option>
							 <option value="시간 약속을 잘 지켜요">시간 약속을 잘 지켜요</option>
							 <option value="제가 있는 곳까지 와서 거래했어요">제가 있는 곳까지 와서 거래했어요</option>
							 <option value="좋은 상품을 저렴하게 판매해요">좋은 상품을 저렴하게 판매해요</option>
							 <option value="나눔을 해주셨어요">나눔을 해주셨어요</option>
						</select>
					</div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
                <button type="submit" class="btn btn-warning" id="btn-writeReview">작성하기</button>
            </div>
        </div>
    </div>
</div>
</html>