<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>



<section>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md border-bottom bg-body-tertiary fixed-top">
      <div class="container">
        <i class="fa-solid fa-camera-retro fa-lg"></i>
        <a class="navbar-brand fst-italic fs-4 p-1"
           href="${pageContext.request.contextPath}/moderator">Photogram</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Переключатель навигации">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <span class="fst-italic text-center fs-4 me-5">${sessionScope.account.username()}
              </span>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="${pageContext.request.contextPath}/moderator">
                <i class="fa-solid fa-house-user fa-lg"></i></a>
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
