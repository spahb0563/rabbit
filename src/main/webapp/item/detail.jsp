<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<%-- detail.jsp --%>
<html>
<div class="row justify-content-md-center" style="float: none; margin:100 auto; width:500px">
	<div class="w-100">
	    <div id="itemImages" class="carousel slide w-100" data-ride="carousel" data-interval="false">
	        <div class="carousel-inner">
	        	<c:if test="${fn:length(fileList) == 0}">
		            <div class="carousel-item active">
		                <img src="${ctxpath}/static/images/itemImage.png" alt="당근" class="d-block w-100" style="height: 400px">
		            </div>        		
	        	</c:if>
	        	<c:if test="${fn:length(fileList) > 0}">
	        		<c:forEach var="file" items="${fileList}" varStatus="status">
	        			<c:if test="${status.first}">
				            <div class="carousel-item active">
				                <img src="/img/${file.savedname}" alt="당근" class="d-block w-100" style="height: 400px">
				            </div>
			            </c:if>
			            <c:if test="${!status.first}">
				            <div class="carousel-item">
				                <img src="/img/${file.savedname}" alt="당근" class="d-block w-100" style="height: 400px">
				            </div>
			            </c:if>
	            	</c:forEach>
	            </c:if>
	        </div>
	        <c:if test="${fn:length(fileList) > 1}">
		        <button class="carousel-control-prev" type="button" data-target="#itemImages" data-slide="prev">
		            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		            <span class="sr-only">Previous</span>
		        </button>
		        <button class="carousel-control-next" type="button" data-target="#itemImages" data-slide="next">
		            <span class="carousel-control-next-icon" aria-hidden="true"></span>
		            <span class="sr-only">Next</span>
		        </button>
			</c:if>
	    </div>
	    <div class="border-bottom w-100 py-3">
			<a class="text-decoration-none" href="${ctxpath}/item/usersaleList.do?seller_id=${item.seller_id}">
			<span class="font-weight-bold text-dark">${item.seller_nickname}</span>
			</a> 	    
			<span class="d-block">${item.address}</span>
	    </div>
	    
	    <div class="border-bottom w-100 py-3">
	    	<div class="d-flex justify-content-between align-items-center">
	    		<span class="font-weight-bold h4">${item.title}</span>
	    		<input type="hidden" value="${sessionScope.member.id}" id="member_id"/>
	    		<input type="hidden" value="${item.id}" id="item_id"/>
	    		<button id="btn-wish" class="border-0 p-0 text-danger" style="background-color: transparent;">
	    			<c:if test="${sessionScope.member != null }">
		    			<c:if test="${like==0}">
				    		<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
							  <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
							</svg>
	 	    			</c:if>
		    			<c:if test="${like==1}">
							<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
		        			  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
		        			</svg>
		    			</c:if>
	    			</c:if>
				</button>
	    	</div>
             <div class="d-flex justify-content-between align-items-center">
             	<span class="text-muted"><a class="text-warning text-decoration-none" href="${ctxpath}/search/categorySearch.do?id=${item.category_id}">${item.category}</a> · ${item.updated_at} · ${item.status}</span>
	    		<span class="font-weight-bold h4"><fmt:formatNumber value="${item.price}" type="number"/>원</span>
             </div>
	    </div>
  	   	<div class="contentFiled w-100 py-3 mb-3 border-bottom" style="min-height: 300px">
  			${item.content}
  		</div>
  		<c:if test="${sessionScope.member != null && item.seller_id != sessionScope.member.id}">
	        <div class="row mb-3">
	            <div class="col text-right">
	           		<input type="hidden" value="${item.id}" id="item_id"/>
	                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#msg">
	                <i class="bi bi-envelope"></i>
	                문의하기</button>
	            </div>
	        </div>
        </c:if>
        <c:if test="${item.seller_id == sessionScope.member.id}">
			<div class="row mb-3">
	            <div class="col text-right">
	            	<button type="button" class="btn btn-warning" id="btn-delete">
	            	<i class="bi bi-trash"></i>
	            	삭제하기
	            	</button>
	                <a type="button" class="btn btn-warning" href="${ctxpath}/item/editForm.do?id=${item.id}">
	                <i class="bi bi-pencil-square"></i>
	                수정하기</a>
	            </div>
	        </div>        
        </c:if>
		<div class="modal fade" id="msg" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">쪽지</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <form>
		                <div class="modal-body">
		                    <div class="form-group">
		                        <label for="recipient-name" class="col-form-label">받는사람:</label>
		                        <input type="text" class="form-control" value="${item.seller_nickname}" readonly="readonly">
		                        <input type="hidden" id="receiver_id" value="${item.seller_id}">
		                        <input type="hidden" id="sender_id" value="${sessionScope.member.id}">
		                        <input type="hidden" id="item_id" value="${item.id}">
		                    </div>
		                    <div class="form-group">
		                        <label for="message-text" class="col-form-label">내용:</label>
		                        <textarea class="form-control" id="content" rows="10"></textarea>
		                    </div>
		                </div>
		            </form>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
		                <button type="submit" class="btn btn-warning" id="btn-writeMsg">보내기</button>
		            </div>
		        </div>
		    </div>
		</div>          		
    </div>
</div>
<script src="${ctxpath}/static/app/js/detail.js?ver=3"></script>
</html>