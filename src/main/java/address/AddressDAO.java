package address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import category.CategoryDTO;

import java.util.*;


public class AddressDAO {
	private static AddressDAO instance=new AddressDAO();//객체생성

	//생성자
	private AddressDAO(){} //private 이라 외부에서 객체생성 못함 자신의 클래스에서만 가능

	public static AddressDAO getDao(){ //jsp 사용할 메서드
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
	//---------------------------------------------------
	public List getcityList(){
		List<AddressDTO> addresslist = null;
		try{
			con=getCon();
			pstmt=con.prepareStatement("select distinct city from address");

			rs = pstmt.executeQuery();

			if(rs.next()){
				addresslist=new ArrayList();
				do{	
					AddressDTO dto=new AddressDTO();

					dto.setCity(rs.getString("city"));

					addresslist.add(dto); //***

				}while(rs.next());
			}//if-end

		}catch(Exception ex){
			System.out.println("getcityList()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		return addresslist;
	}//getcityList()-end


	public AddressDTO getDistrictList(String city){
		AddressDTO dto=new AddressDTO();
		dto.setCity(city);
		List<String> districtList = new ArrayList<>();
		try{
			con=getCon();
			pstmt=con.prepareStatement("select district from address where city like ? order by district");
			pstmt.setString(1, city);
			rs = pstmt.executeQuery();

			if(rs.next()){
				do{	
					districtList.add(rs.getString("district"));
				}while(rs.next());
			}//if-end

		}catch(Exception ex){
			System.out.println("districtList()얘외:"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception exx){}
		}//finally-end
		dto.setDistrictList(districtList);
		return dto;
	}//districtList()-end
}

