<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>Home</title>
</head>

<body>

<%@ include file="navigation.jsp" %>


<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">

    <div class="row">
      <div class="col-12 col-md-6 col-lg-3">
        <div class="mb-3 d-flex flex-column align-items-center">
          <img src="data:image/jpg;base64,${requestScope.icon.iconBase64()}" alt="profile icon"
              class="img-fluid rounded-circle mb-2"
               onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
              style="width: 180px; height: 180px"/>
          <h5 class="mb-0">${requestScope.profile.fullName()}</h5>
        </div>
      </div>
      <div class="col">
        <div class="row p-2">
          <div class="col">
            <p class="lead" style="font-size: xx-large; font-style: italic">${sessionScope.account.username()}</p>
          </div>
        </div>
        <div class="row row-cols-3 p-2">
          <div class="col">
            <p><c:out value="${requestScope.postCount}"/> публикаций</p>
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
          <p>${requestScope.profile.aboutMe()}</p>
        </div>
      </div>
    </div>
  </div>
  <hr/>
</section>

<section style="padding-top: 20px; padding-bottom: 10px;">
  <div class="container">
    <div class="row">
      <c:forEach var="post" items="${requestScope.posts}">
        <div class="col-sm-6 col-lg-4 p-3">
          <a href="${pageContext.request.contextPath}/post/${post.id()}">
            <img src="${pageContext.request.contextPath}/image/download/${requestScope.postToImageMap.get(post.id())}"
                 width="1080" height="1920"
                 class="img-thumbnail <c:if test="${post.isActive() eq 'false'}"><c:out value="opacity-25"/></c:if>"
                 alt="Фотография для публикации">
          </a>
        </div>
      </c:forEach>
    </div>
  </div>
</section>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
