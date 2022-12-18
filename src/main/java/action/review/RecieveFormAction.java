package action.review;

import item.ItemDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandAction;

import java.util.*;

import review.*;
import member.LoginMemberDTO;
import member.MemberDAO;
import member.MemberDTO;

public class RecieveFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		//세션
/*HttpSession session = request.getSession();
		
		LoginMemberDTO loginMember = (LoginMemberDTO)session.getAttribute("member");
		
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/main/viewMain.do");
			return null;
		}*/




String pageNum=request.getParameter("pageNum");		
//
Integer target_id=Integer.parseInt(request.getParameter("seller_id"));

MemberDAO memberDAO=MemberDAO.getDao();
MemberDTO memberDTO=memberDAO.getMember(target_id);
		
		if(pageNum==null){
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int pageSize=10;
		
		int startRow=(currentPage-1)*pageSize+1; //페이지의 시작 row(행) 구한다
		int endRow=currentPage*pageSize; //페이지의 끝 행
		
		int count=0;
		int number=0;
		int pageBlock=5;
		List rlist=null;
		//
		ReviewDAO dao=ReviewDAO.getDao();
		count=dao.getRecieveReview(target_id);//판매 내역 글 갯수
		
		
		if(count>0){ //글 존재
			rlist=dao.getRecievelist(target_id ,startRow, pageSize);
		}else{
			rlist=Collections.EMPTY_LIST; //비어있음
		}//else-end
		
	    number=count-(currentPage-1)*pageSize; //출력용 글번호
	    int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      
	    int startPage=(int)Math.floor((currentPage-1)/pageBlock)*5+1;
	    int endPage=startPage+pageBlock-1;
	      
	    //jsp에서 사용할 속성 설정
	    request.setAttribute("startPage", new Integer(startPage));
	    request.setAttribute("endPage", new Integer(endPage));       //객체 데이터
	    request.setAttribute("currentPage", new Integer(currentPage));
	      
	    request.setAttribute("startRow", new Integer(startRow));
	    request.setAttribute("endRow", new Integer(endRow));
	      
	    request.setAttribute("pageBlock", new Integer(pageBlock));
	    request.setAttribute("pageCount", new Integer(pageCount));
	    request.setAttribute("pageSize", new Integer(pageSize));
	      
	    request.setAttribute("count", new Integer(count));
	    request.setAttribute("number", new Integer(number));
		
		request.setAttribute("rlist", rlist);
		request.setAttribute("member", memberDTO);
		
		return "/review/recieveForm.jsp";
	}

}
