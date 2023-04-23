<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
  <meta name="viewport" content="width=device-width" initial-scale="1">
  <link rel="stylesheet" href="../../../css/bootstrap.css">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="../../../js/bootstrap.js"></script>
  <title>JSP 게시판 웹 사이트</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expanded="false">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand">JSP 게시판 웹 사이트</a>
      <span>${loginMember.nickname}님 어서오세요</span>
      <button class="btn btn-primary" onclick="logout()">로그 아웃</button>
    </div>
  </div>
</nav>
<div class="container">
  <form id="boardForm">
    <div class="row">
      <span>제목</span></br>
      <input type="text" id="title" name="title" placeholder="제목을 입력하세요"></br>
      <span>내용</span></br>
      <textarea id="contents" name="contents" cols="50" rows="20" placeholder="내용을 입력하세요"></textarea>
    </div>
  </form>
  <button class="btn btn-primary" onclick="history.go(-1)">뒤로가기</button>
  <button class="btn btn-primary" onclick="createBoard()">저장</button>
</div>
<script type="text/javascript">
  const logout = () => {
    location.href = "/user/main";
  };

  const createBoard = () => {
    $.ajax({
      type: 'post',
      url: '<c:url value="/board/insertboard"/>',
      data: $("#boardForm").serialize(),
      dataType: 'json',
      success: function (board) {
        let isboard = !!board;
        if (isboard) {
          alert('등록되었습니다.');
          location.href = '/board/main'
        }
      }, error: function (e) {
        console.log(e)
      },
    });
  }
</script>
</body>
</html>