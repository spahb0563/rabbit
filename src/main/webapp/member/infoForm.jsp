<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>" />
<%--infoForm.jsp --%>
<html>
<script type="text/javascript" src="aa.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function findAddr() {

		new daum.Postcode({
			oncomplete : function(data) {
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById('address').value = data.address;
			}
		}).open();
	}//openDaumPostcode()---
</script>
<script>
	function nickCheck() {//ajax로 nick중복체크
		if ($('#nickname').val() == '') {
			alert("nickname를 입력 하세요");
			$('#nickname').focus();
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : "confirmNickname.jsp",
				data : "nickname=" + $('#nickname').val(),/*서버로 보낼 데이터*/
				dataType : "JSON",/*서버로부터 받는 자료형*/
				success : function(data) {
					if (data.x == 1) {
						alert("사용중인 nickname 입니다")
						$('#nickname').val('').focus();
					} else {
						alert("사용 가능한 nickname");

						$('#nicknameck').val('true');///******
						//alert("nickname중복 체크 했다는 증거 :"+$('#nicknameck').val());
						$('#name').focus();
					}
				}//success-end
			});//$.ajax()
		}//else-end
		return true;
	}//nickCheck)-end

	function nick_Check() {
		if ($('#nicknameck').val() == 'false') {
			alert("nickname중복 체크 하세요");
			$('#nickname').focus();
			return false;
		}
	}
</script>
<div class="row">
	<jsp:include page="/module/left.jsp" flush="false" />
	<div class="col-md-9">
		<h2>내 정보 수정</h2>

		<form method="post" action="${ctxpath }/member/infoPro.do"
			onSubmit="return infoFormCheck()">
			<table border="1" align="center">
				<tr>
					<td>EMAIL</td>
					<td>
						<input type="text" name="email" id="email" size="20" value="${dto.email}"> 
						<input type="hidden" name="id" id="id" value="${dto.id}">
					</td>
				</tr>
				<tr>
					<td>암호</td>
					<td>
						<input type="password" name="password" id="password" size="20">
					</td>
				</tr>
				<tr>
					<td>암호확인</td>
					<td><input type="password" name="pw2" id="pw2" size="20">
					</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>
						<input type="text" name="nickname" id="nickname" size="40" value="${dto.nickname}"> <input type="hidden" name="nicknameck" id="nicknameck" value="false"> 
						<input type="button" value="닉네임 중복체크" onClick="nickCheck()">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" id="name" size="40" value="${dto.name}" onfocus="nick_Check()">
					</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" name="zipcode" id="zipcode" size="7"
						value="${dto.zipcode}" readonly="readonly"> <input
						type="button" value="주소찾기" onclick="findAddr()"></td>
				</tr>
				<tr>
					<td>address</td>
					<td><input type="text" name="address" id="address" size="40"
						value="${dto.address}" readonly="readonly"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="내정보 수정"> <input type="button" value="취소"
						onclick="location='${ctxpath}/template/template.jsp'"></td>
				</tr>

			</table>
		</form>
	</div>
</div>
</html>