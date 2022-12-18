<%@page import="member.LoginMemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />   
<%--loginPro.jsp --%>

<%--로그인 성공 --%>
<c:if test="${x==1}">
<%
	String email = (String)request.getAttribute("email");
	LoginMemberDTO dto = MemberDAO.getDao().getLoginMember(email);
 	session.setAttribute("member", MemberDAO.getDao().getLoginMember(email));
%>
  <meta http-equiv="Refresh" content="0;url=${ctxpath}/main/viewMain.do">
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