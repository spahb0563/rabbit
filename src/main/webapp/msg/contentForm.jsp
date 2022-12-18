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
        <h2><a class="text-warning" href="${ctxpath}/item/detail.do?id=${msg.item_id}">${msg.item_title}</a>에 관한 메세지</h2>
        <div class="border-top pt-3">
            <div>
            	<span class="d-block font-weight-bold h3 my-0">${msg.sender_nickname}</span>	 
                <span class="d-block">
	     		    <fmt:parseDate value="${msg.created_at}" pattern="yyyy-MM-dd'T'HH:mm" var="created_at" type="both"/>
	                <fmt:formatDate value="${created_at}" pattern="yy/MM/dd HH:mm"/>
                </span>
            </div>
            <div class="contentField border-top mt-3 py-3" style="min-height: 300px">
                ${msg.content}
            </div>
	        <div class="row mb-3">
	            <div class="col text-right">
	            	<c:if test="${msg.sender_id != sessionScope.member.id}">
		                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#msg">
							<i class="bi bi-envelope"></i>
		                	답장
		                </button>
	                </c:if>
	            </div>
	        </div>            
        </div>
    </div>
</div>
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
                        <input type="text" class="form-control" value="${msg.sender_nickname}" readonly="readonly">
                        <input type="hidden" id="receiver_id" value="${msg.sender_id}">
                        <input type="hidden" id="sender_id" value="${sessionScope.member.id}">
                        <input type="hidden" id="item_id" value="${msg.item_id}">
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
</html>