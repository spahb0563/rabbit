package action.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.CategoryDAO;
import category.CategoryDTO;
import command.CommandAction;
import member.LoginMemberDTO;

public class InsertFormAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		
		LoginMemberDTO loginMember = (LoginMemberDTO)session.getAttribute("member");
		
		if(loginMember == null) {
			return "/member/loginForm.jsp";
		}
		
		CategoryDAO dao = CategoryDAO.getDao();
		List<CategoryDTO> categoryList = dao.getAllList();
		
		request.setAttribute("categoryList", categoryList);
		
		return "/item/insertForm.jsp";
	}
}
