<%@ page import="su.itpro.photogram.model.enums.Gender" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/jasny-bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
  <title>Account</title>
</head>

<body>

<%@ include file="navigation.jsp" %>

<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">
    <div class="row p-3">
      <div class="col-12">
        <div class="card">
          <div class="row g-0">
            <div class="col-12 col-md-4 col-xl-3">
              <div class="card-header h-100 border-end">
                <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist"
                     aria-orientation="vertical">
                  <button class="nav-link btn-outline-secondary active fs-5"
                          id="v-pills-overview-tab"
                          data-bs-toggle="pill"
                          data-bs-target="#v-pills-overview" type="button" role="tab"
                          aria-controls="v-pills-overview" aria-selected="true">Обзор
                  </button>

                  <button class="nav-link btn-outline-secondary fs-5" id="v-pills-account-tab"
                          data-bs-toggle="pill"
                          data-bs-target="#v-pills-account" type="button" role="tab"
                          aria-controls="v-pills-account" aria-selected="false">Аккаунт
                  </button>
                  <button class="nav-link btn-outline-secondary fs-5" id="v-pills-profile-tab"
                          data-bs-toggle="pill"
                          data-bs-target="#v-pills-profile" type="button" role="tab"
                          aria-controls="v-pills-profile" aria-selected="false">Профиль
                  </button>
                  <button class="nav-link btn-outline-secondary fs-5" id="v-pills-icon-tab"
                          data-bs-toggle="pill"
                          data-bs-target="#v-pills-icon" type="button" role="tab"
                          aria-controls="v-pills-icon" aria-selected="false">Иконка
                  </button>

                  <button class="nav-link btn-outline-secondary fs-5" id="v-pills-password-tab"
                          data-bs-toggle="pill"
                          data-bs-target="#v-pills-password" type="button" role="tab"
                          aria-controls="v-pills-password" aria-selected="false">Пароль
                  </button>
                </div>
              </div>
            </div>

            <div class="col">
              <div class="card-body">
                <div class="tab-content" id="v-pills-tabContent">

                  <%--  Вкладка Обзор  --%>
                  <div class="tab-pane fade show active" id="v-pills-overview" role="tabpanel"
                       aria-labelledby="v-pills-overview-tab">
                    <div class="container">
                      <div class="row p-5">
                        <div class="col">
                          <h5>Краткая информация</h5>
                        </div>
                      </div>
                    </div>
                  </div>

                  <%--  Вкладка Аккаунт  --%>
                  <div class="tab-pane fade" id="v-pills-account" role="tabpanel"
                       aria-labelledby="v-pills-account-tab">
                    <div class="container">
                      <form action="${pageContext.request.contextPath}/account/edit" method="post">
                        <div class="row p-3">
                          <div class="col">
                            <h6>При необходимости можно изменить данные для входа в личный
                              кабинет</h6>
                          </div>
                        </div>
                        <%--  Телефон  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editPhone" class="form-label">Телефон</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <span class="input-group-text" id="basic-addon-plus">+</span>
                              <input type="text" class="form-control" name="phone"
                                     placeholder="Номер телефона" aria-label="" id="editPhone"
                                     value="${sessionScope.account.phone()}"
                                     aria-describedby="basic-addon-plus">
                            </div>
                          </div>
                        </div>
                        <%--  Email  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editEmail" class="form-label">Эл. адрес</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="email" class="form-control" name="email" id="editEmail"
                                     value="${sessionScope.account.email()}"
                                     placeholder="Адрес электронной почты"
                                     aria-describedby="emailHelp">
                            </div>
                          </div>
                        </div>
                        <%--  Username  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editUsername" class="form-label">Логин</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <span class="input-group-text" id="basic-addon-email">@</span>
                              <input type="text" class="form-control" name="username"
                                     placeholder="Имя пользователя" aria-label="" id="editUsername"
                                     value="${sessionScope.account.username()}"
                                     aria-describedby="basic-addon-email">
                            </div>
                          </div>
                        </div>

                        <div class="row p-5">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-outline-secondary" type="submit"
                                   value="Сохранить">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>

                  <%--  Вкладка Профиль  --%>
                  <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                       aria-labelledby="v-pills-profile-tab">
                    <div class="container">
                      <form action="${pageContext.request.contextPath}/profile/edit" method="post">
                        <div class="row p-3">
                          <div class="col">
                            <h6>При необходимости можно изменить личные данные</h6>
                          </div>
                        </div>

                        <%--  Имя и фамилия  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editName" class="form-label">Имя и фамилия</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="text" class="form-control" name="full_name" id="editName"
                                     value="${requestScope.profile.fullName()}"
                                     placeholder="Имя и фамилия"
                                     aria-describedby="fullNameHelp">
                            </div>
                          </div>
                        </div>

                        <%--  Дата рождения  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editBirthDate" class="form-label">Дата рождения</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="date" class="form-control" name="birthdate"
                                     id="editBirthDate"
                                     value="${requestScope.profile.birthdate()}"
                                     placeholder="Дата рождения"
                                     aria-describedby="birthDateHelp">
                            </div>
                          </div>
                        </div>

                        <%--  Пол  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="flexRadioUndefine" class="form-label">Пол</label>
                          </div>
                          <div class="col">
                            <%--  Мужской  --%>
                            <div class="form-check form-check-inline">
                              <input class="form-check-input" type="radio" name="gender"
                                     id="flexRadioMale" value="MALE"
                                     <c:if
                                         test="${requestScope.profile.gender() eq Gender.MALE}">checked</c:if>>
                              <label class="form-check-label" for="flexRadioMale">
                                Мужской
                              </label>
                            </div>
                            <%--  Женский  --%>
                            <div class="form-check form-check-inline">
                              <input class="form-check-input" type="radio" name="gender"
                                     id="flexRadioFemale" value="FEMALE"
                                     <c:if
                                         test="${requestScope.profile.gender() eq Gender.FEMALE}">checked</c:if>>
                              <label class="form-check-label" for="flexRadioFemale">
                                Женский
                              </label>
                            </div>
                            <%--  Не выбран  --%>
                            <div class="form-check form-check-inline" hidden>
                              <input class="form-check-input" type="radio" name="gender"
                                     id="flexRadioUndefine" value="UNDEFINE"
                                     <c:if
                                         test="${requestScope.profile.gender() eq Gender.UNDEFINE}">checked</c:if>>
                              <label class="form-check-label" for="flexRadioUndefine"></label>
                            </div>
                          </div>
                        </div>


                        <%--  Контакты  --%>
                        <%--                        <div class="row p-3">--%>
                        <%--                          <div class="col-lg-3 text-start text-lg-end">--%>
                        <%--                            <label for="editContactTitle1" class="form-label">Контакт 1</label>--%>
                        <%--                          </div>--%>
                        <%--                          <div class="col">--%>
                        <%--                            <div class="input-group">--%>
                        <%--                              <input type="text" class="form-control" name="full_name"--%>
                        <%--                                     id="editContactTitle1"--%>
                        <%--                                     value="" placeholder="Название"--%>
                        <%--                                     aria-describedby="fullNameHelp">--%>
                        <%--                            </div>--%>
                        <%--                          </div>--%>
                        <%--                          <div class="col">--%>
                        <%--                            <div class="input-group">--%>
                        <%--                              <input type="text" class="form-control" name="full_name"--%>
                        <%--                                     id="editContactValue1"--%>
                        <%--                                     value="" placeholder="Контакт"--%>
                        <%--                                     aria-describedby="fullNameHelp">--%>
                        <%--                            </div>--%>
                        <%--                          </div>--%>
                        <%--                        </div>--%>

                        <%--  Обо мне  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="editAboutMe" class="form-label">Обо мне</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <textarea class="form-control" name="about_me"
                                        id="editAboutMe" rows="10"
                                        maxlength="1000">${requestScope.profile.aboutMe()}</textarea>
                            </div>
                          </div>
                        </div>


                        <div class="row p-5">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-outline-secondary" type="submit"
                                   value="Сохранить">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>


                  <%--  Вкладка Иконка  --%>
                  <div class="tab-pane fade" id="v-pills-icon" role="tabpanel"
                       aria-labelledby="v-pills-icon-tab">
                    <div class="container">

                      <div class="row row-cols-1 row-cols-sm-2 g-3 m-auto">
                        <div class="col">
                          <div class="row row-cols-1">

                            <div class="col mb-2 text-center">
                              <h5>Текущее фото</h5>
                            </div>

                            <div class="col">
                              <div class="mb-3 d-flex flex-column align-items-center">
                                <img src="data:image/jpg;base64,${requestScope.icon.iconBase64()}"
                                     alt="profile icon"
                                     class="img-fluid rounded-circle mb-2"
                                     onError="this.onerror=null;this.src='${pageContext.request.contextPath}/img/default-profile-icon.jpg';"
                                     style="width: 180px; height: 180px"/>
                              </div>
                            </div>

                            <div class="col">
                              <form action="${pageContext.request.contextPath}/icon/delete"
                                    method="post">
                                <div class="d-flex justify-content-center">
                                  <button type="submit" class="btn btn-outline-danger"
                                          id="buttonIconDelete">
                                    <i class="fa-regular fa-trash-can"></i> Удалить
                                  </button>
                                </div>
                              </form>
                            </div>
                          </div>
                        </div>
                        <div class="col">
                          <form action="${pageContext.request.contextPath}/icon/edit" method="post"
                                enctype="multipart/form-data">
                            <div class="row row-cols-1">
                              <div class="col mb-2 text-center">
                                <h5>Изменить фото</h5>
                              </div>

                              <div class="form-group">
                                <div
                                    class="fileinput fileinput-new mb-2 d-flex flex-column justify-content-center"
                                    data-provides="fileinput">
                                  <div
                                      class="mb-4 fileinput-preview align-self-center img-fluid rounded-circle overflow-hidden"
                                      style="max-width: 180px; max-height: 180px; min-width: 180px; min-height: 180px;"
                                      data-trigger="fileinput"></div>
                                  <span
                                      class="btn btn-outline-secondary btn-file mb-3 w-auto align-self-center">
                                  <span class="fileinput-new">
                                    <i class="fa-regular fa-image"></i> Выбрать фото</span>
                                  <span class="fileinput-exists">
                                    <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                                  <input type="file" name="icon">
                                </span>
                                  <a href="#"
                                     class="btn btn-outline-danger mb-2 fileinput-exists w-auto align-self-center"
                                     data-dismiss="fileinput">
                                    <i class="fa-regular fa-trash-can"></i> Удалить</a>
                                </div>
                              </div>


                              <div class="col">
                                <div class="d-flex justify-content-center">
                                  <button type="submit" class="btn btn-outline-success"
                                          id="buttonIconSave">
                                    <i class="fa-solid fa-check"></i> Сохранить
                                  </button>
                                </div>
                              </div>
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>

                  <%--  Вкладка Пароль  --%>
                  <div class="tab-pane fade" id="v-pills-password" role="tabpanel"
                       aria-labelledby="v-pills-password-tab">
                    <div class="container">
                      <form action="${pageContext.request.contextPath}/password" method="post">
                        <%--  Текущий пароль  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="inputPasswordOld" class="col-form-label">
                              Текущий пароль</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="password" class="form-control" id="inputPasswordOld"
                                     value="" placeholder="" name="old_password"
                                     aria-describedby="passwordHelpOld" required>
                            </div>
                          </div>
                        </div>
                        <%--  Новый пароль  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="inputPasswordNew" class="col-form-label">Новый
                              пароль</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="password" class="form-control" id="inputPasswordNew"
                                     value="" placeholder="" name="new_password"
                                     aria-describedby="passwordHelpNew" required>
                            </div>
                          </div>
                        </div>
                        <%--  Повтор пароля  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="inputPasswordCheck" class="col-form-label">Повтор
                              пароля</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="password" class="form-control" id="inputPasswordCheck"
                                     value="" placeholder="" name="check_password"
                                     aria-describedby="passwordHelpCheck" required>
                            </div>
                          </div>
                        </div>

                        <div class="row p-5 align-items-start">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-outline-secondary" type="submit" value="Изменить">
                          </div>
                        </div>
                      </form>
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

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jasny-bootstrap.min.js"></script>
</body>
</html>