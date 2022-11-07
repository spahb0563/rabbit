<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>"/>
<%-- saleForm.jsp --%>
<html>
<div class="row">
	<jsp:include page="/module/left.jsp" flush="false"/>
	<div class="col-md-9">
<h2>판매내역</h2>
   
   <c:if test="${count==0}">
   판매한 내역이 없습니다
   </c:if>
   
   <c:if test="${count>0}">
      <form name="saleForm" method="post" action="${ctxpath}/item/salePro.do">
         <table>
            <c:forEach var="item" items="${list}">
               <tr>
                  <td><a href="detail.do?id=${item.id}"><img src="semple.png">img</a></td>
                  <%-- 메인에서도 seller_id 넘겨올것 --%>
                  <td>
                  ${item.status} / ${item.title}<br>
                  ${item.price} / ${item.updated_at}
                  </td>
                  <td>
                  <input type="button" value="삭제" onclick="location.href='${ctxpath}/template/template.jsp?seller_id=1'">
                  <input type="button" value="수정" onclick="">
                  </td>
               </tr>
            </c:forEach>
         </table>
         <%-- 블럭,페이지처리 --%>
         <table class="wid">
         <tr>
            <td align="center">
            <c:if test="${count>0}">
            <%-- 에러방지 --%>
            <c:if test="${endPage>pageCount}">
            <c:set var="endPage" value="${pageCount}"></c:set>
            </c:if>
            
            <%-- 이전블럭 --%>
            <c:if test="${startPage>10}">
            <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${startPage-10}">[이전블럭]</a>
            </c:if>
            
            <%-- 페이지 처리 --%>
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${i}">[${i}]</a>
            </c:forEach>
            <%-- 다음 블럭 --%>
            <c:if test="${endPage<pageCount}">
            <a href="${ctxpath}/item/saleForm.do?seller_id=1&pageNum=${startPage+10}">[다음블럭]</a>
            </c:if>
            
            </c:if>
            </td>
         </tr>
         </table>
      </form>
   </c:if>
	</div>
</div>
</html>