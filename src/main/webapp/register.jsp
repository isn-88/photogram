<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <title>Register</title>
</head>

<body>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-sm-10 col-md-8 col-xl-6 p-5">
      <div class="card">
        <div class="card-header text-center">
          <h2 class="text-uppercase">Photogram</h2>
          <span>Зарегистрируйтесь для просмотра фотографий ваших друзей</span>
        </div>
        <div class="card-body">
          <div class="card-text">
            <form class="row gy-4" action="${pageContext.request.contextPath}/register"
                  method="post">

              <div class="col-12">
                <div class="form-text">Укажите номер телефона или адрес электронной почты</div>
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon-plus">+</span>
                  <input type="text" class="form-control" name="phone"
                         placeholder="Номер телефона" aria-label=""
                         aria-describedby="basic-addon-plus">
                </div>
              </div>
              <div class="col-12">
                <input class="form-control" type="text" name="email"
                       placeholder="Адрес электронной почты" aria-label="">
              </div>
              <div class="col-12">
                <input class="form-control" type="text" name="full_name"
                       placeholder="Имя и фамилия" aria-label="">
              </div>
              <div class="col-12">
                <div class="input-group">
                  <span class="input-group-text" id="basic-addon-email">@</span>
                  <input type="text" class="form-control" name="username"
                         placeholder="Имя пользователя" aria-label=""
                         aria-describedby="basic-addon-email">
                </div>
              </div>
              <div class="col-12">
                <input type="password" class="form-control" id="inputPassword"
                       name="password" placeholder="Пароль" aria-label="Пароль">
                <div id="passwordHelpBlock" class="form-text">
                  Ваш пароль должен состоять из 8-20 символов, содержать буквы и цифры
                  и не должен содержать пробелов, специальных символов или эмодзи.
                </div>
              </div>
              <div class="d-grid">
                <input class="btn btn-primary" type="submit" value="Зарегистрировать">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col col-sm-10 col-md-8 col-xl-6 p-5">
      <div class="card">
        <div class="card-body">
          <div class="card-text">

            <h5>Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></h5>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>

  <script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
