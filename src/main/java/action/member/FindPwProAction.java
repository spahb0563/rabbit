package action.member;

import java.util.HashSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class FindPwProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
				
		request.setCharacterEncoding("UTF-8");
		
		String impw=tempPassword();
		String to=request.getParameter("email");
		System.out.println("to: "+to);
		
		String reply=sendMail(impw,to);
		System.out.println("reply:"+reply);
		
		request.setAttribute("reply", reply);

		return "/member/sendPw.jsp";
	}//requestPro()-end
	
    //----------------------------------------------------------
	///public void sendMail(String to,String from, String subject,String impw) throws Exception{
	public String sendMail(String impw,String to){
		String reply="";//변수
		String host = "smtp.naver.com"; // 호스트 정하기 네이버 쓸거임
		String user = ""; //자기 네이버 주소
		String password = "";//비밀번호
		
		// SMTP 서버 정보를 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// 메일 제목
			message.setSubject("[당근마켓]임시 비밀번호 발급");
			
			// 메일 내용
			message.setText("임시 비밀번호가 발급되었습니다.\n"+impw);
			
			// send the message
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return reply;
 	}//sendMail()-end
    //---------------------------------------------------------

	//새로운 비밀번호
	 
	public  String tempPassword(){
		 
		String[] chars = new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
				"L", "M","N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
		}; 

		HashSet<String> set=new HashSet<String>();
		 
		while(set.size()<6){
			String s=chars[(int)(Math.random()*chars.length)];
			set.add(s);
		}//while-end
		 
		StringBuffer sb=new StringBuffer();
		for(String c:set){
			System.out.print(c);
			sb.append(c);
		}
		
		//return "java!$2233";
		return sb.toString();
		
	}//tempPassword()-end
	 
}//class-end


