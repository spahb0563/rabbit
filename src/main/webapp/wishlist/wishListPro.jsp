<%@page import="wishlist.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	request.setCharacterEncoding("UTF-8");
	
	int result = 0;
	
	WishListDTO dto = new WishListDTO();
	String member_id= request.getParameter("member_id");
	String item_id= request.getParameter("item_id");
	
	WishListDAO wishListDAO=WishListDAO.getDao();
	dto.setMember_id(Integer.parseInt(member_id));
	dto.setItem_id(Integer.parseInt(item_id));
	int like = wishListDAO.check(dto);
		
	if(like == 0) {
		result = wishListDAO.like(dto);
	}else {
		result = wishListDAO.cancelLike(dto);	
	}
%>
{
"like" : <%=result%>
}