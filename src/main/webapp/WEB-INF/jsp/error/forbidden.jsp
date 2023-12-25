<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <title>Forbidden</title>
</head>
<body>

<div class="container text-center">
  <div class="row-cols-1 p-5 justify-content-center">
    <div class="col-4 offset-4">
      <div class="card">
        <div class="card-header">Ошибка доступа</div>
        <div class="card-body">
          <div class="card-text"><i class="link-warning fa-solid fa-triangle-exclamation fa-5x"></i></div>

          <div class="card-text p-5">
            К сожалению, у Вас нет доступа на эту страницу.
          </div>

        </div>
        <div class="card-footer">
          <div class="card-text">Есть другой аккаунт?</div>
          <a href="${pageContext.request.contextPath}/login" class="mt-3 btn btn-outline-primary"
             type="button">Войти</a>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/js/jasny-bootstrap.min.js"></script>
</body>
</html>

