package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FileDAO {
	//전역변수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt=null;
		String sql="";

		private FileDAO(){}//생성자, private는 외부에서 생성 못하게 함

		private static FileDAO instance=new FileDAO();//싱글톤 객체 사용(메모리 절약효과)

		public static FileDAO getDao(){//jsp에서 사용할 메서드

			return instance;
		}//getDao()-end

		private Connection getCon() throws Exception{
			Context ct=new InitialContext();
			DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
			return ds.getConnection();
		}//getCon()-end
		
		public FileDTO getFile(Integer item_id) {
			FileDTO dto = new FileDTO();
			try{
				con=getCon();
				pstmt=con.prepareStatement("select * from file where item_id = ? order by id asc limit 1");
				
				pstmt.setInt(1, item_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					dto.setId(rs.getInt("id"));
					dto.setSavedname(rs.getString("savedname"));
				}
			}catch(Exception ex){
				System.out.println("getFile 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			return dto;
		}
		
		/////////
		//파일 업로드
		/////////
		public void insertFile(FileDTO dto) {
			try{
				con=getCon();
				pstmt=con.prepareStatement("insert into file (name, savedname, path, size, type, created_at, item_id) values (?, ?, ?, ?, ?, now(), ?)");
				
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getSavedname());
				pstmt.setString(3, dto.getPath());
				pstmt.setLong(4, dto.getSize());
				pstmt.setString(5, dto.getType());
				pstmt.setInt(6, dto.getItem_id());
				
				pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("insertFile 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		}
		
		public List<FileDTO> getFileList(Integer item_id) {
			List<FileDTO> list = new ArrayList<>(); 
			try{
				con=getCon();
				pstmt=con.prepareStatement("select * from file where item_id = ? order by id asc");
				
				pstmt.setInt(1, item_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					FileDTO dto = new FileDTO();
					dto.setId(rs.getInt("id"));
					dto.setSavedname(rs.getString("savedname"));
					
					list.add(dto);
				}
			}catch(Exception ex){
				System.out.println("getFIleList 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			return list;
		}
		
		public void deleteFile(Integer file_id) {
			try {
				con=getCon();
				pstmt=con.prepareStatement("delete from file where id = ?");
				
				pstmt.setInt(1, file_id);
				
				pstmt.executeUpdate();
				
			}catch (Exception ex) {
				System.out.println("delete() 예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			
		}
		
}
