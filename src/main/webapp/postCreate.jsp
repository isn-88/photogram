<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/jasny-bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/icons.all.min.css" rel="stylesheet">
  <link href="${contextPath}/css/main.css" rel="stylesheet">
  <title>New Post</title>
</head>
<body>

<section>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md border-bottom bg-body-tertiary fixed-top">
      <div class="container">
        <i class="fa-solid fa-camera-retro fa-lg"></i>
        <a class="navbar-brand text-uppercase p-1" href="#">Photogram</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Переключатель навигации">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          </ul>
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/home/${username}">
                <i class="fa-solid fa-house-user fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/post/create/${username}">
                <i class="fa-solid fa-camera fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/edit/${username}">
                <i class="fa-solid fa-id-card fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket fa-lg"></i></a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</section>

<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">
    <form action="${pageContext.request.contextPath}/post/create/${username}" method="post"
          enctype="multipart/form-data" id="formPostCreate" onsubmit="onSubmit()">

      <div class="row">
        <div class="col-lg-6">
          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="fileinput fileinput-new w-100" data-provides="fileinput">
                <div class="card">
                  <div class="card-body">
                    <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
                  </div>
                  <div class="card-footer">
                    <span class="btn btn-outline-secondary btn-file">
                      <span class="fileinput-new">
                        <i class="fa-regular fa-image"></i> Добавить фото</span>
                      <span class="fileinput-exists">
                        <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                      <input type="file" name="image-1">
                    </span>
                    <a href="#" class="btn btn-outline-danger fileinput-exists"
                       data-dismiss="fileinput"><i class="fa-regular fa-trash-can"></i> Удалить</a>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="fileinput fileinput-new w-100" data-provides="fileinput">
                <div class="card">
                  <div class="card-body">
                    <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
                  </div>
                  <div class="card-footer">
                    <span class="btn btn-outline-secondary btn-file">
                      <span class="fileinput-new">
                        <i class="fa-regular fa-image"></i> Добавить фото</span>
                      <span class="fileinput-exists">
                        <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                      <input type="file" name="image-2">
                    </span>
                    <a href="#" class="btn btn-outline-danger fileinput-exists"
                       data-dismiss="fileinput"><i class="fa-regular fa-trash-can"></i> Удалить</a>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="fileinput fileinput-new w-100" data-provides="fileinput">
                <div class="card">
                  <div class="card-body">
                    <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
                  </div>
                  <div class="card-footer">
                    <span class="btn btn-outline-secondary btn-file">
                      <span class="fileinput-new">
                        <i class="fa-regular fa-image"></i> Добавить фото</span>
                      <span class="fileinput-exists">
                        <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                      <input type="file" name="image-3">
                    </span>
                    <a href="#" class="btn btn-outline-danger fileinput-exists"
                       data-dismiss="fileinput"><i class="fa-regular fa-trash-can"></i> Удалить</a>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="fileinput fileinput-new w-100" data-provides="fileinput">
                <div class="card">
                  <div class="card-body">
                    <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
                  </div>
                  <div class="card-footer">
                    <span class="btn btn-outline-secondary btn-file">
                      <span class="fileinput-new">
                        <i class="fa-regular fa-image"></i> Добавить фото</span>
                      <span class="fileinput-exists">
                        <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                      <input type="file" name="image-4">
                    </span>
                    <a href="#" class="btn btn-outline-danger fileinput-exists"
                       data-dismiss="fileinput"><i class="fa-regular fa-trash-can"></i> Удалить</a>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="fileinput fileinput-new w-100" data-provides="fileinput">
                <div class="card">
                  <div class="card-body">
                    <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
                  </div>
                  <div class="card-footer">
                    <span class="btn btn-outline-secondary btn-file">
                      <span class="fileinput-new">
                        <i class="fa-regular fa-image"></i> Добавить фото</span>
                      <span class="fileinput-exists">
                        <i class="fa-solid fa-arrows-rotate"></i> Заменить</span>
                      <input type="file" name="image-5">
                    </span>
                    <a href="#" class="btn btn-outline-danger fileinput-exists"
                       data-dismiss="fileinput"><i class="fa-regular fa-trash-can"></i> Удалить</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>


        <div class="col-lg-6 h-100 sticky-top" style="top: 80px;">
          <div class="row p-3 justify-content-center">
            <div class="col">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title text-center">Описание</h5>
                  <label for="exampleFormControlTextarea1" class="form-label"></label>
                  <textarea class="form-control" name="description"
                            id="exampleFormControlTextarea1" rows="10"
                            maxlength="1000"></textarea>
                </div>

                <div class="form-check form-switch p-5 fs-5 align-self-center">
                  <input class="form-check-input" type="checkbox" role="switch"
                         id="flexSwitchCheckChecked" name="isActive" value="true" checked>
                  <label class="form-check-label"
                         for="flexSwitchCheckChecked">Опубликовать</label>
                </div>
                <div class="card-footer">
                  <div
                      class="d-grid gap-2 p-3 d-flex justify-content-between justify-content-md-around">
                    <a href="${pageContext.request.contextPath}/home/${username}"
                       class="btn btn-outline-secondary" type="button">
                      <i class="fa-solid fa-arrow-left"></i> Назад</a>
                    <button type="submit" class="btn btn-outline-success" id="buttonPostCreate">
                      <i class="fa-solid fa-check"></i> Создать
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</section>


<script>
  function onSubmit() {
    $('#buttonPostCreate').attr('disabled', true).text('Обработка...');
  }

</script>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/js/jasny-bootstrap.min.js"></script>
</body>
</html>
