<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>"/>    
    
<html>
	<script type="text/javascript">
	function loginCheck(){
		if($('#email').val()==''){
			alert("Input email");
			$('#email').focus();
			return false;
		}
		if($('#pw').val()==''){
			alert("Input pw");
			$('#pw').focus();
			return false;
		}
		return true;
	}//loginCheck-end
	</script>
 <h2>로그인</h2>
 <form name="loginForm" method="post" action="${ctxpath }/member/loginPro.do" onsubmit="return loginCheck()">
	<table  align="center">
	 <tr>
	  <td>
	   <input type="text" name="email" id="email" size="20" placeholder="email 입력">
	   </td>
	  
	  <td rowspan="2" align="center">
	  <input type="submit" value="login">
	  </td>
	 </tr>
	 <tr>
	  
	  <td>
	  <input type="password" name="pw" id="pw" size="20" placeholder="pw 입력">
	  <td>
	 </tr>
	 
	 <tr>
	  <td colspan="2" align="center">
	   <input type="button" value="join" onClick="location='${ctxpath }/member/inputForm.do'">
	   <input type="button" value="PW 찾기" onClick="location='${ctxpath }/member/findPwForm.do'">
	  </td>
	 </tr>
	</table>
 </form>
</body>
</html>