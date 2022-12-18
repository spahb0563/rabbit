package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class InfoProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		//infoForm이 보내준 data
		int id=Integer.parseInt(request.getParameter("id"));
		
		MemberDTO dto=new MemberDTO();
		dto.setId(id);
		dto.setEmail(request.getParameter("email"));
		dto.setPassword(request.getParameter("password"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		
		dto.setZipcode(request.getParameter("zipcode"));

		dto.setAddress(request.getParameter("address"));
		
		MemberDAO dao=MemberDAO.getDao();
		dao.updateMember(dto);//메서드 호출
		
		//jsp에서 사용할 값 설정
		request.setAttribute("id", id);
		
		
		return "/member/infoPro.jsp";
	}

}
