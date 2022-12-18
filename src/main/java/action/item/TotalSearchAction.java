package action.item;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import item.ItemDAO;
import item.MainItemListDTO;

public class TotalSearchAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum=request.getParameter("pageNum");//view단에서 페이지 넘길때 요청 받는다
	      if(pageNum==null){
	         pageNum="1";
	      }
	      String keyword = request.getParameter("keyword");
	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=12;
	      
	      int startRow=(currentPage-1)*pageSize+1; //페이지의 시작 row(행) 구한다
	      int endRow=currentPage*pageSize; //페이지의 끝 행
	      
	      int count=0;
	      int number=0;
	      int pageBlock=5;
	      
	      List<MainItemListDTO> itemlist=null;
	      ItemDAO dao=ItemDAO.getDao();
	      count=dao.getSearchCount(keyword);
	      
	      if(count>0){ //글 존재
	    	  itemlist=dao.search(keyword, startRow, pageSize);
	      }else{
	    	  itemlist = Collections.EMPTY_LIST; //비어있음
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
	      
	      request.setAttribute("itemlist", itemlist);	
		
		return "/item/totalSearch.jsp";
	}
}
