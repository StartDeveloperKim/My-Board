<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>도전~</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Study</a>
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link text-light" href="#">로그인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="#">회원가입</a>
      </li>
    </ul>
  </div>
</nav>
<div class="container p-5 my-5 bg-dark text-white">
  <h2>안녕하세요~공부블로그 입니다</h2>
  <p>새로운 것을 공부할 때마다 기능이 추가됩니다!!!</p>
</div>

<div class="container mt-3">
  <h2><b>게시판</b></h2>
  <p style="font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;">기본적인 게시판</p>
  <table class="table">
    <thead>
    <tr>
      <th>글 번호</th>
      <th>제목</th>
      <th>닉네임</th>
      <th>등록날짜</th>
      <th>수정날짜</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${list}">
      <tr>
        <td>${board.id}</td>
        <td>${board.title}</td>
        <td>${board.nickname}</td>
        <td>${board.regdate}</td>
        <c:if test="${board.updatedate == null}">
          <td>-</td>
        </c:if>
        <c:if test="${board.updatedate != null}">
          <td>${board.updatedate}</td>
        </c:if>
        <td></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div >
    <button type="button" class="btn btn-outline-dark"
    onclick="location.href='/board'">더보기</button>
  </div>
</div>
<div class="mt-5 p-4 bg-dark text-white text-center">
  <footer>
    <p>Author: TW<br>
      <a href="mailto:hege@example.com">tw@example.com</a></p>
  </footer>
</div>
</body>
</html>
