package action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

import java.util.*;

import item.*;


public class BuyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		Integer buyer_id = Integer.parseInt(request.getParameter("buyer_id"));	
		String pageNum=request.getParameter("pageNum");		
		
		if(pageNum==null){
			pageNum="1";
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int pageSize=10;
		
		int startRow=(currentPage-1)*pageSize+1; //페이지의 시작 row(행) 구한다
		int endRow=currentPage*pageSize; //페이지의 끝 행
		
		int count=0;
		int number=0;
		int pageBlock=10;
		List list=null;
		//
		ItemDAO dao=ItemDAO.getDao();
		count=dao.getBuyListCount(buyer_id);//판매 내역 글 갯수
		
		if(count>0){ //글 존재
			list=dao.getBuyerList(buyer_id,startRow, pageSize);
		}else{
			list=Collections.EMPTY_LIST; //비어있음
		}//else-end
		
		number=count-(currentPage-1)*pageSize; //출력용 글번호
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		
		int startPage=(int)(currentPage/pageBlock)*10+1;
		int endPage=startPage+pageBlock-1;
		
		//jsp에서 사용할 속성 설정
		request.setAttribute("buyer_id", new Integer(buyer_id));
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("endPage", new Integer(endPage)); 		//객체 데이터
		request.setAttribute("currentPage", new Integer(currentPage));
		
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		
		request.setAttribute("pageBlock", new Integer(pageBlock));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("pageSize", new Integer(pageSize));
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("list", list);
		return "/item/buyForm.jsp";
	}

}

