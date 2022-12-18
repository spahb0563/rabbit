<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/header/header.jsp"%>
<%-- template.jsp --%>
<html>
<body>
	<div class="container-md">
		<jsp:include page="/module/top.jsp" flush="false"/>
		<!-- content -->	
		<div class="container-md">
			<jsp:include page="${CONTENT}" flush="false"/> 
		</div>
		<!-- content end-->
		<jsp:include page="/module/bottom.jsp"/>
	</div>
</body>
<script type="text/javascript" src="${ctxpath}/static/app/js/paging.js"></script>
<script type="text/javascript" src="${ctxpath}/static/app/js/msg.js?ver=6"></script>
<script type="text/javascript" src="${ctxpath}/static/app/js/address.js"></script>
</html>
