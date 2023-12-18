<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <title>Error</title>
</head>
<body>

<div class="container text-center">
  <div class="row-cols-1 p-5 justify-content-center">
    <div class="col-4 offset-4">
      <div class="card">
        <div class="card-header">Ошибка</div>
        <div class="card-body">
          <div class="card-text"><i class="fa-solid fa-bug fa-5x"></i></div>

          <c:if test="${empty requestScope.errors}">
            <div class="card-text p-5">
              Произошла непредвиденная ошибка. Обратитесь в службу поддержки.
            </div>
          </c:if>

          <c:if test="${not empty requestScope.errors}">
            <div class="col-12 p-5">
              <span style="color: darkred">Ошибки при регистрации:</span>
              <ul>
                <c:forEach var="error" items="${requestScope.errors}">
                  <li style="color: darkred">${error.message()}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>

        </div>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/js/jasny-bootstrap.min.js"></script>
</body>
</html>
