package review;

import review.*;
import item.ItemDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
	//전역변수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt=null;
		String sql="";

		private ReviewDAO(){}//생성자, private는 외부에서 생성 못하게 함

		private static ReviewDAO instance=new ReviewDAO();//싱글톤 객체 사용(메모리 절약효과)

		public static ReviewDAO getDao(){//jsp에서 사용할 메서드

			return instance;
		}//getDao()-end

		private Connection getCon() throws Exception{
			Context ct=new InitialContext();
			DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
			return ds.getConnection();
		}//getCon()-end
		
		
		//======================
				//글쓰기
				//======================
				public void insertReview(ReviewDTO dto){
					try{
						con=getCon();
						pstmt=con.prepareStatement("INSERT INTO review (content,created_at,target_id,writer_id) VALUES (?, now(), ?, ?)");
						
						pstmt.setString(1, dto.getContent());
						pstmt.setInt(2,  dto.getTarget_id());
						pstmt.setInt(3, dto.getWriter_id());
						
						pstmt.executeUpdate();
					}catch(Exception ex){
						System.out.println("ReviewDAO/insertReview 예외 : "+ex);
					}finally{
						try{
							if(rs!=null){rs.close();}
							if(pstmt!=null){pstmt.close();}
							if(con!=null){con.close();}
						}catch(Exception ex2){}
					}//finally-end
					
				}//insertReview-end
		
		//내가 쓴 후기글 보기
		public int getSentReview(Integer writer_id) {
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from review where writer_id = ?");
				
				pstmt.setInt(1, writer_id);
				
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
			return cnt; //내가 쓴 후기 글 갯수
		}//getSentReview()-end
		
		//==============
		// getSentlist
		//==============
		public List getSentlist(Integer writer_id, int start, int cnt){

			List<ReviewDTO> slist=null;

			try {
				con=getCon();
				sql="select * from review where writer_id = ? order by id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, writer_id);
				pstmt.setInt(2, start-1); //0부터 시작하려고 -1
				pstmt.setInt(3, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					slist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						ReviewDTO dto=new ReviewDTO();

						dto.setId(rs.getInt("id"));
						dto.setContent(rs.getString("content"));

						dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					
						dto.setTarget_id(rs.getInt("target_id"));
						dto.setWriter_id(rs.getInt("writer_id"));

						slist.add(dto); //***

					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("ReviewDTO/getSentlist()예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally

			return slist;
		}//getSaleList()
		
		
		//====================
		//내가 받은 후기글
		//=======================
		
		public int getRecieveReview(Integer target_id) {
			int cnt=0;
			try {
				con=getCon();
				pstmt=con.prepareStatement("select count(*) from review where target_id = ?");
				
				pstmt.setInt(1, target_id);
				
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
			return cnt; //내가 받은 글 갯수
		}//getRecieveReview()-end
		
		//==============
		// getRecievelist
		//==============
		public List getRecievelist(Integer target_id, int start, int cnt){

			List<ReviewDTO> rlist=null;

			try {
				con=getCon();
				sql="select * from review where target_id = ? order by id desc limit ?,?"; //id 내림차순 정렬
				pstmt=con.prepareStatement(sql); //생성시 인자들어간다

				//?값 채우기
				pstmt.setInt(1, target_id);
				pstmt.setInt(2, start-1); //0부터 시작하려고 -1
				pstmt.setInt(3, cnt);

				rs=pstmt.executeQuery();

				//rs내용을 dto에 담고
				//dto를 list에 넣는다
				//list를 리턴한다

				if(rs.next()){
					rlist=new ArrayList();
					do{	//rs.next로 하나를 받았으므로 do-while 사용
						ReviewDTO dto=new ReviewDTO();

						dto.setId(rs.getInt("id"));
						dto.setContent(rs.getString("content"));

						dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					
						dto.setTarget_id(rs.getInt("target_id"));
						dto.setWriter_id(rs.getInt("writer_id"));

						rlist.add(dto); //***

					}while(rs.next());
				}//if-end

			} catch (Exception ex) {
				System.out.println("ReviewDTO/getRecievelist 예외:"+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				} catch (Exception exx) {}
			}//finally

			return rlist;
		}//getSaleList()
		
		
		
		
		
		
		
		//join하기
		
//		public ReviewDTO getReviewDTO(Integer id){//review 글 id
//			ReviewDTO dto=null;
//			
//			try{
//				con=getCon();
//				
//				pstmt=con.prepareStatement("select * from (select * from review where id = 1) r join item i on i.seller_id = r.target_id and i.buyer_id = r.writer_id");
//				
//				pstmt.setInt(1, id);
//				
//				rs=pstmt.executeQuery();
//				
//				//rs 내용을 dto에 넣고
//				//dto를 리턴
//				if(rs.next()){
//					dto=new ReviewDTO(); //객체생성
//					
//					dto.setId(rs.getInt("i.id"));
//					dto.setTitle(rs.getString("i.title"));
//					dto.setContent(rs.getString("i.content"));
//					
//					dto.setPrice(rs.getBigDecimal("i.price"));
//					dto.setStatus(rs.getString("i.status"));
//					dto.setView_count((rs.getInt("i.view_count")));
//					dto.setLike_count(rs.getInt("i.like_count"));
//
//					dto.setUpdated_at(rs.getTimestamp("i.updated_at").toLocalDateTime());
//
//					dto.setCategory(rs.getString("c.type"));
//					dto.setSeller_id(rs.getInt("i.seller_id"));
//					dto.setSeller_nickname(rs.getString("m.nickname"));
//					String[] address = rs.getString("m.address").split(" ");
//					dto.setAddress(address[0] + " " + address[1]);
//				}//if-end
//				
//			}catch(Exception ex){
//				System.out.println("getItemDetail 예외 : "+ex);
//			}finally{
//				try{
//					if(rs!=null){rs.close();}
//					if(pstmt!=null){pstmt.close();}
//					if(con!=null){con.close();}
//				}catch(Exception ex2){}
//			}//finally-end
//			return dto;
//		}//getItemDetail-end
		
}//class-end
