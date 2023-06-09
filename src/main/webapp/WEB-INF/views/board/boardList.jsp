<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <div class="row">
    <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
      <thead>
        <th style="background-color: #eeeeee; text-align: center;">번호</th>
        <th style="background-color: #eeeeee; text-align: center;">제목</th>
        <th style="background-color: #eeeeee; text-align: center;">작성자</th>
        <th style="background-color: #eeeeee; text-align: center;">작성일</th>
      <tr>

      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${boardList}" varStatus="status">
        <fmt:parseDate value="${item.indate}" var="registered" pattern="yyyyMMddHHmmss" />
        <tr>
          <td>${status.count}</td>
          <td><a onclick="goDetail(${item.boardseq})">${item.title}</a></td>
          <td>${item.member.nickname}</td>
          <td><fmt:formatDate value="${registered}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <button class="btn btn-primary" onclick="createTable()">생성</button>
  </div>
</div>
<script type="text/javascript">
  const logout = () => {
    location.href = "/user/main";
  };

  const createTable = () => {
    location.href = "/board/create";
  };

  const goDetail = boardseq => {
    location.href = "/board/detailBoard?boardseq=" + encodeURI(boardseq);
  };
</script>
</body>
</html>