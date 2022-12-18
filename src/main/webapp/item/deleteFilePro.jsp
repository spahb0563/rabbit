<%@page import="file.FileDAO"%>
<%@page import="item.ItemDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<%
	Integer file_id = Integer.parseInt(request.getParameter("file_id"));

	FileDAO fileDAO = FileDAO.getDao();

	fileDAO.deleteFile(file_id);
%>
{
}