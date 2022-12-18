package wishlist;

import item.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.MemberDAO;
import wishlist.*;
public class WishListDAO {
	public WishListDAO(){}
	private static WishListDAO instance=new WishListDAO();
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		public static WishListDAO getDao(){ //jsp 사용할 메서드
			return instance;
		}
		
		//커넥션 얻기
		public Connection getCon() throws Exception{
			Context ct=new InitialContext();
			DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
			return ds.getConnection();
		}//getCon()-end
		//----------------------------------------------------------------------
		public int check(WishListDTO wishlistDTO){
			int x = 0;
			try{
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from wishlist where item_id=? and member_id=?");
				pstmt.setInt(1, wishlistDTO.getItem_id());
				pstmt.setInt(2, wishlistDTO.getMember_id());
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}

			}catch(Exception ex){
				System.out.println("insertLike()예외:"+ex);
			}finally{
				try{	
					if(con!=null) {con.close();}
					if(pstmt!=null){pstmt.close();}
					if(rs!=null){rs.close();}
				}catch(Exception exx){}
			}//finally-end
			return x;
		}
//---------------------------------------------------------------------------------------------		
		public int like(WishListDTO wishlistDTO){
			ItemDAO itemDAO = ItemDAO.getDao();
			try{
				con = getCon();
				pstmt=con.prepareStatement("insert into wishlist (item_id, member_id) values(?,?)");
				itemDAO.plusLikeCount(wishlistDTO.getItem_id());
				
				pstmt.setInt(1, wishlistDTO.getItem_id());
				pstmt.setInt(2, wishlistDTO.getMember_id());
				
				pstmt.executeUpdate();// 쿼리 수행
			}catch(Exception ex){
				System.out.println("insertLike()예외:"+ex);
			}finally{
				try{
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception exx){}
			}//finally-end
			return 1;
		}//insertLike()-end
		
		public int cancelLike(WishListDTO wishlistDTO){
			ItemDAO itemDAO = ItemDAO.getDao();
			try{
				con = getCon();
				pstmt=con.prepareStatement("delete from wishlist where item_id=? and member_id=?");
				itemDAO.MinusLikeCount(wishlistDTO.getItem_id());
				
				//?값 채우기
				pstmt.setInt(1, wishlistDTO.getItem_id());
				pstmt.setInt(2, wishlistDTO.getMember_id());
				
				pstmt.executeUpdate();// 쿼리 수행
			}catch(Exception ex){
				System.out.println("insertLike()예외:"+ex);
			}finally{
				try{
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception exx){}
			}//finally-end
			return 0;
		}//insertLike()-end
}
