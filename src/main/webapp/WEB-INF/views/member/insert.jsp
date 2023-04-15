<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에서 사용하는 meta tag -->
  <link rel="stylesheet" href="../../../css/bootstrap.css"> <!-- 참조  -->
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="../../../js/bootstrap.js"></script>
  <title>JSP 게시판 웹 사이트</title>
</head>
<body>
<nav class ="navbar navbar-default">
  <div class="navbar-header"> <!-- 홈페이지 로고 -->
    <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expand="false">
      <span class ="icon-bar"></span> <!-- 화면 줄였을 때 아이콘 바 -->
      <span class ="icon-bar"></span>
      <span class ="icon-bar"></span>
    </button>
    <a class ="navbar-brand">JSP 게시판 웹 사이트</a>
  </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  </div>
</nav>

<div class="container">
  <div class="col-lg-4"></div>
  <div class="col-lg-4">
    <div class ="jumbotron" style="padding-top:20px;">
      <form method = "post" id="insertForm" action="/user/insertMember">
        <h3 style="text-align:center;">회원가입</h3>
        <div class ="form-group">
          <input type ="text" class="form-control" placeholder="아이디" name ="id" maxlength='20'>
        </div>
        <div class ="form-group">
          <input type ="password" class="form-control" placeholder="비밀번호" name ="password" maxlength='20'>
        </div>
        <div class ="form-group">
          <input type ="text" class="form-control" placeholder="닉네임" name ="nickname" maxlength='20'>
        </div>
      </form>
      <button class="btn btn-primary form-control" onclick="memberInsert()">회원 가입</button>
      <button class="btn btn-primary form-control" style="margin-top: 10px;" onclick="location.href='/user/main'">뒤로 가기</button>
    </div>
  </div>
  <div class="col-lg-4"></div>
</div>
<script type="text/javascript">
  const memberInsert = () => {
    $.ajax({
      type: 'post',
      url: '<c:url value="/user/insertMember"/>',
      data: $("#insertForm").serialize(),
      dataType: 'json',
      success: function (data) {
        alert(data.nickname + "님 회원가입 되셨습니다.");
        location.href = '/user/main';
      }, error: function (e) {
        console.log(e);
        alert("오류가 발생했습니다.");
      },
    });
  };
</script>
</body>
</html>