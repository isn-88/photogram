<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


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
              <a class="nav-link"
                 href="${pageContext.request.contextPath}/home">
                <i class="fa-solid fa-house-user fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="${pageContext.request.contextPath}/post/create">
                <i class="fa-solid fa-camera fa-lg"></i></a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="${pageContext.request.contextPath}/edit">
                <i class="fa-solid fa-id-card fa-lg"></i></a>
            </li>
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
