<%@ page import="su.itpro.photogram.model.enums.Status" %>
<%@ page import="su.itpro.photogram.model.enums.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <title>Administrator</title>
</head>
<body>

<section>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md border-bottom bg-body-tertiary fixed-top">
      <div class="container">
        <i class="fa-solid fa-camera-retro fa-lg"></i>
        <a class="navbar-brand fst-italic fs-4 p-1"
           href="${pageContext.request.contextPath}/admin">Photogram</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Переключатель навигации">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">


          <ul class="nav nav-underline" id="myTab" role="tablist">

            <c:if test="${not empty requestScope.tabCreate or empty requestScope.tabFind}">
              <c:set var="showCreate" value="active"/>
            </c:if>

            <li class="nav-item" role="presentation">
              <button class="nav-link ${showCreate} text-dark" id="home-tab" data-bs-toggle="tab"
                      data-bs-target="#home-tab-pane" aria-controls="home-tab-pane"
                      type="button" aria-selected="true"
                      role="tab">Создать аккаунт
              </button>
            </li>

            <c:if test="${not empty requestScope.tabFind}">
              <c:set var="showFind" value="active"/>
            </c:if>

            <li class="nav-item" role="presentation">
              <button class="nav-link ${showFind} text-dark" id="profile-tab" data-bs-toggle="tab"
                      data-bs-target="#profile-tab-pane" aria-controls="profile-tab-pane"
                      type="button" aria-selected="false"
                      role="tab">Найти аккаунт
              </button>
            </li>
          </ul>

          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link"
                 href="${pageContext.request.contextPath}/admin">
                <i class="fa-solid fa-house-user fa-lg"></i></a>
            </li>
            <%--            <li class="nav-item">--%>
            <%--              <a class="nav-link"--%>
            <%--                 href="${pageContext.request.contextPath}/admin/edit">--%>
            <%--                <i class="fa-solid fa-id-card fa-lg"></i></a>--%>
            <%--            </li>--%>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket fa-lg"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</section>

<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">
    <div class="row mt-4">
      <div class="col">
        <div class="tab-content" id="myTabContent">

          <c:if test="${not empty requestScope.tabCreate or empty requestScope.tabFind}">
            <c:set var="showCreate" value="show active"/>
          </c:if>

          <div class="tab-pane fade ${showCreate}" id="home-tab-pane" role="tabpanel"
               aria-labelledby="home-tab" tabindex="0">
            <div class="row justify-content-center">
              <div class="col-sm-10 col-md-8 col-xl-6">
                <div class="card shadow">
                  <div class="card-header text-center">
                    <span class="fs-4">Регистрация нового аккаунта</span>
                  </div>
                  <div class="card-body">
                    <div class="card-text">
                      <form class="row gy-4"
                            action="${pageContext.request.contextPath}/admin/create"
                            method="post">
                        <div class="col-12">
                          <div class="form-text">Укажите номер телефона или адрес электронной
                            почты
                          </div>
                          <div class="input-group">
                            <span class="input-group-text" id="basic-addon-plus">+</span>
                            <input type="text" class="form-control" name="phone"
                                   value="${requestScope.phone}" placeholder="Номер телефона"
                                   aria-describedby="basic-addon-plus" aria-label="">
                          </div>
                        </div>
                        <div class="col-12">
                          <input class="form-control" type="text" name="email"
                                 value="${requestScope.email}" placeholder="Адрес электронной почты"
                                 aria-label="">
                        </div>
                        <div class="col-12">
                          <div class="input-group">
                            <span class="input-group-text" id="basic-addon-email">@</span>
                            <input type="text" class="form-control" name="username"
                                   value="${requestScope.username}" placeholder="Имя пользователя"
                                   aria-describedby="basic-addon-email" aria-label="" required>
                          </div>
                        </div>
                        <div class="col-12">
                          <input type="password" class="form-control" id="inputPassword"
                                 name="password" placeholder="Пароль" aria-label="Пароль" required>
                          <div id="passwordHelpBlock" class="form-text">
                            Ваш пароль должен состоять из 8-20 символов, содержать буквы и цифры
                            и не должен содержать пробелов, специальных символов или эмодзи.
                          </div>
                        </div>
                        <div class="col-12">
                          <%--  Тип аккаунта  --%>
                          <div class="row">
                            <div class="col-12 col-sm-5 text-start text-sm-end">
                              <label for="flexRadioUser" class="form-label">Тип аккаунта</label>
                            </div>

                            <div class="col">
                              <div class="row row-cols-1">
                                <div class="col">

                                  <%--  Пользовательский --%>
                                  <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="account_type"
                                           id="flexRadioUser" value="USER"
                                    <c:if
                                        test="${requestScope.role eq Role.USER or empty requestScope.role}">
                                      <c:out value="checked"/>
                                    </c:if> >
                                    <label class="form-check-label"
                                           for="flexRadioUser">Пользователь</label>
                                  </div>
                                </div>
                                <div class="col">
                                  <%--  Модератор  --%>
                                  <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="account_type"
                                           id="flexRadioModerator" value="MODERATOR"
                                    <c:if test="${requestScope.role eq Role.MODERATOR}">
                                      <c:out value="checked"/>
                                    </c:if> >
                                    <label class="form-check-label"
                                           for="flexRadioModerator">Модератор</label>
                                  </div>
                                </div>
                                <div class="col">
                                  <%--  Администратор  --%>
                                  <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="account_type"
                                           id="flexRadioAdmin" value="ADMIN"
                                    <c:if test="${requestScope.role eq Role.ADMIN}">
                                      <c:out value="checked"/>
                                    </c:if> >
                                    <label class="form-check-label"
                                           for="flexRadioAdmin">Администратор</label>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>

                        <c:if test="${not empty requestScope.errors}">
                          <div class="col-12">
                            <span style="color: darkred">Ошибки при регистрации:</span>
                            <ul>
                              <c:forEach var="error" items="${requestScope.errors}">
                                <li style="color: darkred">${error.message()}</li>
                              </c:forEach>
                            </ul>
                          </div>
                        </c:if>

                        <div class="d-grid">
                          <input class="btn btn-outline-secondary" type="submit"
                                 value="Зарегистрировать">
                        </div>

                        <c:if test="${empty requestScope.result or requestScope.result eq 'false'}">
                          <c:set var="hiddenValue" value="hidden"/>
                        </c:if>
                        <div class="text-center" ${hiddenValue}>
                          <span class="fs-5 text-success">Успешно!</span>
                        </div>

                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <c:if test="${not empty requestScope.tabFind}">
            <c:set var="showFind" value="show active"/>
          </c:if>

          <div class="tab-pane fade ${showFind}" id="profile-tab-pane" role="tabpanel"
               aria-labelledby="profile-tab" tabindex="0">

            <div class="row justify-content-center">
              <div class="col-12">
                <div class="card shadow">
                  <div class="card-header text-center">
                    <span class="fs-4">Поиск аккаунта</span>
                  </div>

                  <div class="card-body">

                    <div class="table-responsive">
                      <table class="table">
                        <thead>
                        <tr>
                          <th scope="col" hidden></th>
                          <th scope="col">Идентификатор</th>
                          <th scope="col">Username</th>
                          <th scope="col">Телефон</th>
                          <th scope="col">Email</th>
                          <th scope="col">Роль</th>
                          <th scope="col">Статус</th>
                          <th scope="col">Действие</th>
                        </tr>
                        </thead>

                        <tbody>

                        <tr>
                          <td hidden>
                            <form action="${pageContext.request.contextPath}/admin/find"
                                  method="post" id="formAdminFind">
                            </form>
                          </td>

                          <td>
<%--                            <label>--%>
<%--                              <input type="text" name="find_id" value="" class="form-control-sm"--%>
<%--                                     placeholder="id" form="formAdminFind" readonly/>--%>
<%--                            </label>--%>
                          </td>
                          <td>
                            <label>
                              <input type="text" name="find_username" class="form-control-sm"
                                     value="${requestScope.findUsername}"
                                     placeholder="username" form="formAdminFind"/>
                            </label>
                          </td>
                          <td>
                            <label>
                              <input type="text" name="find_phone" class="form-control-sm"
                                     value="${requestScope.findPhone}"
                                     placeholder="phone" form="formAdminFind"/>
                            </label>
                          </td>
                          <td>
                            <label>
                              <input type="text" name="find_email" class="form-control-sm"
                                     value="${requestScope.findEmail}"
                                     placeholder="email" form="formAdminFind"/>
                            </label>
                          </td>
                          <td></td>
                          <td>
                            <div class="form-check form-switch">
                              <input class="form-check-input" type="checkbox" role="switch"
                                     id="flexSwitchCheckDefault" name="only_blocked" value="true"
                              <c:if test="${requestScope.onlyBlocked}">
                                <c:out value="checked"/>
                              </c:if> form="formAdminFind">
                              <label class="form-check-label"
                                     for="flexSwitchCheckDefault">Заблок.</label>
                            </div>
                          </td>

                          <td>
                            <div class="col text-center">
                              <button class="btn btn-outline-secondary btn-sm"
                                      type="submit" form="formAdminFind">Найти
                              </button>
                            </div>
                          </td>
                        </tr>


                        <c:forEach var="accountFind" items="${requestScope.accountFindList}">
                          <tr>
                            <td hidden>
                              <form action="${pageContext.request.contextPath}/admin/status"
                                    id="formFor${accountFind.id()}" method="post">
                              </form>
                            </td>
                            <td class="font-monospace"><c:out value="${accountFind.id()}"/></td>
                            <td><c:out value="${accountFind.username()}"/></td>
                            <td><c:out value="${accountFind.phone()}"/></td>
                            <td><c:out value="${accountFind.email()}"/></td>
                            <td><c:out value="${accountFind.role().name()}"/></td>

                            <td>
                              <c:if test="${accountFind.status() eq 'ACTIVE'}"><c:out
                                  value="Активный"/></c:if>
                              <c:if test="${accountFind.status() eq 'BLOCKED'}"><c:out
                                  value="Заблокирован"/></c:if>
                              <c:if test="${accountFind.status() eq 'DELETED'}"><c:out
                                  value="Удалён"/></c:if>
                            </td>
                            <td>
                              <div class="col text-center">


                                <c:if test="${accountFind.status() eq Status.ACTIVE}">
                                  <c:set var="setStatus" value="${Status.BLOCKED}"/>
                                  <c:set var="textButton" value="Заблокировать"/>
                                  <c:set var="colorButton" value="btn-outline-danger"/>
                                </c:if>

                                <c:if test="${accountFind.status() eq Status.BLOCKED}">
                                  <c:set var="setStatus" value="${Status.ACTIVE}"/>
                                  <c:set var="textButton" value="Разблокировать"/>
                                  <c:set var="colorButton" value="btn-outline-success"/>
                                </c:if>

                                <label>
                                  <input name="updated_account" value="${accountFind.id()}"
                                         id="inputAccount${accountFind.id()}"
                                         form="formFor${accountFind.id()}" hidden/>
                                </label>
                                <label>
                                  <input name="updated_status" value="${setStatus}"
                                         id="inputStatus${accountFind.id()}"
                                         form="formFor${accountFind.id()}" hidden/>
                                </label>

                                <button type="submit" form="formFor${accountFind.id()}"
                                        id="buttonFor${accountFind.id()}"
                                        class="btn ${colorButton} btn-sm">${textButton}
                                </button>
                              </div>
                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
