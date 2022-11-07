<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="item.*"
    %>
<%@ include file="/header/header.jsp" %>
<%-- detail.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check(){
	alert("삭제하시겠습니까?")
}
</script>
</head>
<body>
	<table border="1">
		<tr><td>img</td></tr>
		<tr>
			<td>판매자 ${item.seller_id}</td>
			<td>가격 : ${item.price}&nbsp;
			<%-- 로그인이 안된 경우 하트모양을 누르면 로그인 폼으로 넘어간다 --%>
				<c:if test="${empty sessionScope.id}">
				<input type="button" value="like" onclick="location.href='${ctxpath}/member/loginForm.do'"/>
				</c:if>
			<%-- 로그인이 된 경우 라이크 처리가 된다 --%>
				<c:if test="${!empty sessionScope.id}">
				<input type="button" value="like" /> <%-- wish 처리 --%>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>판매상태 ${item.status}</td>
			<td>제목 ${item.title}</td>
		</tr>
		<tr>
		<td><a href="">카테고리</a>|업데이트 시간 ${item.updated_at}</td>
		</tr>
		<tr>
			<td>
			내용 ${item.content}
			</td>
		</tr>
		<tr>
			<td>${item.view_count}</td>
		</tr>
	</table>
	<form action="${ctxpath}/item/detailPro.jsp?" >
	<%-- 로그인 안되어 있을 때 --%>
	
		<input type="button" value="쪽지쓰기" onclick="location.href='${ctxpath}/member/loginForm.do'" />
		
		
	<%-- 로그인 되어 있을 때 --%>
		<c:if test="${empty sessionScope.id}">
	<%-- 로그인 되어서 판매글 올렸을 경우 --%>
			<c:if test="${x==1}">
			<input type="button" value="수정하기" onclick="loaction.href='${ctxpath}/item/updateForm.do'" />
			<input type="button" value="삭제하기" onclick="return check()"/> <%-- ? --%>
			</c:if>
	<%-- 로그인 되어서 판매글이 내글이 아닌경우--%>
			<c:if test="${x==-1}">
			<input type="button" value="쪽지쓰기" onclick="location.href='${ctxpath}/msg/writeForm.do'" />		
			</c:if>
		</c:if>
	</form>
		
	<!-- <input type="button" value="쪽지" onclick="" />
	
	<input type="button" value="" onclick="" />
	<input type="button" value="쪽지" onclick="" /> -->
</body>
</html>