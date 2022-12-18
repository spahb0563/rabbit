<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>" />
<%--infoForm.jsp --%>
<html>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="check.js"></script>
<script src="append.js"></script>
<script>
	function findAddr() {

		new daum.Postcode({
			oncomplete: function (data) {
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById('address').value = data.address;
			}
		}).open();
	} //openDaumPostcode()---
</script>
<div class="row mt-3">
	<jsp:include page="/module/left.jsp" flush="false" />
	<div class="col-md-9 pt-3">
		<h2>내 정보 수정</h2>
		<form method="post" action="${ctxpath }/member/infoPro.do" onSubmit="return infoFormCheck()">
			<table border="1" align="center">
				<tr>
					<td>EMAIL</td>
					<td>
						<input type="text" name="email" id="email" size="20" value="${dto.email}" readonly>
						<input type="hidden" name="id" id="id" value="${dto.id}">
					</td>
				</tr>

				<tr>
					<td>pw</td>
					<td>
						<input type="hidden" name="password_change" id="password_change" value="false">
						<input type="hidden" name="password" id="password" value="${dto.password}">
						<p id="passwordField"> <input class="btn btn-outline-warning" type="button" id="pw_btn" name="pw_btn" value="암호변경"
								onClick="return append()"> </p>
					</td>
				</tr>

				<tr>
					<td>닉네임</td>
					<td>
						<input type="text" name="nickname" id="nickname" size="40" value="${dto.nickname}">
						<input type="hidden" name="current_nick" id="current_nick" value="${dto.nickname}">
						<input type="hidden" name="nickck" id="nickck" value="false">
						<input class="btn btn-outline-warning" type="button" value="닉네임 중복체크" onClick="nickCheck()">
					</td>
				</tr>


				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" id="name" size="40" value="${dto.name}">
					</td>
				</tr>

				<tr>
					<td>우편번호</td>
					<td>
						<input type="text" name="zipcode" id="zipcode" size="7" value="${dto.zipcode}"
							readonly="readonly">
						<input class="btn btn-outline-warning" type="button" value="주소찾기" onclick="findAddr()">
					</td>
				</tr>

				<tr>
					<td>address</td>
					<td>
						<input type="text" name="address" id="address" size="40" value="${dto.address}"
							readonly="readonly">
					</td>
				</tr>


				<tr>
					<td colspan="2" align="center">
						<input class="btn btn-outline-warning" type="submit" value="내정보 수정">
						<input class="btn btn-outline-warning" type="reset" value="취소">
						<input class="btn btn-outline-warning" type="button" value="회원탈퇴" onclick="location='${ctxpath}/member/deleteForm.do'">
					</td>
				</tr>

			</table>
		</form>
	</div>
</div>

</html>