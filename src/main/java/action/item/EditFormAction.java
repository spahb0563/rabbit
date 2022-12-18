package action.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.CategoryDAO;
import category.CategoryDTO;
import command.CommandAction;
import file.FileDAO;
import file.FileDTO;
import item.ItemDAO;
import item.ItemDetailDTO;
import member.LoginMemberDTO;

public class EditFormAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
        Integer item_id = Integer.parseInt(request.getParameter("id"));
		
		ItemDAO itemDAO = ItemDAO.getDao();
		FileDAO fileDAO = FileDAO.getDao();
		CategoryDAO dao = CategoryDAO.getDao();
		
		ItemDetailDTO itemDetailDTO = itemDAO.getItemDetail(item_id);
		itemDetailDTO.setContent(itemDetailDTO.getContent().replace("<br>", "\n"));
		List<FileDTO> fileList = fileDAO.getFileList(item_id);
		List<CategoryDTO> categoryList = dao.getAllList();
		
		request.setAttribute("item", itemDetailDTO);
		request.setAttribute("fileList", fileList);
		request.setAttribute("categoryList", categoryList);
		
		return "/item/editForm.jsp";
	}
}
