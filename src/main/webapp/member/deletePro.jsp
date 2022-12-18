<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header/header.jsp" %>      
    
<%--deletePro.jsp 
x=1 삭제성공
x=-1 암호틀림

--%>

<%
//이전방법
//session.setAttribute("id", (String)request.getAttribute("id"));
%>

<%--로그인 성공 --%>
<c:if test="${x==1}">
<%--세션무효화 --%>
<c:remove var="member" scope="session"/> <%-- 변수제거 세션 무효화 --%>
  <h3>회원 탈퇴 되었습니다.</h3>
  <%
  Integer id = (Integer)request.getAttribute("id");
  MemberDAO.getDao().deleteMember(id); %>
  
  <meta http-equiv="Refresh" content="1;url=${ctxpath}/main/viewMain.do">
</c:if>

<%--암호가 틀릴때 --%>
<c:if test="${x==-1}">
  <script>
    alert("암호틀림");
    history.back();
  </script>
</c:if>