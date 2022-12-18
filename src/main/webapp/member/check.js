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


function nickCheck(){//ajax로 nick중복체크
	  
	  
	  if($('#nickname').val()==''){
		  alert("닉네임 값이 비었습니다");
		  $('#nickname').val('').focus();
		  return false;
	  }

if($('#nickname').val()==$('#current_nick').val()){

		  $('#nickck').val('true');///******
		  return true;
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
					  
					  $('#nickck').val('true');///******
					  //alert("nickname중복 체크 했다는 증거 :"+$('#nicknameck').val());
					  $('#name').focus();
				  }
			  }//success-end
		  });//$.ajax()
	  }//else-end
	  return true;
}//nickCheck)-end




function infoFormCheck(){
			nickCheck()
		  if($('#email').val()==''){
				alert("email 입력하세요");
				$('#email').focus();
				return false;
			}
		  
		  if($('#password_change').val()=='true'){
			  if($('#password1').val()==''){
					alert("기존 암호를 입력하세요");
					$('#password1').focus();
					return false;
				}
			  
				if($('#password2').val()==''){
					alert("새 암호 입력하세요");
					$('#password2').focus();
					return false;
				}
				
				if($('#password2_ck').val()==''){
					alert("새 암호 확인 입력하세요");
					$('#password2_ck').focus();
					return false;
				}
				
				if($('#password').val()!=$('#password1').val()){
					alert("기존암호 불일치");
					$('#password1').val('').focus();//val('')
					$('#password2').val('').focus();
					$('#password2_ck').val('').focus();
					return false;
				}
				
				if($('#password2').val()!=$('#password2_ck').val()){
					alert("암호와 암호 확인이 다릅니다");
					$('#password2').val('').focus();//val('')
					$('#password2_ck').val('');
					return false;
				}
				
				$('#password').val($('#password2').val());
		  }//if-end
			if($('#nickname').val()==''){
				alert("nickname 입력하세요");
				$('#nickname').focus();
				return false;
			}
			
			  if($('#nickck').val()=='false'){
					alert("닉네임 중복체크하세요");
					return false;
				}
			  
			if($('#name').val()==''){
				alert("name 입력하세요");
				$('#name').focus();
				return false;
			}
			
			alert('수정완료');
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
		alert("password:"+$('#password').val());
		alert("암호와 암호 확인이 다릅니다");
		$('#password').val().focus();//val('')
		$('#password2').val();
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
  


