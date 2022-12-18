package action.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import msg.MsgDAO;
import msg.MsgListDTO;

public class ContentFormAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		MsgListDTO dto = MsgDAO.getDao().getMsg(id);
		
		request.setAttribute("msg", dto);
		
		return "/msg/contentForm.jsp";
	}
}
