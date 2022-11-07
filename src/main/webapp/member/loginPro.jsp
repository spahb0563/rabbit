<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp"%>   
<%--loginPro.jsp --%>

<c:set var="ctxpath" value="<%=request.getContextPath()%>" scope="session"/>
<%--로그인 성공 --%>
<c:if test="${x>0}">
<%
 	System.out.println(session.getAttribute("id"));
%>
  <meta http-equiv="Refresh" content="1;url=${ctxpath}/main/viewMain.do">
</c:if>

<%--로그인 실패--%>
<c:if test="${x==0}">
	<script>
  		alert("암호틀림");
  		history.back();
	</script>
</c:if>

<c:if test="${x==-1}">
 <script>
  alert("없는 ID");
  history.back();
 </script>
</c:if>