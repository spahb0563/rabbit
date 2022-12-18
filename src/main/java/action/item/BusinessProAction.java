package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import item.ItemDAO;
import member.LoginMemberDTO;

public class BusinessProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		LoginMemberDTO member = (LoginMemberDTO)request.getSession().getAttribute("member");
		
		Integer item_id = Integer.parseInt(request.getParameter("item_id"));
		
		Integer buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
		
		ItemDAO itemDAO = ItemDAO.getDao();
		
		itemDAO.updateBuyerItem(buyer_id, item_id);
		
		return "/item/businessPro.jsp";
	}
	
}
