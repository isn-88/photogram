<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>Login</title>
</head>

<body>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-sm-10 col-md-8 col-xl-6 p-5">
      <div class="card shadow">
        <div class="card-header text-center">
          <h2 class="text-uppercase">Photogram</h2>
          <span>Войдите для просмотра фотографий ваших друзей</span>
        </div>
        <div class="card-body">
          <div class="card-text">
            <form class="row gy-4" action="${pageContext.request.contextPath}/login" method="post">

              <div class="col-12">
                <div class="form-text">Введите логин, номер телефона или email
                </div>
                <div class="input-group">
                  <input type="text" class="form-control" name="login" id="inputLogin"
                         value="${requestScope.login}" aria-label="Логин"
                         placeholder="Логин / Номер телефона / Email" required>
                </div>
              </div>

              <div class="col-12">
                <input type="password" class="form-control" id="inputPassword"
                       name="password" placeholder="Пароль" aria-label="Пароль" required>
                <div id="passwordHelpBlock" class="form-text">
                  Не помните пароль? <a href="#">Сбросить</a>
                </div>
              </div>

              <c:if test="${not empty requestScope.errors}">
                <div class="col-12">
                  <span style="color: darkred">Ошибки при входе:</span>
                  <ul>
                    <c:forEach var="error" items="${requestScope.errors}">
                      <li style="color: darkred">${error.message()}</li>
                    </c:forEach>
                  </ul>
                </div>
              </c:if>

              <div class="d-grid">
                <input class="btn btn-outline-secondary" type="submit" value="Войти">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col col-sm-10 col-md-8 col-xl-6 p-5">
      <div class="card shadow">
        <div class="card-body">
          <div class="card-text">

            <h5>Нет аккаунта? <a href="${pageContext.request.contextPath}/registration">Зарегистрировать</a>
            </h5>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
