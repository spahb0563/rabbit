<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />   
    
<%--deleteForm.jsp--%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="aa.css">
	
	<script type="text/javascript">
	  function pwcheck(){
		  if(document.delForm.pw.value==''){
			  alert("암호를 입력하세요");
			  document.delForm.pw.focus();
			  return false;
		  }
		  return true;
	  }
	</script>
	
</head>
<body>
  <h2>회원탈퇴</h2>
  <form name="delForm" method="post" 
     action="${ctxpath}/member/deletePro.do" onsubmit="return pwcheck()">
     
     <table border="1" align="center">
       <tr>
         <td>암호</td>
         <td>
           <input type="password" name="pw" id="pw" size="20">
           <input type="hidden" name="id" value="${sessionScope.id}">
         </td>
       </tr>
       
       <tr>
         <td colspan="2" align="center">
           <input type="submit" value="회원탈퇴">
           <input type="button" value="취소" onclick="location='${ctxpath}/template/template.jsp'">         
         </td>
       </tr>
     
     </table>
  </form>

</body>
</html>