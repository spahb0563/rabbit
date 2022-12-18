package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import item.ItemDTO;


public class CategoryDAO {
	private static CategoryDAO instance=new CategoryDAO();//객체생성
	
	//생성자
	private CategoryDAO(){} //private 이라 외부에서 객체생성 못함 자신의 클래스에서만 가능
	
	public static CategoryDAO getDao(){ //jsp 사용할 메서드
		return instance;
	}//getDao-end
	
	//커넥션 풀 사용
	private Connection getCon() throws Exception{ //커넥션 풀 메서드
		Context ct=new InitialContext(); //META-INF/context.xml 안의 문장을 축약하여 변수 ct에 할당
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql"); //context.xml에 리소스 네임이 jdbc/oracle이므로 수정
		 //ct변수에서 java:comp/env/jdbc/mysql구문을 찾아 DataSource형식으로 변수 ds에 할당
		
		return ds.getConnection();  //데이터 베이스 연결
	}//Connection-end
	
	//전역변수
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	
	//===============
	//모든 카테고리 리스트
	//===============
	public List<CategoryDTO> getAllList() {
		List<CategoryDTO> list = null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from category");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList();
				do{	
					CategoryDTO dto=new CategoryDTO();

					dto.setId(rs.getInt("id"));
					dto.setType(rs.getString("type"));
					dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					list.add(dto); //***

				}while(rs.next());
			}//if-end
			
		}catch(Exception ex){
			System.out.println("getAllList()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		return list;
	}
	
}
