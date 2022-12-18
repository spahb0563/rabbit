package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;
import command.CommandAction;

public class ReviewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		Integer seller_id=Integer.parseInt(request.getParameter("seller_id"));
		
		MemberDAO memberDAO=MemberDAO.getDao();
		MemberDTO memberDTO=memberDAO.getMember(seller_id);
		
		request.setAttribute("member", memberDTO);
		
		return "/review/review.jsp";
	}

}
