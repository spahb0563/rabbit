package action.item;

import item.ItemDAO;
import item.ItemDTO;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import address.AddressDAO;
import address.AddressDTO;
import command.CommandAction;
import category.*;
import item.*;

public class CategoryAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		Integer category_id = Integer.parseInt(request.getParameter("id"));
		String pageNum=request.getParameter("pageNum");		
		
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
		
	    if(city != null && city.equals("세종")) city = "세종특별자치시";
	    if(city != null && city.equals("제주")) city = "제주특별자치도";
	      
	    String address = city + " ";
	    address += district == null ? "" : district; 
		
		List<MainItemListDTO> citemlist  = Collections.EMPTY_LIST;
		  
		AddressDTO districtList = null;
		ItemDAO dao=ItemDAO.getDao();
		AddressDAO addressDAO=AddressDAO.getDao();
		if(city != null) {
		      count = dao.getCategoryListByAddressCount(category_id, address);
			  citemlist = dao.getCategoryListByAddress(category_id, address, startRow, pageSize);
			  districtList = addressDAO.getDistrictList(city);
		}else {
			  count=dao.getCategoryListCount(category_id); //총 글갯수
			  citemlist=dao.getCategoryList(category_id, startRow, pageSize);
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
		
		request.setAttribute("citemlist", citemlist);
		request.setAttribute("cityList", addressDAO.getcityList());
		request.setAttribute("districtList", districtList);
      
		request.setAttribute("category_id", category_id);
		return "/item/categoryList.jsp";
	}

}
