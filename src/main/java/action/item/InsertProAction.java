package action.item;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import command.CommandAction;
import file.FileDAO;
import file.FileDTO;
import item.ItemDAO;
import item.ItemDTO;
import member.LoginMemberDTO;

public class InsertProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		LoginMemberDTO loginMember = (LoginMemberDTO)request.getSession().getAttribute("member");
		
        String uploadPath="\\\\192.168.0.47\\jsp프로젝트\\a조\\images";
        int size=20*1024*1024;
        
        request.setCharacterEncoding("utf-8");
		
		File attachesDir = new File(uploadPath);
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(attachesDir);
        fileItemFactory.setSizeThreshold(size);
        
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		fileUpload.setHeaderEncoding("utf-8");
		
		ItemDAO itemDAO = ItemDAO.getDao();
		FileDAO fileDAO = FileDAO.getDao();
		Integer item_id = null;
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			Collections.reverse(items);
			
	        ItemDTO Itemdto = new ItemDTO();
	        Itemdto.setPrice(new BigDecimal(items.get(0).getString()));
	        Itemdto.setCategory_id(Integer.parseInt(items.get(1).getString()));
	        Itemdto.setContent(items.get(2).getString("utf-8").replace("\r\n","<br>"));
	        Itemdto.setTitle(items.get(3).getString("utf-8"));
	        Itemdto.setStatus("판매중");
	        Itemdto.setSeller_id(loginMember.getId());
			
	        item_id = itemDAO.insertItem(Itemdto); 
	        
	        Collections.reverse(items);
	        
			for(FileItem item : items) {
				if(!item.isFormField()) {
					if(item.getSize() > 0) {
						String separator = File.separator;
						int index = item.getName().lastIndexOf(separator);
						String fileName = item.getName().substring(index + 1);
						String savedname = UUID.randomUUID() + fileName;
						File uploadFile = new File(uploadPath + separator + savedname);
						item.write(uploadFile);
						 
						FileDTO fileDTO = new FileDTO(); 
			            fileDTO.setItem_id(item_id);
			            fileDTO.setName(fileName);
			            fileDTO.setSavedname(savedname);
			            fileDTO.setType(item.getContentType());
			            fileDTO.setPath(uploadPath);
			            fileDTO.setSize(item.getSize());
			            fileDAO.insertFile(fileDTO);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일업로드 중 오류가 발생하였습니다.");
		}
		
		request.setAttribute("item_id", item_id);
		return "/item/insertPro.jsp";
	}
}
