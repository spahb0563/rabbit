package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.*;
import command.CommandAction;

public class InfoFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		LoginMemberDTO loginMember = (LoginMemberDTO)session.getAttribute("member");
		
		MemberDAO dao=MemberDAO.getDao();
		
		MemberDTO dto = dao.getMember(loginMember.getId());		
		
		request.setAttribute("dto", dto);	
		
		return "/member/infoForm.jsp";
	}

}
