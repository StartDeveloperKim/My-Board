<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 등록</title>
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
    <h2>글 등록하세요</h2>
    <p>대신 욕은 금물!!</p>
</div>

<div class="container">
    <form action="/board/new" method="post">
        <div class="mb-3 mt-3">
            <label for="title" class="form-label">제목:</label>
            <input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" name="title">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">본문:</label>
            <textarea class="form-control" name="content" id="content" rows="10" placeholder="본문을 입력해주세요"></textarea>
        </div>
        <div class="mb-3 mt-3">
            <label for="nickname" class="form-label">작성자 닉네임:</label>
            <input type="text" class="form-control" id="nickname" placeholder="제목을 입력해주세요" name="nickname">
        </div>
        <button type="submit" class="btn btn-outline-primary"
                onclick="location.href='/board/new'">등록하기</button>
    </form>
</div>

<div class="mt-5 p-4 bg-dark text-white text-center">
    <footer>
        <p>Author: TW<br>
            <a href="mailto:hege@example.com">tw@example.com</a></p>
    </footer>
</div>
</body>
</html>
