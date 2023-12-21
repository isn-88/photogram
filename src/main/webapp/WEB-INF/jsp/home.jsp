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
      <div class="col-12 col-md-4 col-lg-3 col-xxl-2">
        <div class="mb-3 d-flex flex-column align-items-center">
          <p class="lead"
             style="font-size: xx-large; font-style: italic">${requestScope.account.username()}</p>
          <img src="${pageContext.request.contextPath}/icon/download/${requestScope.account.id()}"
               alt="profile icon" class="img-fluid rounded-circle mb-2"
               onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
               style="width: 180px; height: 180px"/>
          <h5 class="mb-0">${requestScope.profile.fullName()}</h5>
        </div>
      </div>
      <div class="col">
        <div class="row p-2">
          <div class="col-12 col-md-3 col-lg-2">
            <div class="row row-cols-1 row-cols-sm-3 row-cols-md-1 mt-md-5 gy-2 text-center">
              <div class="col">
                <span>публикаций</span><br>
                <span class="fs-4"><c:out value="${requestScope.postCount}"/></span>
              </div>
              <div class="col">
                <span>подписок</span><br>
                <span class="fs-4"><c:out value="${requestScope.subscribeCount}"/></span>
              </div>
              <div class="col">
                <span>подписчиков</span><br>
                <span class="fs-4"><c:out value="${requestScope.subscribersCount}"/></span>
              </div>
            </div>
          </div>

          <div class="col">
            <div class="row p-2">
              <h5>Обо мне</h5>
              <p>${requestScope.profile.aboutMe()}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <hr/>
</section>


<section>
  <div class="container">
    <ul class="nav nav-underline" id="adviceTab" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link active" id="advice-tab" data-bs-toggle="tab"
                data-bs-target="#advice-tab-pane"
                type="button" role="tab" aria-controls="advice-tab-pane"
                aria-selected="true">Рекомендуем подписаться
        </button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="subscribe-tab" data-bs-toggle="tab"
                data-bs-target="#subscribe-tab-pane"
                type="button" role="tab" aria-controls="subscribe-tab-pane"
                aria-selected="false">Вы подписаны
        </button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="subscribers-tab" data-bs-toggle="tab"
                data-bs-target="#subscribers-tab-pane"
                type="button" role="tab" aria-controls="subscribers-tab-pane"
                aria-selected="false">Ваши подписчики
        </button>
      </li>
    </ul>
    <div class="tab-content" id="adviceTabContent">
      <%--  Рекомендуем подписаться  --%>
      <div class="tab-pane fade show active" id="advice-tab-pane" role="tabpanel"
           aria-labelledby="advice-tab" tabindex="0">
        <div class="row p-3 overflow-x-auto flex-nowrap">
          <c:forEach var="adviceDto" items="${requestScope.adviceDtoList}">
            <div class="card card-body text-center"
                 style="min-width: 160px; max-width: 160px; margin-right: 20px;">
              <form action="${pageContext.request.contextPath}/subscribe/${adviceDto.id()}"
                    method="post">
                <a href="${pageContext.request.contextPath}/home/${adviceDto.username()}">
                  <img src="${pageContext.request.contextPath}/icon/download/${adviceDto.id()}"
                       alt="profile icon" class="img-fluid rounded-circle mb-2"
                       onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
                       style="width: 120px; height: 120px"/></a>
                <h5 class="mb-0">${adviceDto.username()}</h5>

                <label>
                  <input type="text" id="inputAdviceId" name="subscribeId"
                         value="${adviceDto.id()}" hidden>
                </label>
                <button type="submit" class="btn mt-2 m-auto btn-outline-primary">Подписаться
                </button>
              </form>
            </div>
          </c:forEach>
        </div>
      </div>

      <%--  Вы подписаны  --%>
      <div class="tab-pane fade" id="subscribe-tab-pane" role="tabpanel"
           aria-labelledby="subscribe-tab"
           tabindex="0">
        <div class="row p-3 overflow-x-auto flex-nowrap">
          <c:forEach var="subscribeDto" items="${requestScope.subscribeDtoList}">
            <div class="card card-body text-center"
                 style="min-width: 160px; max-width: 160px; margin-right: 20px;">
              <form
                  action="${pageContext.request.contextPath}/unsubscribe/${subscribeDto.id()}"
                  method="post">
                <a href="${pageContext.request.contextPath}/home/${subscribeDto.username()}">
                  <img
                      src="${pageContext.request.contextPath}/icon/download/${subscribeDto.id()}"
                      alt="profile icon" class="img-fluid rounded-circle mb-2"
                      onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
                      style="width: 120px; height: 120px"/></a>
                <h5 class="mb-0">${subscribeDto.username()}</h5>
                <button type="submit" class="btn mt-2 m-auto btn-outline-danger">Отписаться</button>
              </form>
            </div>
          </c:forEach>
        </div>
      </div>

      <%--  Ваши подписчики  --%>
      <div class="tab-pane fade" id="subscribers-tab-pane" role="tabpanel"
           aria-labelledby="subscribers-tab" tabindex="0">
        <div class="row p-3 overflow-x-auto flex-nowrap">
          <c:forEach var="subscribersDto" items="${requestScope.subscribersDtoList}">
            <div class="card card-body text-center"
                 style="min-width: 160px; max-width: 160px; margin-right: 20px;">
              <form
                  action="${pageContext.request.contextPath}/unsubscribe/${subscribersDto.id()}"
                  method="post">
                <a href="${pageContext.request.contextPath}/home/${subscribersDto.username()}">
                  <img
                      src="${pageContext.request.contextPath}/icon/download/${subscribersDto.id()}"
                      alt="profile icon" class="img-fluid rounded-circle mb-2"
                      onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
                      style="width: 120px; height: 120px"/></a>
                <h5 class="mb-0">${subscribersDto.username()}</h5>
              </form>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</section>


<section style="padding-top: 20px; padding-bottom: 10px;">
  <div class="container">

    <ul class="nav nav-underline" id="postTab" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link active" id="my-post-tab" data-bs-toggle="tab"
                data-bs-target="#my-post-tab-pane"
                type="button" role="tab" aria-controls="my-post-tab-pane"
                aria-selected="true">Мои публикации
        </button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="subscribers-post-tab" data-bs-toggle="tab"
                data-bs-target="#subscribers-post-tab-pane"
                type="button" role="tab" aria-controls="subscribers-post-tab-pane"
                aria-selected="false">Подписки
        </button>
      </li>

    </ul>
    <div class="tab-content" id="postTabContent">
      <%--  Мои публикации  --%>
      <div class="tab-pane fade show active" id="my-post-tab-pane" role="tabpanel"
           aria-labelledby="my-post-tab" tabindex="0">
        <div class="row">
          <c:forEach var="post" items="${requestScope.posts}">
            <div class="col-sm-6 col-lg-4 p-3">
              <c:if test="${sessionScope.account.id() ne requestScope.account.id()}">
                <c:set var="queryParam" value="?view=${requestScope.account.username()}"/>
              </c:if>
              <a href="${pageContext.request.contextPath}/post/${post.id()}${queryParam}">
                <img
                    src="${pageContext.request.contextPath}/image/download/${requestScope.postToImageMap.get(post.id())}"
                    width="1080" height="1920"
                    class="img-thumbnail <c:if test="${post.isActive() eq 'false'}"><c:out value="opacity-25"/></c:if>"
                    alt="Фотография для публикации">
              </a>
            </div>
          </c:forEach>
        </div>
      </div>


      <%--  Подписки  --%>
      <div class="tab-pane fade" id="subscribers-post-tab-pane" role="tabpanel"
           aria-labelledby="subscribers-post-tab" tabindex="0">
        <div class="row">
          <c:forEach var="post" items="${requestScope.subscribePosts}">
            <div class="col-sm-6 col-lg-4 p-3">
              <c:if test="${sessionScope.account.id() ne requestScope.account.id()}">
                <c:set var="queryParam" value="?view=${requestScope.account.username()}"/>
              </c:if>
              <a href="${pageContext.request.contextPath}/post/${post.id()}${queryParam}">
                <img
                    src="${pageContext.request.contextPath}/image/download/${requestScope.postSubscribeToImageMap.get(post.id())}"
                    width="1080" height="1920"
                    class="img-thumbnail <c:if test="${post.isActive() eq 'false'}"><c:out value="opacity-25"/></c:if>"
                    alt="Фотография для публикации">
              </a>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</section>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
