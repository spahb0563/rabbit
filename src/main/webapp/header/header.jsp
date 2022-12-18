<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--header.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctxpath" value="<%=request.getContextPath()%>" scope="session"/>
<head>
	<title>당근마켓</title>
	<meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="${ctxpath}/static/images/favicon.png">
 	<link rel="stylesheet" href="${ctxpath}/static/lib/css/bootstrap.css" type="text/css">
 	<link rel="stylesheet" href="${ctxpath}/static/app/css/pagination.css" type="text/css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link href="https://webfontworld.github.io/SCoreDream/SCoreDream.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	<style>
		body {
			font-family: 'SCoreDream';
		}
	</style>
</head>
