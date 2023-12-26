<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <title>Registration</title>
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
            <span><fmt:message key="page.register.header.title"/></span>
          </div>
        </div>
        <div class="card-body">
          <div class="card-text">
            <form class="row gy-4" action="${pageContext.request.contextPath}/registration"
                  method="post">
              <div class="col-12">
                <div class="form-text"><fmt:message key="page.register.login.label"/></div>
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon-plus">+</span>
                  <input type="text" class="form-control" name="phone"
                         value="${requestScope.phone}"
                         placeholder="<fmt:message key="page.register.phone.placeholder"/>"
                         aria-describedby="basic-addon-plus" aria-label="">
                </div>
              </div>
              <div class="col-12">
                <input class="form-control" type="text" name="email"
                       value="${requestScope.email}"
                       placeholder="<fmt:message key="page.register.email.placeholder"/>"
                       aria-label="">
              </div>
              <div class="col-12">
                <input class="form-control" type="text" name="full_name"
                       value="${requestScope.full_name}"
                       placeholder="<fmt:message key="page.register.full-name.placeholder"/>"
                       aria-label="">
              </div>
              <div class="col-12">
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon-email">@</span>
                  <input type="text" class="form-control" name="username"
                         value="${requestScope.username}"
                         placeholder="<fmt:message key="page.register.username.placeholder"/>"
                         aria-describedby="basic-addon-email" aria-label="" required>
                </div>
              </div>
              <div class="col-12">
                <input type="password" class="form-control" id="inputPassword"
                       name="password" aria-label="Password"
                       placeholder="<fmt:message key="page.register.password.placeholder"/>"
                       required>
                <div id="passwordHelpBlock" class="form-text">
                  <fmt:message key="page.register.password.help"/>
                </div>
              </div>

              <c:if test="${not empty requestScope.errors}">
                <div class="col-12">
                  <span style="color: darkred">
                    <fmt:message key="page.register.error.title"/>
                  </span>
                  <ul>
                    <c:forEach var="error" items="${requestScope.errors}">
                      <li style="color: darkred">
                        <fmt:message key="${error.message()}"/>
                      </li>
                    </c:forEach>
                  </ul>
                </div>
              </c:if>

              <div class="d-grid">
                <input class="btn btn-outline-secondary" type="submit"
                       value="<fmt:message key="page.register.register.button"/>">
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
            <h5><fmt:message key="page.register.has.account"/>
              <a href="${pageContext.request.contextPath}/login">
                <fmt:message key="page.register.login.button"/></a></h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
