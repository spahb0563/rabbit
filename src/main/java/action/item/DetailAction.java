package action.item;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wishlist.WishListDAO;
import wishlist.WishListDTO;
import command.CommandAction;
import file.FileDAO;
import file.FileDTO;
import item.*;
import member.LoginMemberDTO;

public class DetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		LoginMemberDTO member = (LoginMemberDTO)request.getSession().getAttribute("member");
		
		ItemDAO itemDAO = ItemDAO.getDao();
		FileDAO fileDAO = FileDAO.getDao();
		WishListDAO wishListDAO = WishListDAO.getDao();
		
		Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        Integer member_id = member != null ? member.getId() : 0;
        Integer item_id = Integer.parseInt(request.getParameter("id"));
        WishListDTO wishListDTO = new WishListDTO();
        
        wishListDTO.setItem_id(item_id);
        wishListDTO.setMember_id(member_id);
        
        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals(member_id+"_visited")) {
                    cookie = c;
                    break;
                }
            }
        }

        if(cookie != null) {
        	if(!cookie.getValue().contains("["+item_id+"]")) {
	        	itemDAO.plusViewCount(item_id);
	            cookie.setValue(cookie.getValue()+"["+item_id+"]");
	            cookie.setPath("/");
	            cookie.setMaxAge(60 * 60 * 24);
	            response.addCookie(cookie);
        	}
        }else {
        	itemDAO.plusViewCount(item_id);
            Cookie newCookie = new Cookie(member_id+"_visited", "["+item_id+"]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
		
		
		ItemDetailDTO itemDetailDTO = itemDAO.getItemDetail(item_id);		
		List<FileDTO> fileList = fileDAO.getFileList(item_id);
		int like = wishListDAO.check(wishListDTO);
		
		request.setAttribute("item", itemDetailDTO);
		request.setAttribute("fileList", fileList);
		request.setAttribute("like", like);
		
		return "/item/detail.jsp";
	}
}
