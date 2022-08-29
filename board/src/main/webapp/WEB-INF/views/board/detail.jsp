<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${board.title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .footer {
            left: 0;
            bottom: 0;
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/tw">Study</a>
        <ul class="navbar-nav">
            <%--로그인 및 회원가입 링크 수정해야함--%>
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
    <h2><b>${board.title}</b></h2>
    <p>${board.nickname} 님</p>
</div>

<div class="container">
    <div class="mb-3 mt-3">
        <label for="title" class="form-label">제목:</label>
        <input type="text" class="form-control" id="title" name="title" readonly value="${board.title}">
    </div>
    <div class="mb-3">
        <label for="content" class="form-label">본문:</label>
        <textarea class="form-control" name="content" id="content" rows="10" readonly>${board.content}</textarea>
    </div>
    <div class="mb-3 mt-3">
        <label for="nickname" class="form-label">작성자 닉네임:</label>
        <input type="text" class="form-control" id="nickname" name="nickname" readonly value="${board.nickname}">
    </div>
    <hr>
        <button type="button" class="btn btn-outline-primary"
            onclick="location.href='/board/${board.id}/edit?pageNum=${param.pageNum}&amount=${param.amount}'">수정하기</button>
        <button type="button" class="btn btn-outline-dark"
                onclick="location.href='/board?pageNum=${param.pageNum}&amount=${param.amount}'">목록으로</button>
</div>

        <div class="mt-5 p-4 bg-dark text-white text-center">
        <footer>
        <p>Author: TW<br>
                <a href="mailto:hege@example.com">tw@example.com</a></p>
        </footer>
        </div>
</body>
</html>