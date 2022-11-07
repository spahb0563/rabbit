<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- template.jsp --%>
<html>
<body>
	<div class="container">
		<jsp:include page="/module/top.jsp" flush="false"/>
		<!-- content -->	
		<div>
		<jsp:include page="/main/main.jsp" flush="false"/>
		</div>
		<!-- content end-->
		<jsp:include page="/module/bottom.jsp"/>
	</div>
</body>
</html>    
    