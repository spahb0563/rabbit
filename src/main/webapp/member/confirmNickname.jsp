<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--confirmnickname.jsp --%>
<%
String nickname=request.getParameter("nickname");//ajax에서 넘겨준 id
MemberDAO dao=MemberDAO.getDao();
int x=dao.confirmNickname(nickname);//dao 메서드 호출
%>    

<%--json data --%>
{
"x":<%=x %>
}