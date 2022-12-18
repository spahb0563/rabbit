package member;
import java.sql.*; //Connection, Statement, PreparedStatement, ResultSet

import javax.sql.*; //DataSource
import javax.naming.*; //lookup

import java.util.*;

//DAO : 비지니스 로직, CRUD처리
public class MemberDAO {
	//싱글톤 객체사용(객채를 하나만 사용한다), 메모리 절약효과
	private static MemberDAO instance=new MemberDAO();//객체생성
		
	//생성자
	private MemberDAO(){} //private 이라 외부에서 객체생성 못함 자신의 클래스에서만 가능
	
	
	public static MemberDAO getDao(){ //jsp 사용할 메서드
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
		//id 중복체크
		//===============
		public int confirmEmail(String email){
			int x=-100; //변수
			
			try{
				con=getCon();
				pstmt=con.prepareStatement("select email from member where email=?");
				
				pstmt.setString(1, email);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					x=1; //사용중인 id
				}else{
					x=-1; //사용가능 id
				}//else-end
			}catch(Exception ex){
				System.out.println("confirmEmail 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			return x;
		}//confirmID-end
		//===============
		//nickname 중복체크
		//===============
		public int confirmNickname(String nickname){
			int x=-100; //변수
			
			try{
				con=getCon();
				pstmt=con.prepareStatement("select nickname from member where nickname=?");
				
				pstmt.setString(1, nickname);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					x=1; //사용중인 id
				}else{
					x=-1; //사용가능 id
				}//else-end
			}catch(Exception ex){
				System.out.println("confirmNickname 예외 : "+ex);
			}finally{
				try{
					if(rs!=null){rs.close();}
					if(pstmt!=null){pstmt.close();}
					if(con!=null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
			return x;
		}//confirmNickname-end

	//===============
	//로그인, 인증
	//===============
	public int userCheck(String email, String pw){
		int x=-100;
		String dbpw="";
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where email=?");
			
			pstmt.setString(1, email);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbpw=rs.getString("password");
				
				if(pw.equals(dbpw)){//암호가 일치하면
					x=1; //인증성공
				}else{//암호가 일치하지 않으면
					x=0;
				}//else-end
			}else{
				x=-1; //없는 email
			}//else-end
		}catch(Exception ex){
			System.out.println("userCheck 예외 : "+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//userCheck-end
	
	
	//===============
	//로그인 멤버 정보
	//===============
		public LoginMemberDTO getLoginMember(String email){
			LoginMemberDTO dto=null;
			
			try{
				con=getCon();
				pstmt=con.prepareStatement("select * from member where email=?");
				
				pstmt.setString(1, email);
				
				rs=pstmt.executeQuery();
				
				//rs 내용을 dto에 넣고
				//dto를 리턴
				if(rs.next()){
					dto=new LoginMemberDTO(); //객체생성
					
					dto.setId(rs.getInt("id"));
					dto.setNickname(rs.getString("nickname"));
					dto.setAddress(rs.getString("address"));
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
		}//getLoginMember-end
	
	
	//===============
	//회원 정보 가져오기
	//===============
	public MemberDTO getMember(Integer id){
		MemberDTO dto=null;
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			
			pstmt.setInt(1, id);
			
			rs=pstmt.executeQuery();
			
			//rs 내용을 dto에 넣고
			//dto를 리턴
			if(rs.next()){
				dto=new MemberDTO(); //객체생성
				
				dto.setId(rs.getInt("id"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setNickname(rs.getString("nickname"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setZipcode(rs.getString("zipcode"));
			
				dto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
				dto.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
				
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
	
	//====================
	   //DB내용 수정(내정보 수정)
	   //====================
	   public void updateMember(MemberDTO dto){
	      try{
	         con=getCon();
	         String sql="update member set password=?, name=?, nickname=?,  zipcode=?, address=?, updated_at=NOW() where id=?";
	         pstmt=con.prepareStatement(sql);
	         
	         pstmt.setString(1, dto.getPassword());
	         pstmt.setString(2, dto.getName());
	         pstmt.setString(3, dto.getNickname());
	         pstmt.setString(4, dto.getZipcode());
	         pstmt.setString(5, dto.getAddress());
	         pstmt.setInt(6, dto.getId());
	         
	         pstmt.executeUpdate();
	      }catch(Exception ex){
	         System.out.println("updateMember 예외 : "+ex);
	      }finally{
	         try{
	            if(pstmt!=null){pstmt.close();}
	            if(con!=null){con.close();}
	         }catch(Exception ex2){}
	      }//finally-end
	   }//updateMember-end

	
	//====================
	//회원탈퇴
	//====================
	public int deleteMember(int id,String pw){
		PreparedStatement pstmt2=null;
		int x=-100;
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from member where id=?");
			
			pstmt.setInt(1,id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				String dbpw=rs.getString("pw");
				
				if(dbpw.equals(pw)){
					//암호가 일치하면 회원탈퇴
					pstmt2=con.prepareStatement("delete from member where id=?");
					
					pstmt2.setInt(1,id);
					
					pstmt2.executeUpdate();
					
					x=1; //삭제성공
				}else{
					x=-1; //암호틀림
				}//else-end
			}//if-end
		}catch(Exception ex){
			System.out.println("deleteMember 예외 : "+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//deleteMember-end
	
	public void insertMember(MemberDTO dto){
		try{
			con=getCon();
			pstmt=con.prepareStatement("insert into member (email, password, nickname, name, address, zipcode, created_at, updated_at)"+
			" values(?,?,?,?,?,?, now(), now())");

			//?값 채우기
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getZipcode());

			pstmt.executeUpdate();// 쿼리 수행

		}catch(Exception ex){
			System.out.println("insertMember()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
	}//메서드-end
	
	public List<MemberDTO> getBusinessList(Integer item_id, Integer member_id){
		List<MemberDTO> relist=null;

		try {
			con=getCon();
			String sql="select m.nickname,m.id from (select distinct sender_id from message where item_id = ?) me join (select * from member where id != ?) m on me.sender_id = m.id;";
			pstmt=con.prepareStatement(sql); //생성시 인자들어간다

			//?값 채우기
			pstmt.setInt(1, item_id);
			pstmt.setInt(2, member_id);

			rs=pstmt.executeQuery();

			//rs내용을 dto에 담고
			//dto를 list에 넣는다
			//list를 리턴한다

			if(rs.next()){
				relist=new ArrayList();
				do{	//rs.next로 하나를 받았으므로 do-while 사용
					MemberDTO dto=new MemberDTO();

					dto.setNickname(rs.getString("m.nickname"));
					dto.setId(rs.getInt("m.id"));

					relist.add(dto); //***

				}while(rs.next());
			}//if-end

		} catch (Exception ex) {
			System.out.println("getBusinessList()예외:"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return relist;
	}//getSaleList()
	
	//===============
	//로그인, 인증
	//===============
	public int userCheck(String email, String pw, int a){
		int x=-100;
		String dbpw="";
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where email=?");
			
			pstmt.setString(1, email);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbpw=rs.getString("password");
				
				if(pw.equals(dbpw)){//암호가 일치하면
					x=1; //인증성공
				}else{//암호가 일치하지 않으면
					x=0;
				}//else-end
			}else{
				x=-1; //없는 email
			}//else-end
		}catch(Exception ex){
			System.out.println("userCheck 예외 : "+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//userCheck-end
	
	public int userCheck(Integer id, String pw){
		int x=-100;
		String dbpw="";
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			
			pstmt.setInt(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbpw=rs.getString("password");
				
				if(pw.equals(dbpw)){//암호가 일치하면
					x=1; //인증성공
				}else{//암호가 일치하지 않으면
					x=0;
				}//else-end
			}else{
				x=-1; //없는 email
			}//else-end
		}catch(Exception ex){
			System.out.println("userCheck 예외 : "+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//userCheck-end
	
	//====================
			//회원탈퇴
			//====================
			public int deleteMember(Integer id){
				PreparedStatement pstmt2=null;
				int x=-100;
				try{
					con=getCon();
					pstmt=con.prepareStatement("delete from member where id=?");
					pstmt.setInt(1, id);
					
					x = pstmt.executeUpdate();
					
				}catch(Exception ex){
					System.out.println("deleteMember() 예외"+ex);
				}finally{
					try{
						if(rs!=null){rs.close();}
						if(pstmt!=null){pstmt.close();}
						if(con!=null){con.close();}
					}catch(Exception exx){}
				}//finally end
				return x;
			}//deleteMember-end
	
}//class-end
