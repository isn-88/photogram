<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>View Post</title>
</head>

<body>

<%@ include file="navigation.jsp" %>


<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">
    <div class="row row-cols-1 row-cols-lg-3 g-3">
      <div class="col">
        <div class="card shadow" id="cardImages"
          style="min-height: 300px;">
          <div id="carouselImages" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <c:set var="isFirstImage" value="${true}"/>
              <c:forEach var="imageId" items="${requestScope.imageIds}">
                <div
                    class="carousel-item <c:if test="${isFirstImage}"><c:out value="active"/></c:if>">
                  <img src="${pageContext.request.contextPath}/image/download/${imageId}"
                       width="1080" height="1920" class="img-fluid"
                       alt="Фотография для публикации"/>
                </div>
                <c:set var="isFirstImage" value="${false}"/>
                <form
                    action="${pageContext.request.contextPath}/post/edit/${requestScope.post.id()}"
                    method="post">

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
                                    id="editPostDescription" maxlength="1000"
                                    rows="10">${requestScope.post.description()}</textarea>

                          <div class="form-check form-switch p-5">
                            <input class="form-check-input" type="checkbox" role="switch"
                                   id="flexSwitchCheckChecked" name="isActive" value="true"
                            <c:if test="${requestScope.post.isActive()}">
                              <c:out value="checked"/>
                            </c:if>>
                            <label class="form-check-label"
                                   for="flexSwitchCheckChecked">Опубликовано</label>
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
                <c:if test="${requestScope.imageIds.size() == 1}"><c:out value=" hidden"/></c:if>>
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <%--              <span class="visually-hidden">Предыдущий</span>--%>
            </button>
            <button class="carousel-control-next" type="button"
                    data-bs-target="#carouselImages" data-bs-slide="next"
                <c:if test="${requestScope.imageIds.size() == 1}"><c:out value=" hidden"/></c:if>>
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <%--              <span class="visually-hidden">Следующий</span>--%>
            </button>
          </div>

        </div>
      </div>
      <%--  Описание  --%>
      <div class="col" onload="handleResize();">
        <div class="card shadow" id="cardDescription">
          <div class="card-header">
            <h5 class="card-title text-center">Описание</h5>
          </div>
          <div class="card-body" style="overflow-y: auto">
            <p class="card-text">${requestScope.post.getDescriptionForPage()}</p>
            <%--            <p class="card-text"><small class="text-body-secondary">Последнее обновление 3 мин. назад</small></p>--%>
          </div>
          <div class="card-footer">
            <div
                class="d-grid gap-2 p-3 d-md-flex justify-content-md-around d-lg-grid justify-content-lg-center d-xl-flex justify-content-xl-between">
              <a href="${pageContext.request.contextPath}/home"
                 class="btn btn-outline-secondary" type="button">
                <i class="fa-solid fa-arrow-left"></i> Назад</a>
              <button class="btn btn-outline-warning" type="button"
                      data-bs-toggle="modal" data-bs-target="#editModal">
                <i class="fa-solid fa-pen-to-square"></i> Редактировать
              </button>
              <button class="btn btn-outline-danger" type="button"
                      data-bs-toggle="modal" data-bs-target="#deletePostModal">
                <i class="fa-regular fa-trash-can"></i> Удалить
              </button>
            </div>
          </div>
        </div>
      </div>


      <!-- Модальное окно -->
      <div class="modal fade" id="deletePostModal" tabindex="-1"
           aria-labelledby="deletePostModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="deletePostModalLabel">Удаление публикации</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal"
                      aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
              <span>Вы уверены, что хотите удалить эту публикацию?</span>
            </div>
            <form action="${pageContext.request.contextPath}/post/delete/${requestScope.post.id()}"
                  method="post">
              <div class="modal-footer">
                <form
                    action="${pageContext.request.contextPath}/post/delete/${requestScope.post.id()}"></form>
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                  Отмена
                </button>
                <button type="submit" class="btn btn-outline-danger">Удалить</button>
              </div>
            </form>
          </div>
        </div>
      </div>


      <div class="col" onload="handleResize();">
        <div class="card shadow" id="cardComment">
          <div class="card-header">
            <h5>Комментарии</h5>
          </div>
          <div class="card-body" style="overflow-y: auto">
            <div class="card-text">
              <c:forEach var="comment" items="${requestScope.comments}">
                <div class="row p-1">
                  <div class="col-2">
                  </div>
                  <div class="col-10">
                    <figure>
                      <blockquote class="blockquote fs-6">
                        <p><c:out value="${comment.message()}"/></p>
                      </blockquote>
                      <figcaption class="blockquote-footer">
                        <c:out value="${comment.elapsedTimeInfo()}"/>
                      </figcaption>
                    </figure>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>

          <div class="card-footer">
            <form
                action="${pageContext.request.contextPath}/comment/send/${requestScope.post.id()}"
                method="post">
              <div class="row row-cols-1 g-2">
                <div class="col">
                  <label for="createCommentMessage"
                         class="form-label ms-2 fs-5">Написать сообщение</label>
                  <textarea class="form-control" name="message" id="createCommentMessage"
                            rows="3" maxlength="500" required></textarea>
                </div>
                <div class="col d-flex justify-content-center">
                  <button class="btn btn-outline-primary" type="submit">
                    <i class="fa-solid fa-paper-plane"></i> Опубликовать
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


<script>
  function handleResize() {
    const sourceElement = document.getElementById("cardImages");
    const sourceInfo = sourceElement.getBoundingClientRect();
    const sourceHeight = sourceInfo.height;
    const elementCardDescription = document.querySelector('#cardDescription');
    elementCardDescription.style.maxHeight = sourceHeight + 'px';
    elementCardDescription.style.minHeight = sourceHeight + 'px';
    const elementCardComment = document.querySelector('#cardComment');
    elementCardComment.style.maxHeight = sourceHeight + 'px';
    elementCardComment.style.minHeight = sourceHeight + 'px';
  }

  window.addEventListener('resize', handleResize);
  window.addEventListener('load', handleResize);
</script>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>