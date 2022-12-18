<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="<%=request.getContextPath() %>" />
<html>
<div class="row justify-content-md-center py-5">
    <div class="w-100 text-center mb-5">
        <img src="${ctxpath}/static/images/loginLogo.png" style="height: 300px" />
    </div>
    <div class="w-25">
        <form method="post" action="${ctxpath}/member/loginPro.do">
            <div class="form-group">
                <label for="inputEmail">이메일 주소</label>
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address"
                    required="required" autofocus="autofocus">
            </div>
            <div class="form-group">
                <label for="inputPassword">비밀번호</label>
                <input type="password" name="pw" id="inputPassword" class="form-control" placeholder="Password"
                    required="required">
            </div>
            <div class="mb-3">
                <button class="btn btn-warning btn-block" type="submit"><i class="bi bi-box-arrow-in-left"></i>
                    로그인</button>
            </div>
            <div class="text-center">
                <a class="text-dark" href="${ctxpath }/member/inputForm.do">회원가입 </a> <span> | </span> <a
                    class="text-dark" href="${ctxpath }/member/findPwForm.do"> 비밀번호 찾기</a>
            </div>
            <p class="mt-5 mb-3 text-muted text-center">© 2022-2022</p>
        </form>
    </div>
</div>
</body>

</html>