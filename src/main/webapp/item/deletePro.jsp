<%@page import="item.ItemDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />

<%
	Integer item_id = Integer.parseInt(request.getParameter("item_id"));

	ItemDAO itemDAO = ItemDAO.getDao();

	itemDAO.delete(item_id);
%>
{
}