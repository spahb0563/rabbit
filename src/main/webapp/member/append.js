
 function append(){   
	 
	 const pw_btn = document.getElementById('pw_btn');

 	  
 	  // btn 숨기기 (display: none)

 	  if(pw_btn.style.display !== 'none') {
 		 pw_btn.style.display = 'none';
 		 
 		 $("#passwordField").append("<div> <input type='password' name='password1' id='password1' size='20' placeholder='기존 암호 입력'/></div>");
 		   $("#passwordField").append("<div> <input type='password' name='password2' id='password2' size='20' placeholder='새 암호 입력'/></div>");
 		   $("#passwordField").append("<div> <input type='password' name='password2_ck' id='password2_ck' size='20' placeholder='새 암호 확인'/></div>");
 	  }
 	  
 	 $('#password_change').val('true')
  
 }
 
 
