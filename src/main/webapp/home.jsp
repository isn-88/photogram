

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${contextPath}/css/main.css" rel="stylesheet">
  <title>Home</title>
</head>

<body>

<section>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md border-bottom bg-body-tertiary fixed-top">
      <div class="container">
        <i class="fa-solid fa-camera-retro fa-lg"></i>
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
                <i class="fa-solid fa-house-user fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/post/create/${username}">
                <i class="fa-solid fa-camera fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/edit/${username}">
                <i class="fa-solid fa-id-card fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket fa-lg"></i></a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</section>


<section style="padding-top: 80px; padding-bottom: 10px;">
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
          <h5>Обо мне</h5>
          <p>${profile.aboutMe()}</p>
        </div>
      </div>
    </div>
  </div>
  <hr/>
</section>

<section style="padding-top: 20px; padding-bottom: 10px;">
  <div class="container">
    <div class="row">
      <c:forEach var="post" items="${posts}">
        <div class="col-sm-6 col-lg-4 p-3">
          <a href="${pageContext.request.contextPath}/post/${username}?id=${post.id()}">
            <img src="data:image/jpg;base64,${images.get(post.id()).base64Image()}"
                 class="img-thumbnail <c:if test="${post.isActive() eq 'false'}"><c:out value="opacity-25"/></c:if>"
                 alt="<c:out value="${post.description()}"/>">
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</section>


<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
