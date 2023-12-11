<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${contextPath}/css/main.css" rel="stylesheet">
  <title>View Post</title>
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
    <div class="row p-3">
      <div class="col">
        <div class="card mb-3">
          <div class="row g-0">
            <div class="col-lg-4">

              <div id="carouselImages" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                  <c:set var="isFirstImage" value="${true}"/>
                  <c:forEach var="image" items="${images}">
                    <div
                        class="carousel-item <c:if test="${isFirstImage}"><c:out value="active"/></c:if>">
                      <img src="data:image/jpg;base64,${image.base64Image()}"
                           class="card-img-top" alt="post picture"/>
                    </div>
                    <c:set var="isFirstImage" value="${false}"/>
                    <form action="/post/edit/${username}?id=${post.id()}" method="post">
                      <!-- Модальное окно -->
                      <div class="modal fade" id="editModal" tabindex="-1"
                           aria-labelledby="editModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1 class="modal-title fs-5" id="editModalLabel">Редактирование</h1>
                              <button type="button" class="btn-close" data-bs-dismiss="modal"
                                      aria-label="Закрыть"></button>
                            </div>
                            <div class="modal-body">
                              <label for="editPostDescription"
                                     class="form-label ms-2 fs-5">Описание</label>
                              <textarea class="form-control" name="description"
                                        id="editPostDescription"
                                        rows="10" maxlength="1000">${post.description()}</textarea>

                              <div class="form-check form-switch p-5">
                                <input class="form-check-input" type="checkbox" role="switch"
                                       id="flexSwitchCheckChecked" name="isActive" value="true"
                                  <c:if test="${post.isActive()}">
                                    <c:out value="checked"/>
                                  </c:if>>
                                <label class="form-check-label" for="flexSwitchCheckChecked">Опубликовано</label>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-outline-secondary"
                                      data-bs-dismiss="modal">
                                <i class="fa-solid fa-xmark"></i> Закрыть
                              </button>
                              <button type="submit" class="btn btn-outline-success">
                                <i class="fa-solid fa-check"></i> Сохранить изменения
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </form>
                  </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button"
                        data-bs-target="#carouselImages" data-bs-slide="prev"
                    <c:if test="${images.size() == 1}"><c:out value=" hidden"/></c:if>>
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <%--              <span class="visually-hidden">Предыдущий</span>--%>
                </button>
                <button class="carousel-control-next" type="button"
                        data-bs-target="#carouselImages" data-bs-slide="next"
                    <c:if test="${images.size() == 1}"><c:out value=" hidden"/></c:if>>
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <%--              <span class="visually-hidden">Следующий</span>--%>
                </button>
              </div>
            </div>

            <div class="col-lg-4">
              <div class="card-body h-100">

                <div class="d-grid gap-2 p-3 d-lx-flex justify-content-lx-between">
                  <a href="${pageContext.request.contextPath}/home/${username}"
                     class="btn btn-outline-secondary" type="button">
                    <i class="fa-solid fa-arrow-left"></i> Назад</a>
                  <button class="btn btn-outline-warning" type="button"
                          data-bs-toggle="modal" data-bs-target="#editModal">
                    <i class="fa-solid fa-pen-to-square"></i> Редактировать
                  </button>
                </div>

                <hr class="hr"/>

                <h5 class="card-title p-1 text-center">Описание</h5>
                <p class="card-text">${post.getDescriptionForPage()}</p>
                <%--            <p class="card-text"><small class="text-body-secondary">Последнее обновление 3 мин. назад</small></p>--%>

              </div>
            </div>

            <div class="col-lg-4">
              <div class="card-footer h-100 border-start">
                <h5>Комментарии</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
