package item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	// totalCount
	// 전체 글 갯수
	//===========================
	public int getTotalCount(){
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from item i join member m on i.seller_id = m.id");

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
			System.out.println("getSaleListCount()예외:"+ex);
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
			pstmt=con.prepareStatement("select count(*) from (select * from item where buyer_id = ?) i join member m on i.seller_id = m.id");
			
			pstmt.setInt(1, buyer_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getBuyListCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt; //판매내역 글 갯수
	}//getSaleListCount()-end

	public int getWishListCount(Integer member_id) {
		int cnt = 0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from (select * from wishlist where member_id = ?) w join item i on w.item_id = i.id");
			
			pstmt.setInt(1, member_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getWishListCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt;
	}
	
	
	//==============
	// getSalelist
	//==============
	public List<MainItemListDTO> getSaleList(Integer seller_id, int start, int cnt){
		List<MainItemListDTO> sitemlist=null;
		try {
			con=getCon();
			sql="select * from (select * from item where seller_id = ?) i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id left join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
			pstmt=con.prepareStatement(sql); //생성시 인자들어간다

			//?값 채우기
			pstmt.setInt(1, seller_id); //0부터 시작하려고 -1
			pstmt.setInt(2, start-1); //0부터 시작하려고 -1
			pstmt.setInt(3, cnt);

			rs=pstmt.executeQuery();

			//rs내용을 dto에 담고
			//dto를 list에 넣는다
			//list를 리턴한다

			if(rs.next()){
				sitemlist=new ArrayList();
				do{	//rs.next로 하나를 받았으므로 do-while 사용
					MainItemListDTO dto=new MainItemListDTO();
					dto.setMember_id(rs.getInt("m.id"));
					dto.setNickname(rs.getString("m.nickname"));
					dto.setAddress(rs.getString("m.address"));
						
					dto.setItem_id(rs.getInt("i.id"));
					dto.setTitle(rs.getString("i.title"));
					dto.setPrice(rs.getBigDecimal("i.price"));
					dto.setView_count(rs.getInt("i.view_count"));
					dto.setLike_count(rs.getInt("i.like_count"));
					dto.setStatus(rs.getString("i.status"));
					dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
					dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
					
					dto.setSeller_id(rs.getInt("i.seller_id"));

					dto.setFile_id(rs.getInt("f.id"));
					dto.setSavedname(rs.getString("f.savedname"));

					sitemlist.add(dto);
				}while(rs.next());
			}//if-end

		} catch (Exception ex) {
			System.out.println("getSellerItemList()예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return sitemlist;
	}//getSellerItemList-end
	
	public List<MainItemListDTO> getWishList(Integer member_id, int start, int cnt) {
		List<MainItemListDTO> witemlist=null;
		try {
			con=getCon();
			sql="select * from (select * from wishlist where member_id = ?) w join item i on w.item_id = i.id left join (select * from file where id in (select min(id) from file group by item_id)) f on w.item_id = f.item_id join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
			pstmt=con.prepareStatement(sql); //생성시 인자들어간다

			//?값 채우기
			pstmt.setInt(1, member_id); 
			pstmt.setInt(2, start-1); //0부터 시작하려고 -1
			pstmt.setInt(3, cnt);

			rs=pstmt.executeQuery();

			//rs내용을 dto에 담고
			//dto를 list에 넣는다
			//list를 리턴한다

			if(rs.next()){
				witemlist=new ArrayList();
				do{	//rs.next로 하나를 받았으므로 do-while 사용
					MainItemListDTO dto=new MainItemListDTO();

					dto.setMember_id(rs.getInt("m.id"));
					dto.setNickname(rs.getString("m.nickname"));
					dto.setAddress(rs.getString("m.address"));
						
					dto.setItem_id(rs.getInt("i.id"));
					dto.setTitle(rs.getString("i.title"));
					dto.setPrice(rs.getBigDecimal("i.price"));
					dto.setView_count(rs.getInt("i.view_count"));
					dto.setLike_count(rs.getInt("i.like_count"));
					dto.setStatus(rs.getString("i.status"));
					dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
					dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
					dto.setSeller_id(rs.getInt("i.seller_id"));

					dto.setFile_id(rs.getInt("f.id"));
					dto.setSavedname(rs.getString("f.savedname"));

					witemlist.add(dto);
				}while(rs.next());
			}//if-end

		} catch (Exception ex) {
			System.out.println("getWishList()예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return witemlist;
	}
	
	
	//==============
	// getBuyerlist
	//==============
		public List<MainItemListDTO> getBuyerList(Integer buyer_id, int start, int cnt){
			List<MainItemListDTO> bitemlist=null;
			try {
				con=getCon();
				sql="select * from (select * from item where buyer_id = ?) i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id join member m on buyer_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
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
					bitemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
						dto.setAddress(rs.getString("m.address"));
							
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						bitemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("getBuyerItemList()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return bitemlist;
		}//getBuyerItemList-end
		
		//-------------------------------------------------------------------
		//getItemDetail
		//-------------------------------------------------------------------
		public ItemDetailDTO getItemDetail(Integer id){
			ItemDetailDTO dto=null;
			
			try{
				con=getCon();
				
				pstmt=con.prepareStatement("select * from (select * from item where id = ?) i join member m on i.seller_id = m.id join category c on i.category_id = c.id");
				
				pstmt.setInt(1, id);
				
				rs=pstmt.executeQuery();
				
				//rs 내용을 dto에 넣고
				//dto를 리턴
				if(rs.next()){
					dto=new ItemDetailDTO(); //객체생성
					
					dto.setId(rs.getInt("i.id"));
					dto.setTitle(rs.getString("i.title"));
					dto.setContent(rs.getString("i.content"));
					
					dto.setPrice(rs.getBigDecimal("i.price"));
					dto.setStatus(rs.getString("i.status"));
					dto.setView_count((rs.getInt("i.view_count")));
					dto.setLike_count(rs.getInt("i.like_count"));
					dto.setAddress(rs.getString("m.address"));

					dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
					dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
					
					dto.setCategory_id(rs.getInt("c.id"));
					dto.setCategory(rs.getString("c.type"));
					dto.setSeller_id(rs.getInt("i.seller_id"));
					dto.setSeller_nickname(rs.getString("m.nickname"));
				}//if-end
				
			}catch(Exception ex){
				System.out.println("getItemDetail 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			return dto;
		}//getItemDetail-end
		
		//-------------------------------------------------------------------
		//plusViewCount
		//-------------------------------------------------------------------
		public void plusViewCount(Integer item_id) {
			try{
				con=getCon();
				
				pstmt=con.prepareStatement("update item set view_count = view_count + 1 where id = ?");
				
				pstmt.setInt(1, item_id);
				
				pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("plusViewCount 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		}
		
		public void plusLikeCount(Integer item_id) {
			try{
				con=getCon();
				
				pstmt=con.prepareStatement("update item set like_count = like_count + 1 where id = ?");
				
				pstmt.setInt(1, item_id);
				
				pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("plusLikeCount 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		}
		
		public void MinusLikeCount(Integer item_id) {
			try{
				con=getCon();
				
				pstmt=con.prepareStatement("update item set like_count = like_count - 1 where id = ?");
				
				pstmt.setInt(1, item_id);
				
				pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("MinusLikeCount 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		}
		
		
		public int insertItem(ItemDTO dto){
			int x = 0;
			try{
				con=getCon();
				pstmt=con.prepareStatement(
						"INSERT INTO item "
						+ "(title, content, price, status, view_count, like_count, "
						+ "created_at, updated_at, category_id, seller_id) "
						+ "VALUES (?, ?, ?, ?, ?, ?, now(), now(), ?, ?)");
				
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setBigDecimal(3, dto.getPrice());
				pstmt.setString(4, dto.getStatus());
				pstmt.setInt(5, dto.getView_count());
				pstmt.setInt(6, dto.getLike_count());
				pstmt.setInt(7, dto.getCategory_id());
				pstmt.setInt(8, dto.getSeller_id());
				
				pstmt.executeUpdate();
				
				pstmt=con.prepareStatement("SELECT last_insert_id()");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception ex){
				System.out.println("insertItem 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			
			return x;
		}//getMember-end
		
		public List<MainItemListDTO> getMainItemList(int start, int cnt){
			List<MainItemListDTO> mitemlist=null;
			try {
				con=getCon();
				sql="select * from item i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				pstmt.setInt(1, start-1); //0부터 시작하려고 -1
				pstmt.setInt(2, cnt);

				rs=pstmt.executeQuery();
				if(rs.next()){
					mitemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
							
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setAddress(rs.getString("m.address"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));
						mitemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("getMainItemList()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return mitemlist;
		}//getMainItemList-end
		
		public List<MainItemListDTO> search(String keyword, int start, int cnt){
			List<MainItemListDTO> mitemlist=null;
			try {
				con=getCon();
				sql="select * from (select * from item where title like '%"+keyword+"%') i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, start-1); //0부터 시작하려고 -1
				pstmt.setInt(2, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					mitemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
						dto.setAddress(rs.getString("m.address"));
						
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						mitemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("search()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return mitemlist;
		}//getMainItemList-end
	
	
		public int getSearchByCateogryCount(String keyword){
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from (item where title like '%"+keyword+"%') i join member m on i.seller_id = m.id");
				
				rs=pstmt.executeQuery();
				if(rs.next()){
					cnt=rs.getInt(1); //1:필드번호
				}
			} catch (Exception ex) {
				System.out.println("getSearchCount()예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return cnt;
		}//getSearchCount()

		public List<MainItemListDTO> searchByCategory(String keyword, int start, int cnt){
			List<MainItemListDTO> mitemlist=null;
			try {
				con=getCon();
				sql="select * from (select * from item where title like '%"+keyword+"%') i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id left join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, start-1); //0부터 시작하려고 -1
				pstmt.setInt(2, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					mitemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
						String[] address = rs.getString("m.address").split(" ");
						dto.setAddress(address[0] + " " + address[1]);
							
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						mitemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("search()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return mitemlist;
		}//getMainItemList-end
	
		public int getSearchCount(String keyword){
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from (select * from item where title like '%"+keyword+"%') i join member m on i.seller_id = m.id");
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					cnt=rs.getInt(1); //1:필드번호
				}
			} catch (Exception ex) {
				System.out.println("getSearchCount()예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return cnt;
		}//getSearchCount()
	
		//============================================
		// category
		//============================================
		
		//CategoryListCount
		public int getCategoryListCount(Integer category_id) {
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from (select * from item where category_id = ?) i join member m on i.seller_id = m.id");
				
				pstmt.setInt(1, category_id);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					cnt=rs.getInt(1); //1:필드번호
				}
			}catch (Exception ex) {
				System.out.println("getCategoryListCount()예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return cnt; //판매내역 글 갯수
		}//getCategoryListCount()-end
		
		public int getCategoryListByAddressCount(Integer category_id, String address) {
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from (select seller_id from item where category_id = ?) i join (select * from member where instr(address, ?) = 1) m on i.seller_id = m.id");
				
				pstmt.setInt(1, category_id);
				pstmt.setString(2, address);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					cnt=rs.getInt(1); //1:필드번호
				}
			}catch (Exception ex) {
				System.out.println("getCategoryListByCityCount()예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return cnt; //판매내역 글 갯수
		}//getCategoryListCount()-end
		
		
		public List<MainItemListDTO> getCategoryListByAddress(Integer category_id, String address, int start, int cnt){
			List<MainItemListDTO> citemlist=null;
			try {
				con=getCon();
				sql="select * from (select * from item where category_id =?) i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id join (select * from member where instr(address, ?) = 1) m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, category_id);
				pstmt.setString(2, address);
				pstmt.setInt(3, start-1); //0부터 시작하려고 -1
				pstmt.setInt(4, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					citemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
						dto.setAddress(rs.getString("m.address"));
						
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						citemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("getCategoryListByAddress()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return citemlist;
		}
		
		
		
		//CategoryList
		public List<MainItemListDTO> getCategoryList(Integer category_id, int start, int cnt){
			List<MainItemListDTO> citemlist=null;
			try {
				con=getCon();
				sql="select * from (select * from item where category_id =?) i left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id join member m on i.seller_id = m.id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, category_id);
				pstmt.setInt(2, start-1); //0부터 시작하려고 -1
				pstmt.setInt(3, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					citemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
						dto.setAddress(rs.getString("m.address"));
						
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						citemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("getCategoryList()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return citemlist;
		}//getCategoryList()
		
		
	
		
		public void delete(Integer item_id) {
			try {
				con=getCon();
				pstmt=con.prepareStatement("delete from item where id = ?");
				
				pstmt.setInt(1, item_id);
				
				pstmt.executeUpdate();
				
				pstmt=con.prepareStatement("delete from file where item_id = ?");
				
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
		
		public void updateItem(Integer item_id, ItemDTO dto) {
			try {
				con=getCon();
				pstmt=con.prepareStatement("update item set title=?,content=?,price=?,category_id=?,updated_at=now() where id=?");
				
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setBigDecimal(3, dto.getPrice());
				pstmt.setInt(4, dto.getCategory_id());
				
				pstmt.setInt(5, item_id);
				
				pstmt.executeUpdate();
				
			}catch (Exception ex) {
				System.out.println("update() 예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
		}
			
		public List<MainItemListDTO> getMainByAddress(String address, int start, int cnt){
			List<MainItemListDTO> addrmitemlist=null;
			try {
				con=getCon();
				sql="select * from item i join (select * from member where instr(address, ?) = 1) m on i.seller_id = m.id left join (select * from file where id in (select min(id) from file group by item_id)) f on i.id = f.item_id order by i.id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setString(1, address);
				pstmt.setInt(2, start-1); 
				pstmt.setInt(3, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					addrmitemlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						MainItemListDTO dto=new MainItemListDTO();

						dto.setMember_id(rs.getInt("m.id"));
						dto.setNickname(rs.getString("m.nickname"));
							
						dto.setItem_id(rs.getInt("i.id"));
						dto.setTitle(rs.getString("i.title"));
						dto.setPrice(rs.getBigDecimal("i.price"));
						dto.setView_count(rs.getInt("i.view_count"));
						dto.setLike_count(rs.getInt("i.like_count"));
						dto.setStatus(rs.getString("i.status"));
						dto.setAddress(rs.getString("m.address"));
						dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
						dto.setCreated_at(rs.getTimestamp("i.created_at").toLocalDateTime());
						dto.setSeller_id(rs.getInt("i.seller_id"));

						dto.setFile_id(rs.getInt("f.id"));
						dto.setSavedname(rs.getString("f.savedname"));

						addrmitemlist.add(dto);
					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("getAddressMainItemList()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return addrmitemlist;
		}//getMainByCity-end

		public int getMainByAddressCount(String address) {
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from item i join (select * from member where instr(address, ?) = 1) m on i.seller_id = m.id");
				
				pstmt.setString(1, address);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					cnt=rs.getInt(1); //1:필드번호
				}
			} catch (Exception ex) {
				System.out.println("getMainByCityCount()예외:"+ex);
			}finally{
				try {
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally
			return cnt; //
		}
		
		public void updateBuyerItem(Integer buyer_id, Integer item_id) {
	         try {
	            con=getCon();
	            pstmt=con.prepareStatement("update item set updated_at = NOW(), buyer_id=?, status='거래완료' where id=?");
	            
	            pstmt.setInt(1, buyer_id);
	            pstmt.setInt(2, item_id);
	            
	            pstmt.executeUpdate();
	            
	         }catch (Exception ex) {
	            System.out.println("updateBuyerItem() 예외:"+ex);
	         }finally{
	            try {
	               if(rs!=null){rs.close();}
	               if(pstmt!=null){pstmt.close();}
	               if(con!=null){con.close();}
	            } catch (Exception exx) {}
	         }//finally
	      }
}//class-end
