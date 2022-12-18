<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />   

<html>
     <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
     <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

     <script>
      function findAddr(){
          
         new daum.Postcode({
            oncomplete:function(data){
               document.getElementById('zipcode').value=data.zonecode;
               document.getElementById('address').value=data.address;
             }
         }).open();
      }//findAddr()-end
   </script>
     
   <script>   
        //ajax로 email 중복체크
      function emailCheck(){
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
                    if(data.x==1){
                       alert("사용중인 email 입니다");
                       /* verifyEmail(); */
                       $('#email').val('').focus();
                    }else{
                       alert("사용 가능한 email");
                       /* verifyEmail(); */
                       $('#emailck').val('true');///******
                       //alert("email중복 체크 했다는 증거 :"+$('#emailck').val());
                       //$('#password').focus();
                    }
                 }//success-end
              });//$.ajax()
            }//else-end
           return true;
        }//emailCheck()-end
     function email_Check(){
          if($('#emailck').val()=='false'){
               alert("email중복 체크 하세요");
               $('#email').focus();
               return false;
            }
      }        
 
      function nickCheck(){//ajax로 nick중복체크
         if($('#nickname').val()==''){
            alert("nickname를 입력 하세요");
            $('#nickname').focus();
            return false;
         }else{
            $.ajax({
               type:"POST",
               url:"confirmNickname.jsp",
               data:"nickname="+$('#nickname').val(),/*서버로 보낼 데이터*/
               dataType:"JSON",/*서버로부터 받는 자료형*/
               success:function(data){
                  if(data.x==1){
                     alert("사용중인 nickname 입니다")
                     $('#nickname').val('').focus();
                  }else{
                     alert("사용 가능한 nickname");
                     
                     $('#nicknameck').val('true');///******
                     //alert("nickname중복 체크 했다는 증거 :"+$('#nicknameck').val());
                     $('#name').focus();
                  }
               }//success-end
            });//$.ajax()
         }//else-end
         return true;
      }//nicknameCheck()-end
      
      function nickname_Check(){
         if($('#nicknameck').val()==''){
            alert("nickname중복 체크 하세요");
            $('#nickname').focus();
            return false;
         }
      }
      
   </script>
   <h2>회원가입</h2>
   <form name="inputForm" method="post" action="${ctxpath}/member/inputPro.do" onsubmit="return check2()">
      <table border="1" align="center">
      <%--
         <tr>
            <td>ID</td>
            <td>
               <input type="text" name="id" id="id" size="20" placeholder="ID입력">
            </td>
         </tr>
       --%>
         
         <tr>
            <td>이메일</td>
            <td>
                <input type="text" name="email" id="email" size="30" placeholder="이메일입력">
                <input class="btn btn-outline-warning" type="button" value="email중복체크" onClick="emailCheck()" >                
                <%--
                <input type="button" value="email유효성체크" onClick="verifyEmail()">                      
                 --%>
                 
                <input type="hidden" name="emailck" id="emailck" value="false">                
            </td>
         </tr>
         
         <tr>
            <td>PW</td>
            <td><input type="password" name="password" id="password" size="12">
                               ※소문자/숫자/특수문자포함 8자리이상 
                <%--
                <input type="button" value="비번체크" onClick="chkPW()">                
                 --%>
            </td>
         </tr>
         
         <tr>
            <td>PW확인</td>
            <td>
                <input type="password" name="password2" id="password2" size="12" ">
                <%--
                <input type="button" value="비번확인체크" onClick="chkPW2()">
                 --%>
            </td>
         </tr>
          
         
         <tr>
            <td>이름</td>
            <td><input type="text" name="name" id="name" size="30" placeholder="이름입력" ">
                                ※숫자불가
                <%--
                <input type="button" value="이름체크" onClick="chkName()">
                 --%>
            </td>
         </tr>
         <tr>
            <td>닉네임</td>
            <td>
            <input type="text" name="nickname" id="nickname" size="12">
            <input type="hidden" name="nicknameck" id="nicknameck" value="false" >
            <%--
            <input type="button" value="닉네임체크" onClick="nickCheck()">
             --%>
            </td>
            
         </tr>
         <tr>
            <td>우편번호</td>
            <td>
               <input type="text" name="zipcode" id="zipcode" size="7">
               <input class="btn btn-outline-warning" type="button" value="주소찾기" onClick="findAddr()">
            </td>
         </tr>
         
         <tr>
            <td>주소</td>
            <td><input type="text" name="address" id="address" size="50" ></td>
         </tr>        
       
         <tr>
            <td colspan="2" align="center">
               <input class="btn btn-warning" type="submit" value="회원가입" >
               <input class="btn btn-warning" type="reset" value="다시입력">
               <input class="btn btn-warning" type="button" value="취소" onClick="location='${ctxpath}/main/viewMain.do'">
            </td>
         </tr>
      </table>
   </form>
</body>
</html>
<script type="text/javascript" src="${ctxpath}/static/app/js/aa.js"></script>