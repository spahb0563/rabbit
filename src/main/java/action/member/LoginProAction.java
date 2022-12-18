package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandAction;
import member.*;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		//loginForm.jsp에서 보낸 데이터 받기
		String email=request.getParameter("email");
		String pw=request.getParameter("pw");
		
		MemberDAO dao=MemberDAO.getDao();
		int x = dao.userCheck(email, pw);//메서드 호출
		
		//jsp에서 사용할 속성설정
		request.setAttribute("x", x);
		request.setAttribute("email", email);
		
		return "/member/loginPro.jsp";
	}

}
