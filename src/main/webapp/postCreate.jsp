<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="ru">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/jasny-bootstrap.min.css" rel="stylesheet">
  <title>New Post</title>
</head>
<body>

<div class="container-fluid">
  <nav class="navbar navbar-expand-md border-bottom">
    <div class="container">
      <i class="fa-solid fa-camera-retro"></i>
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
              <i class="fa-solid fa-house-user"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/post/create/${username}">
              <i class="fa-solid fa-camera"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/edit/${username}">
              <i class="fa-solid fa-id-card"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">
              <i class="fa-solid fa-right-from-bracket"></i></a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>


<div class="container">
  <form action="${pageContext.request.contextPath}/post/create/${username}" method="post"
        enctype="multipart/form-data">

    <div class="row p-3 justify-content-center">
      <div class="col-sm-10 col-lg-8 col-xl-6">
        <div class="fileinput fileinput-new w-100" data-provides="fileinput">
          <div class="card">
            <div class="card-body">
              <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
            </div>
            <div class="card-footer">
            <span class="btn btn-outline-secondary btn-file">
              <span class="fileinput-new">Добавить фото</span>
              <span class="fileinput-exists">Заменить</span>
              <input type="file" name="file-1">
            </span>
              <a href="#" class="btn btn-outline-secondary fileinput-exists"
                 data-dismiss="fileinput">Удалить</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row p-3 justify-content-center">
      <div class="col-sm-10 col-lg-8 col-xl-6">
        <div class="fileinput fileinput-new w-100" data-provides="fileinput">
          <div class="card">
            <div class="card-body">
              <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
            </div>
            <div class="card-footer">
            <span class="btn btn-outline-secondary btn-file">
              <span class="fileinput-new">Добавить фото</span>
              <span class="fileinput-exists">Заменить</span>
              <input type="file" name="file-2">
            </span>
              <a href="#" class="btn btn-outline-secondary fileinput-exists"
                 data-dismiss="fileinput">Удалить</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row p-3 justify-content-center">
      <div class="col-sm-10 col-lg-8 col-xl-6">
        <div class="fileinput fileinput-new w-100" data-provides="fileinput">
          <div class="card">
            <div class="card-body">
              <div class="fileinput-preview img-thumbnail" data-trigger="fileinput"></div>
            </div>
            <div class="card-footer">
            <span class="btn btn-outline-secondary btn-file">
              <span class="fileinput-new">Добавить фото</span>
              <span class="fileinput-exists">Заменить</span>
              <input type="file" name="file-3">
            </span>
              <a href="#" class="btn btn-outline-secondary fileinput-exists"
                 data-dismiss="fileinput">Удалить</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row p-3 justify-content-center">
      <div class="col-sm-10 col-lg-8 col-xl-6">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Описание</h5>
            <label for="exampleFormControlTextarea1" class="form-label"></label>
            <textarea class="form-control" name="description"
                      id="exampleFormControlTextarea1" rows="10" maxlength="1000"></textarea>
          </div>
          <div class="card-footer">
            <input class="btn btn-outline-secondary" type="submit" value="Опубликовать">
          </div>
        </div>
      </div>
    </div>
  </form>
</div>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="${contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/js/jasny-bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/f297933945.js" crossorigin="anonymous"></script>
</body>
</html>
