<%@page import="msg.MsgDAO"%>
<%@page import="msg.MsgDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	MsgDTO dto = new MsgDTO();
	
	dto.setContent(request.getParameter("content").replace("\r\n","<br>"));
	dto.setSender_id(Integer.parseInt(request.getParameter("sender_id")));
	dto.setReceiver_id(Integer.parseInt(request.getParameter("receiver_id")));
	dto.setItem_id(Integer.parseInt(request.getParameter("item_id")));
	
	MsgDAO dao = MsgDAO.getDao();
	dao.writeMsg(dto);
%>
{
}