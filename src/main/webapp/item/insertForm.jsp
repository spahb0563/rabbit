<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<style>
    #att_zone {
        width: 660px;
        min-height: 150px;
        padding: 10px;
        border: 1px dotted #00f;
    }

    #att_zone:empty:before {
        content: attr(data-placeholder);
        color: #999;
        font-size: .9em;
    }
</style>
<div class="row justify-content-md-center">
    <form action="${ctxpath}/item/insertPro.do" enctype="multipart/form-data" method="post" onsubmit="return insertForm.check()">
        <div id='image_preview'>
            <input type='file' id='btnAtt' name="file" accept="image/png, image/jpeg, image/jpg" multiple="multiple"/>
            <div id='att_zone' data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'></div>
        </div>
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="15"></textarea>
        </div>
        <div class="row">
			<div class="form-group col-6">
	        	<label for="category">카테고리</label>
	        	<select class="form-control" id="category" name="category">
	        			<option>카테고리를 선택해주세요.</option>
	        		<c:forEach var="category" items="${categoryList}">
	        			<option value="${category.id}">${category.type}</option>
	        		</c:forEach>
	        	</select>	
	        </div>
	        <div class="form-group col-6">
	        	<label for="price">가격</label>
				<input type="text" class="form-control" id="price" name="price">
	        </div>
        </div>
        <div class="row">
            <div class="col text-right">
                <a class="btn btn-dark" href="${ctxpath}/main/viewMain.do">취소</a>
                <input class="btn btn-warning" type="submit" id="btn-upload-file" value="작성"/>
            </div>
        </div>
    </form>
</div>
<script src="${ctxpath}/static/app/js/imageUploader.js?ver=5"></script>
<script src="${ctxpath}/static/app/js/insertForm.js"></script>