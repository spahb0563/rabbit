<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>"/>
    
<c:remove var="member" scope="session"/> <%-- 변수제거 세션 무효화 --%>

<meta http-equiv="Refresh" content="0;url=${ctxpath}/main/viewMain.do" />