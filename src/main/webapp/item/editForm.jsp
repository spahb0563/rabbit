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
    <form action="${ctxpath}/item/updatePro.do?id=${item.id}" enctype="multipart/form-data" method="post" onsubmit="return editForm.update()">
        <div id='image_preview'>
            <input type='file' id='btnAtt' name="file" accept="image/png, image/jpeg, image/jpg" multiple="multiple"/>
            <div id='att_zone' data-placeholder='파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요'>
            <c:forEach var="file" items="${fileList}">
	            <div style="display:inline-block; position:relative; width:130px;height:120px;margin:5px;margin-left:10px; margin-right:15px;border:1px solid black;z-index:1">
		            <img style="width:100%;height:102%;" src="/img/${file.savedname}">
		            <button class="btn-delete-file" type="button" style="width:20px;height:25px;position:absolute;font-size:15px;right:0px;bottom:95px;z-index:0;background-color: transparent;color:orange; border:1px" value="${file.id}">
		            	x
		            </button>
	            </div>
            </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${item.title}"> 
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="15"><c:out value="${item.content}" escapeXml="true"></c:out></textarea>
        </div>
        <div class="row">
			<div class="form-group col-6">
	        	<label for="category">카테고리</label>
	        	<select class="form-control" id="category" name="category">
	        			<option>카테고리를 선택해주세요.</option>
	        		<c:forEach var="category" items="${categoryList}">
	        			<option value="${category.id}" <c:if test="${item.category == category.type}">selected</c:if>>${category.type}</option>
	        		</c:forEach>
	        	</select>	
	        </div>
	        <div class="form-group col-6">
	        	<label for="price">가격</label>
				<input type="text" class="form-control" id="price" name="price" value="${item.price}">
	        </div>
        </div>
        <div class="row">
            <div class="col text-right">
                <a class="btn btn-dark" href="${ctxpath}/main/viewMain.do">취소</a>
                <input class="btn btn-warning" type="submit" id="update-file" value="수정"/>
            </div>
        </div>
    </form>
</div>

<script src="${ctxpath}/static/app/js/imageUploader.js?ver=5"></script>
<script src="${ctxpath}/static/app/js/editForm.js?ver=3"></script>