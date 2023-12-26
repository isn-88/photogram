<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">


<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>Search</title>
</head>

<body>

<%@ include file="include/navigation.jsp" %>


<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 gy-5">

      <c:forEach var="accountDto" items="${requestScope.accountFindList}">
        <div class="col">
          <div class="card card-body text-center"
               style="min-width: 160px; max-width: 160px; margin-right: 20px;">

            <a href="${pageContext.request.contextPath}/home/${accountDto.username()}">
              <img
                  src="${pageContext.request.contextPath}/icon/download/${accountDto.id()}"
                  alt="profile icon" class="img-fluid rounded-circle mb-2"
                  onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
                  style="width: 120px; height: 120px"/></a>
            <h5 class="mb-0">${accountDto.username()}</h5>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</section>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
