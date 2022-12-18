package action.item;

import item.ItemDAO;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.LoginMemberDTO;
import member.MemberDAO;
import command.CommandAction;

public class BusinessListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		
		LoginMemberDTO loginMember = (LoginMemberDTO)session.getAttribute("member");
		Integer item_id = Integer.parseInt(request.getParameter("item_id"));

		List businessList=null;
		MemberDAO dao=MemberDAO.getDao();
		businessList=dao.getBusinessList(item_id, loginMember.getId());
		
		request.setAttribute("item_id", item_id);
		request.setAttribute("list", businessList);
		return "/item/businessList.jsp";
	}

}
