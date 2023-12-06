

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/main.css" rel="stylesheet">
  <title>Home</title>
</head>

<body>
<div class="container-fluid">
  <nav class="navbar navbar-expand-md border-bottom">
    <div class="container">
      <i class="fa-solid fa-camera-retro"></i>
      <a class="navbar-brand text-uppercase p-1" href="#">Photogram</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Переключатель навигации">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        </ul>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/home/${username}">
              <i class="fa-solid fa-house-user"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/post/create/${username}">
              <i class="fa-solid fa-camera"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/edit/${username}">
              <i class="fa-solid fa-id-card"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">
              <i class="fa-solid fa-right-from-bracket"></i></a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>


<section>
  <div class="container">

    <div class="row">
      <div class="col-3">

      </div>
      <div class="col">
        <div class="row p-2">
          <div class="col">
            <p class="lead" style="font-size: xx-large; font-style: italic">${username}</p>
          </div>
        </div>
        <div class="row p-2">
          <div class="col">
            <p>0 публикаций</p>
          </div>
          <div class="col">
            <p>0 подписчиков</p>
          </div>
          <div class="col">
            <p>0 подписок</p>
          </div>
        </div>
        <div class="row p-2">
          <p>Обо мне Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, atque corporis
            doloribus esse eum fugiat molestias ratione reiciendis reprehenderit repudiandae.
            Consectetur deserunt eligendi facilis optio qui rem sunt tempora voluptas!</p>
        </div>
      </div>
    </div>
  </div>
  <hr/>
</section>

<section class="user-post">
  <div class="container">
    <div class="row">
      <c:forEach var="post" items="${posts}">
        <div class="col-sm-6 col-lg-4">

          <img src="data:image/jpg;base64,${images.get(post.id).base64Image()}"
               class="img-fluid" alt="<c:out value="${post.description}"/>">
        </div>
      </c:forEach>
    </div>
  </div>
</section>


<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/f297933945.js" crossorigin="anonymous"></script>
</body>
</html>
