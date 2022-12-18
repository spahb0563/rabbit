<%@page import="review.ReviewDAO"%>
<%@page import="review.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	ReviewDTO dto = new ReviewDTO();
	
	dto.setContent(request.getParameter("content").replace("\r\n","<br>"));
	dto.setTarget_id(Integer.parseInt(request.getParameter("target_id")));
	dto.setWriter_id(Integer.parseInt(request.getParameter("writer_id")));
	
	ReviewDAO dao = ReviewDAO.getDao();
	
	dao.insertReview(dto);
%>
{
}