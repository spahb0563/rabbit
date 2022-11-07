package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import item.*;

public class DetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		ItemDAO dao=ItemDAO.getDao();
		
		ItemDTO itemDTO= dao.getItem(id);		
		
		request.setAttribute("item", itemDTO);
		
		return "/item/detail.jsp";
	}

}
