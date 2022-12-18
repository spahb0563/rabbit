package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import member.*;//DTO,DAO

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
        //deleteForm.jsp보낸 데이터 받기
		
		LoginMemberDTO dto = (LoginMemberDTO)request.getSession().getAttribute("member");
		Integer id = dto.getId();
		String pw=request.getParameter("pw");
		
		//삭제진행
		MemberDAO dao=MemberDAO.getDao();
		int x=dao.userCheck(id, pw);//메서드호출
		
		//deletePro.jsp에서 사용할 속성 설정
		request.setAttribute("id", id);
		request.setAttribute("x", x);
	
		return "/member/deletePro.jsp";//뷰리턴
	}

}
