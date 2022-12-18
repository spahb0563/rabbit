package msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import category.CategoryDTO;

public class MsgDAO {
	//싱글톤 객체사용(객채를 하나만 사용한다), 메모리 절약효과
	private static MsgDAO instance=new MsgDAO();//객체생성
		
	//생성자
	private MsgDAO(){} //private 이라 외부에서 객체생성 못함 자신의 클래스에서만 가능
	
	public static MsgDAO getDao(){ //jsp 사용할 메서드
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
	//메세지 보내기
	//===============
	public void writeMsg(MsgDTO dto) {
		try{
			con=getCon();
			pstmt=con.prepareStatement("insert into message (content, status, sender_id, receiver_id, created_at, item_id) values(?, ?, ?, ?, now(), ?)");

			//?값 채우기
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getStatus());
			pstmt.setInt(3, dto.getSender_id());
			pstmt.setInt(4, dto.getReceiver_id());
			pstmt.setInt(5, dto.getItem_id());

			pstmt.executeUpdate();// 쿼리 수행

		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("writeMsg()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
	}
	
	public List<MsgListDTO> getReceivedMsgList(Integer receiver_id, int start, int cnt) {
		List<MsgListDTO> list = null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from (select * from message where receiver_id = ?) m1 join member m2 on m1.sender_id = m2.id order by m1.id desc limit ?,?");
			
			pstmt.setInt(1, receiver_id);
			pstmt.setInt(2, start-1); //0부터 시작하려고 -1
			pstmt.setInt(3, cnt);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList();
				do{	
					MsgListDTO dto=new MsgListDTO();
					
					dto.setId(rs.getInt("m1.id"));
					dto.setContent(rs.getString("m1.content"));
					dto.setSender_id(rs.getInt("m1.sender_id"));
					dto.setStatus(rs.getInt("m1.status"));
					dto.setSender_nickname(rs.getString("m2.nickname"));
					dto.setCreated_at(rs.getTimestamp("m1.created_at").toLocalDateTime());
					list.add(dto); //***

				}while(rs.next());
			}//if-end
		}catch(Exception ex){
			System.out.println("getReceivedMsgList()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		return list;
	}
	
	public int getSentMsgCount(Integer sender_id) {
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from message where sender_id = ?");
			
			pstmt.setInt(1, sender_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getSentMsgCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt;
	}
	
	public List<MsgListDTO> getSentMsgList(Integer sender_id, int start, int cnt) {
		List<MsgListDTO> list = null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from (select * from message where sender_id = ?) m1 join member m2 on m1.receiver_id = m2.id order by m1.id desc limit ?,?");
			
			pstmt.setInt(1, sender_id);
			pstmt.setInt(2, start-1); //0부터 시작하려고 -1
			pstmt.setInt(3, cnt);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				list=new ArrayList();
				do{	
					MsgListDTO dto=new MsgListDTO();
					
					dto.setId(rs.getInt("m1.id"));
					dto.setContent(rs.getString("m1.content"));
					dto.setSender_id(rs.getInt("m1.sender_id"));
					dto.setStatus(rs.getInt("m1.status"));
					dto.setReceiver_nickname(rs.getString("m2.nickname"));
					dto.setCreated_at(rs.getTimestamp("m1.created_at").toLocalDateTime());
					list.add(dto); //***

				}while(rs.next());
			}//if-end
		}catch(Exception ex){
			System.out.println("getSenderMsgList()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		return list;
	}
	
	public int getReceivedMsgCount(Integer reciver_id) {
		int cnt=0;
		try {
			con=getCon();
			pstmt=con.prepareStatement("select count(*) from message where receiver_id = ?");
			
			pstmt.setInt(1, reciver_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1); //1:필드번호
			}
		} catch (Exception ex) {
			System.out.println("getReceivedMsgCount()예외:"+ex);
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception exx) {}
		}//finally
		return cnt;
	}
	
	public MsgListDTO getMsg(Integer id) {
		MsgListDTO dto = null;
		try{
			con=getCon();
			
			pstmt=con.prepareStatement("update message set status = 1 where id=?");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt=con.prepareStatement("select * from (select * from message where id = ?) m1 join member m2 on m1.sender_id = m2.id join item i on m1.item_id = i.id");
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new MsgListDTO();
				dto.setId(rs.getInt("m1.id"));
				dto.setContent(rs.getString("m1.content"));
				dto.setSender_id(rs.getInt("m1.sender_id"));
				dto.setStatus(rs.getInt("m1.status"));
				dto.setSender_nickname(rs.getString("m2.nickname"));
				dto.setItem_id(rs.getInt("m1.item_id"));
				dto.setCreated_at(rs.getTimestamp("m1.created_at").toLocalDateTime());
				dto.setItem_title(rs.getString("i.title"));
			}//if-end
		}catch(Exception ex){
			System.out.println("insertMember()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		return dto;
	}
	
	
}
