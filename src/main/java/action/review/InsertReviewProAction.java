package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.LoginMemberDTO;
import command.CommandAction;
import review.*;//DTO DAO

public class InsertReviewProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		LoginMemberDTO loginMember = (LoginMemberDTO)session.getAttribute("member");
		Integer target_id=Integer.parseInt(request.getParameter("seller_id"));
		
		/*System.out.println("hi");
	      System.out.println("내 이름:"+request.getParameter("writer_id"));
	      System.out.println("남 이름:"+request.getParameter("target_id"));
	      System.out.println("내 글:"+request.getParameter("content"));
		*/
		
		
		ReviewDTO dto=new ReviewDTO();
		
		//클라이언트가 보낸 데이터를 받아서 dto에 넣는다

		dto.setContent(request.getParameter("content"));
		dto.setTarget_id(Integer.parseInt(request.getParameter("target_id")));
		dto.setWriter_id(loginMember.getId());
		
		
		ReviewDAO dao=ReviewDAO.getDao();
		
		dao.insertReview(dto);//메서드 호출(글쓰기 완료)
		
		return "/review/insertReviewPro.jsp";//뷰 리턴

	}

}
