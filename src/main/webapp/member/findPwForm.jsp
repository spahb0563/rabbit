<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
<%--
function findPw() {
 		const data = {
    		email : $('#email').val(),
    		name : $('#name').val()
    	}
    	alert(data.email);
}
--%>

function sendTempPw(){//ajax로 email 체크
	  if($('#email').val()==''){
		  alert("email를 입력 하세요");
		  $('#email').focus();
		  return false;
	  }else{
		  $.ajax({
			  type:"POST",
			  url:"confirmEmail.jsp",
			  data:"email="+$('#email').val(),/*서버로 보낼 데이터*/
			  dataType:"JSON",/*서버로부터 받는 자료형*/
			  success:function(data){
				  if(data.x!=1){
					  alert("해당 이메일로 가입된 계정이 없습니다");
					  $('#email').focus().val('');
					  return false;
				  }else{
					  alert("이메일을 발송합니다");
					 // $('#nicknameck').val('true');///******
					 
				  }//else
			  }//success-end
		  });//$.ajax()
	  }//else-end
	  return true;
}//sendTempPw)-end



</script>
<link rel="shortcut icon" href="#">
</head>
<body>
<h2>비밀번호 찾기</h2>
<form method="post" action="${ctxpath}/member/findPwPro.do" onSubmit="return sendTempPw()">
	<table  align="center">
	 <tr>
	  <td>
	   <input type="text" name="email" id="email" size="20" placeholder="email 입력">
	   </td>
	   
	   <td>
	   <input type="submit"  value="확인">
	   </td>
	 </tr>
	
	 
	 
	</table>
 </form>
</body>
</html>