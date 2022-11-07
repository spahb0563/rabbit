package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class InfoFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		MemberDAO dao=MemberDAO.getDao();
		
		MemberDTO dto= dao.getMember(id);		
		
		request.setAttribute("dto", dto);	
		
		return "/member/infoForm.jsp";
	}

}
