package action.main;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import address.AddressDAO;
import address.AddressDTO;
import command.CommandAction;
import item.ItemDAO;
import item.MainItemListDTO;

public class MainAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		  String pageNum=request.getParameter("pageNum");//view단에서 페이지 넘길때 요청 받는다
		  
	      if(pageNum==null){
	         pageNum="1";
	      }
	      
	      int currentPage=Integer.parseInt(pageNum);
	      int pageSize=12;
	      
	      int startRow=(currentPage-1)*pageSize+1; //페이지의 시작 row(행) 구한다
	      int endRow=currentPage*pageSize; //페이지의 끝 행
	      
	      int count=0;
	      int number=0;
	      int pageBlock=5;
	      
	      String city = request.getParameter("city");
	      
	      String district = request.getParameter("district");
	      
	      request.setAttribute("c", city);
	      request.setAttribute("d", district);
	      AddressDTO districtList = null;
	      AddressDAO addressDAO=AddressDAO.getDao();
	      if(city != null ) districtList = addressDAO.getDistrictList(city);
	      
	      if(city != null && city.equals("세종")) city = "세종특별자치시";
	      if(city != null && city.equals("제주")) city = "제주특별자치도";
	      
	      String address = city + " ";
	      address += district == null ? "" : district;
	      
	      List<MainItemListDTO> mitemlist = Collections.EMPTY_LIST;
	    		  
	      ItemDAO dao=ItemDAO.getDao();
	      if(city != null) {
		      count = dao.getMainByAddressCount(address);
	    	  mitemlist = dao.getMainByAddress(address, startRow, pageSize);
	      }else {
	    	  count=dao.getTotalCount(); //총 글갯수
	    	  mitemlist=dao.getMainItemList(startRow, pageSize);
	      }
	      
	      
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
	      
	      request.setAttribute("mitemlist", mitemlist);
	      request.setAttribute("cityList", addressDAO.getcityList());
	      request.setAttribute("districtList", districtList);
	      
	      return "/main/main.jsp"; //view return
	}
}
