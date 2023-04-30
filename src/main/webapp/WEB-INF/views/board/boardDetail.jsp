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
    <input type="hidden" id="boardseq" name="boardseq" value="${detailBoard.boardseq}"/>
    <div class="row">
      <h2>제목</h2></br>
      <div class="text-left">${detailBoard.title}</div></br>
      <h2>내용</h2></br>
      <div class="text-left">${detailBoard.contents}</div>
    </div>
    <div class="container">
      </br><span>${loginMember.nickname}</span></br>
      <textarea id="contents" cols="50" rows="3" name=contents placeholder="댓글을 입력해 주세요."></textarea>
      <button class="btn-primary" onclick="insertComment()">등록</button>
    </div>
  </form>
  <button class="btn btn-primary" onclick="location.href='/board/main'">뒤로가기</button>
  <c:if test="${loginMember.memberseq eq detailBoard.member.memberseq}">
    <button class="btn btn-primary" onclick="modifyBoard()">수정하기</button>
    <button class="btn btn-primary" onclick="deleteBoard()">삭제하기</button>
  </c:if>
</div>
<script type="text/javascript">
  const logout = () => {
    location.href = "/user/main";
  };

  const modifyBoard = () => {
    $("#boardForm").attr({
      type: "post",
      action: "<c:url value='/board/modifyBoard'/>"
    });
    $("#boardForm").submit();
  };

  const deleteBoard = () => {
    if (confirm("게시물을 삭제하시겠습니까?")) {
      $.ajax({
        type: "post",
        url: "<c:url value="/board/deleteBoard"/>",
        data: $("#boardForm").serialize(),
        dataType: "text",
        success: function (msg) {
          alert(msg);
          location.href = "/board/main";
        }, error: function (e) {
          alert("오류가 발생했습니다.");
        },
      })
    }
  };

  const insertComment = () => {
    if (confirm("댓글을 등록하시겠습니까?")) {
      $.ajax({
        type: "post",
        url: "<c:url value="/comment/insertComment"/>",
        data: $("#boardForm").serialize(),
        dataType: "text",
        success: function (msg) {
          alert(msg);
          location.reload();
        }, error: function (e) {
          alert("오류가 발생했습니다.");
        },
      });
    }
  }
</script>
</body>
</html>