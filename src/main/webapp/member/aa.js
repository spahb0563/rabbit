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
  


