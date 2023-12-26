<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>Login</title>
</head>

<body>


<%@ include file="include/locale.jsp" %>


<div class="container">
  <div class="row justify-content-center">
    <div class="col-sm-10 col-md-8 col-xl-6 p-5">
      <div class="card shadow">
        <div class="card-header">


          <%@ include file="include/locale-menu.jsp" %>


          <div class="text-center">
            <h3 class="fst-italic">Photogram</h3>
            <span><fmt:message key="page.login.header"/></span>
          </div>
        </div>
        <div class="card-body">
          <div class="card-text">
            <form class="row gy-4" action="${pageContext.request.contextPath}/login" method="post">

              <div class="col-12">
                <div class="form-text"><fmt:message key="page.login.login.label"/></div>
                <div class="input-group">
                  <input type="text" class="form-control" name="login" id="inputLogin"
                         value="${requestScope.login}" aria-label="Login"
                         placeholder="<fmt:message key="page.login.login.placeholder"/>"
                         required>
                </div>
              </div>

              <div class="col-12">
                <input type="password" class="form-control" id="inputPassword"
                       name="password" aria-label="Password"
                       placeholder="<fmt:message key="page.login.password.placeholder"/>"
                       required>
                <div id="passwordHelpBlock" class="form-text">
                  <fmt:message key="page.login.recover.message"/>
                  <a href="#"><fmt:message key="page.login.recover.button"/></a>
                </div>
              </div>

              <c:if test="${not empty requestScope.errors}">
                <div class="col-12">
                  <span style="color: darkred"><fmt:message key="page.login.error.label"/></span>
                  <ul>
                    <c:forEach var="error" items="${requestScope.errors}">
                      <li style="color: darkred">
                        <fmt:message key="${error.message()}"/></li>
                    </c:forEach>
                  </ul>
                </div>
              </c:if>

              <div class="d-grid">
                <input class="btn btn-outline-secondary" type="submit"
                       value="<fmt:message key="page.login.sign.in.button"/>">
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

            <h5><fmt:message key="page.login.no.account"/>
              <a href="${pageContext.request.contextPath}/registration">
                <fmt:message key="page.login.registration.link"/></a>
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
