<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <title>Account</title>
</head>

<body>
<nav class="navbar bg-primary" data-bs-theme="dark">
  <div class="container-fluid">
    <div class="container">
      <span class="navbar-brand mb-3 p-3 h1 text-uppercase">Photogram</span>
    </div>
  </div>
</nav>
<section class="main-content">
  <div class="container">
    <div class="row p-5">
      <div class="col-12">
        <div class="card">
          <div class="row g-0">
            <div class="col-12 col-md-4 col-xl-3">
              <div class="card-header">
                <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist"
                     aria-orientation="vertical">
                  <button class="nav-link active" id="v-pills-overview-tab" data-bs-toggle="pill"
                          data-bs-target="#v-pills-overview" type="button" role="tab"
                          aria-controls="v-pills-overview" aria-selected="true">
                    <h5>Обзор</h5>
                  </button>
                  <button class="nav-link" id="v-pills-account-tab" data-bs-toggle="pill"
                          data-bs-target="#v-pills-account" type="button" role="tab"
                          aria-controls="v-pills-account" aria-selected="false">
                    <h5>Аккаунт</h5>
                  </button>
                  <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill"
                          data-bs-target="#v-pills-profile" type="button" role="tab"
                          aria-controls="v-pills-profile" aria-selected="false">
                    <h5>Профиль</h5>
                  </button>
                  <button class="nav-link" id="v-pills-password-tab" data-bs-toggle="pill"
                          data-bs-target="#v-pills-password" type="button" role="tab"
                          aria-controls="v-pills-password" aria-selected="false">
                    <h5>Пароль</h5>
                  </button>

<%--                  <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill"--%>
<%--                          data-bs-target="#v-pills-settings" type="button" role="tab"--%>
<%--                          aria-controls="v-pills-settings" aria-selected="false">--%>
<%--                    <h5>Settings</h5>--%>
<%--                  </button>--%>

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
                      <form action="/account/edit/${account.username}" method="post">
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
                                     value="${account.phone}" aria-describedby="basic-addon-plus">
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
                                     value="${account.email}" placeholder="Адрес электронной почты"
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
                                     value="${account.username}"
                                     aria-describedby="basic-addon-email">
                            </div>
                          </div>
                        </div>

                        <div class="row p-5">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-primary" type="submit" value="Сохранить">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                  <%--  Вкладка Профиль  --%>
                  <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                       aria-labelledby="v-pills-profile-tab">
                    <div class="container">
                      <form action="/profile/edit/${account.username}" method="post">
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
                                     value="${account.profile.fullName}" placeholder="Имя и фамилия"
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
                              <input type="text" class="form-control" name="birth_date" id="editBirthDate"
                                     value="${account.profile.birthdate}" placeholder="Дата рождения"
                                     aria-describedby="birthDateHelp">
                            </div>
                          </div>


                        </div>

                        <div class="row p-5">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-primary" type="submit" value="Сохранить">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                  <%--  Вкладка Пароль  --%>
                  <div class="tab-pane fade" id="v-pills-password" role="tabpanel"
                       aria-labelledby="v-pills-password-tab">
                    <div class="container">
                      <form action="/password/${account.username}" method="post">
                        <%--  Текущий пароль  --%>
                        <div class="row p-3">
                          <div class="col-lg-3 text-start text-lg-end">
                            <label for="inputPasswordOld" class="col-form-label">Текущий
                              пароль</label>
                          </div>
                          <div class="col">
                            <div class="input-group">
                              <input type="password" class="form-control" id="inputPasswordOld"
                                     value="" placeholder="" name="old_password"
                                     aria-describedby="passwordHelpOld">
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
                                     aria-describedby="passwordHelpNew">
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
                                     aria-describedby="passwordHelpCheck">
                            </div>
                          </div>
                        </div>

                        <div class="row p-5 align-items-start">
                          <div class="col-lg-3">
                          </div>
                          <div class="col">
                            <input class="btn btn-primary" type="submit" value="Изменить">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>


<%--                  <div class="tab-pane fade" id="v-pills-settings" role="tabpanel"--%>
<%--                       aria-labelledby="v-pills-settings-tab">--%>
<%--                  </div>--%>


                </div>
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