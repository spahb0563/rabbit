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


function infoFormCheck(){

		  if($('#email').val()==''){
				alert("email 입력하세요");
				$('#email').focus();
				return false;
			}
		  
		  if($('#emailck').val()=='false'){
				alert("이메일 중복체크");
				return false;
			}
		  
		  if($('#password').val()==''){
				alert("pw를 입력하세요");
				$('#password').focus();
				return false;
			}
			if($('#pw2').val()==''){
				alert("pw check 입력하세요");
				$('#pw2').focus();
				return false;
			}
			
			if($('#password').val() != $('#pw2').val()){
				alert("password:"+$('#password').val(''));
				alert("암호와 암호 확인이 다릅니다");
				$('#password').val('').focus();//val('')
				$('#pw2').val('');
				return false;
			}
			
			if($('#nickname').val()==''){
				alert("nickname 입력하세요");
				$('#nickname').focus();
				return false;
			}
			
			  if($('#nickck').val()=='false'){
					alert("닉네임 중복체크");
					return false;
				}
			if($('#name').val()==''){
				alert("name 입력하세요");
				$('#name').focus();
				return false;
			}


			return true;
	  }

//회원가입
function check2(){
     //이메일 정규식 체크
	function verifyEmail() {
		// 이메일 검증 스크립트 작성
		var emailVal = $("#email").val();	  
		// 검증에 사용할 정규식 변수 regExp에 저장
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (emailVal.match(regExp) == null) {
			//정규식에 맞지않으면 return null
			alert('이메일 형식이 맞지않습니다.');
			$('#email').val('').focus(); 
			$('#email').focus();
			return false;
		}
		return true;
	}  
	
  	 function chkPW(){//비밀번호 유효성검사
   		 var reg = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
   		 var pw = $("#password").val();
   		 if(false === reg.test(pw)) {
   		 alert('비밀번호는 8자 이상이어야 하며, 숫자/소문자/특수문자를 모두 포함해야 합니다.');
   		 return false;
   		 }else {
   		  return true;
   		 }   
   	 }
   	 function chkPW2(){//비밀번호 유효성검사
   		 var reg = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
   		 var pw2 = $("#password2").val();
   		 if(false === reg.test(pw2)) {
   		 alert('비밀번호확인는 상단 비밀번호와 같아야합니다.');
   		 return false;
   		 }else {
   			return true;
   		 } 
   	 }
   	 function chkName(){//이름 유효성검사
   		 var reg = /^[가-힣]{2,6}$/;
   		 var name = $("#name").val();
   		 if(false === reg.test(name)) {
   		    alert('이름은 한글만 입력가능합니다.');
            $('#name').val('').focus();  		    
   		    $('#name').focus(); 
   			return false;
   		 }else {
   			return true;
   			}
   		 } 	
   	 
 	if(!verifyEmail()) {
		return false;		
	}
	if(!chkPW()){
		return false;		
	}
	
	if(!chkPW2()){
		return false;		
	}
	
	if(!chkName()){
		return false;		
	}

	if($('#id').val()==''){
		alert('ID를 입력 하세요');
		$('#id').focus();
		return false;
	}
	
	if($('#pw').val()==''){
		alert('암호를 입력 하세요');
		$('#pw').focus();
		return false;
	}
	
	if($('#email').val()==''){
		alert('이메일를 입력 하세요');
		$('#email').focus();
		return false;
	}
	if($('#pw').val() !=$('#pw2').val()){
		alert('암호와 암호확인 입력 하세요');
		$('#pw').focus();
		return false;
	}
	
	if($('#pw2').val()==''){
		alert('암호확인를 입력 하세요');
		$('#pw2').focus();
		return false;
	}
	
	if($('#password').val() != $('#password2').val()){
		alert("password:"+$('#password').val(''));
		alert("암호와 암호 확인이 다릅니다");
		$('#password').val('').focus();//val('')
		$('#password2').val('');
		return false;
	}
	
	
	if($('#nickname').val()==''){
		alert('닉이름를 입력 하세요');
		$('#nickname').focus();
		return false;
	}
	
	
	if($('#name').val()==''){
		alert('이름를 입력 하세요');
		$('#name').focus();
		return false;
	}
	
	
	if($('#address').val()==''){
		alert('주소를 입력 하세요');
		$('#address').focus();
		return false;
	}

	return true;

}//check2() -end
  


