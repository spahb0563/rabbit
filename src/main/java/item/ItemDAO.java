package item;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import item.*;
import member.*;

import java.util.*;
import java.time.LocalDateTime;

public class ItemDAO {
	//전역변수
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Statement stmt=null;
	String sql="";

	private ItemDAO(){}//생성자, private는 외부에서 생성 못하게 함

	private static ItemDAO instance=new ItemDAO();//싱글톤 객체 사용(메모리 절약효과)

	public static ItemDAO getDao(){//jsp에서 사용할 메서드

		return instance;
	}//getDao()-end

	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end



	//====================
	// 카테고리
	//====================

	
	
	//===========================
	// saleList
	// 글 갯수,페이지,블럭처리에 필요함
	//===========================
	public int getTotalCount(){
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from item");

			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt; //총 글 갯수
	}//getCount()
	
	public int getSaleListCount(Integer seller_id) {
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from item where seller_id = ?");
			
			pstmt.setInt(1, seller_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt; //판매내역 글 갯수
	}//getSaleListCount()-end
	
	public int getBuyListCount(Integer buyer_id) {
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from item where buyer_id = ?");
			
			pstmt.setInt(1, buyer_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt; //판매내역 글 갯수
	}//getSaleListCount()-end
	
	
	//==============
	// getSalelist
	//==============
	public List getSaleList(Integer seller_id, int start, int cnt){

		List<ItemDTO> slist=null;

		try {
			con=getCon();
			sql="select * from item where seller_id = ? order by id desc limit ?,?"; //id 내림차순 정렬
			pstmt=con.prepareStatement(sql); //생성시 인자들어간다

			//?값 채우기
			pstmt.setInt(1, seller_id);
			pstmt.setInt(2, start-1); //0부터 시작하려고 -1
			pstmt.setInt(3, cnt);

			rs=pstmt.executeQuery();

			//rs내용을 dto에 담고
			//dto를 list에 넣는다
			//list를 리턴한다

			if(rs.next()){
				slist=new ArrayList();
				do{	//rs.next로 하나를 받았으므로 do-while 사용
					ItemDTO dto=new ItemDTO();

					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));

					dto.setPrice(rs.getBigDecimal("price"));
					dto.setStatus(rs.getString("status"));
					dto.setView_count((rs.getInt("view_count")));
					dto.setLike_count(rs.getInt("like_count"));

					dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					dto.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

					dto.setCategory_id(rs.getInt("category_id"));
					dto.setSeller_id(rs.getInt("seller_id"));
					dto.setBuyer_id(rs.getInt("buyer_id"));

					slist.add(dto); //***

				}while(rs.next());
			}//if-end

		} catch (Exception ex) {
			System.out.println("getSaleList()예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally

		return slist;
	}//getSaleList()
	
	//==============
	// getBuyerlist
	//==============
		public List getBuyerList(Integer buyer_id, int start, int cnt){

			List<ItemDTO> blist=null;

			try {
				con=getCon();
				sql="select * from item where buyer_id = ? order by id desc limit ?,?"; //id 내림차순 정렬

				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, buyer_id);
				pstmt.setInt(2, start-1); //0부터 시작하려고 -1
				pstmt.setInt(3, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					blist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						ItemDTO dto=new ItemDTO();

						dto.setId(rs.getInt("id"));
						dto.setTitle(rs.getString("title"));
						dto.setContent(rs.getString("content"));

						dto.setPrice(rs.getBigDecimal("price"));
						dto.setStatus(rs.getString("status"));
						dto.setView_count((rs.getInt("view_count")));
						dto.setLike_count(rs.getInt("like_count"));

						dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
						dto.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

						dto.setCategory_id(rs.getInt("category_id"));
						dto.setSeller_id(rs.getInt("seller_id"));
						dto.setBuyer_id(rs.getInt("buyer_id"));

						blist.add(dto); //***

					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("getSaleList()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally

			return blist;
		}//getBuyerList()
		
		//-------------------------------------------------------------------
		//getItem 모든아이템 불러오기
		//-------------------------------------------------------------------
		public ItemDTO getItem(Integer id){
			ItemDTO dto=null;
			
			try{
				con=getCon();
				pstmt=con.prepareStatement("select * from item where id=?");
				
				pstmt.setInt(1, id);
				
				rs=pstmt.executeQuery();
				
				//rs 내용을 dto에 넣고
				//dto를 리턴
				if(rs.next()){
					dto=new ItemDTO(); //객체생성
					
					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					
					dto.setPrice(rs.getBigDecimal("price"));
					dto.setStatus(rs.getString("status"));
					dto.setView_count((rs.getInt("view_count")));
					dto.setLike_count(rs.getInt("like_count"));

					dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					dto.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

					dto.setCategory_id(rs.getInt("category_id"));
					dto.setSeller_id(rs.getInt("seller_id"));
					dto.setBuyer_id(rs.getInt("buyer_id"));
		
				}//if-end
				
			}catch(Exception ex){
				System.out.println("getMember 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			
			return dto;
		}//getMember-end
		
		//----------------------------------------------------
		// likecount method
		//----------------------------------------------------
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class-end
