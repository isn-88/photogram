<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="locale.jsp" %>


<section>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md border-bottom bg-body-tertiary fixed-top">
      <div class="container">
        <i class="fa-solid fa-camera-retro fa-lg"></i>
        <a class="navbar-brand fst-italic fs-4 p-1"
           href="${pageContext.request.contextPath}/home">Photogram</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Переключатель навигации">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <form class="d-flex" role="search" method="post"
                    action="${pageContext.request.contextPath}/search">
                <div class="input-group input-group-sm">
                  <span class="input-group-text" id="inputGroup-sizing-sm">@</span>
                  <input type="search" class="form-control" name="search"
                         aria-describedby="inputGroup-sizing-sm"
                         placeholder="<fmt:message key="nav.user.search.placeholder"/>"
                         aria-label="search">
                  <button class="btn btn-outline-secondary" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
              </form>
            </li>
          </ul>
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item mt-1 me-lg-4">
              <%@ include file="locale-menu.jsp" %>
            </li>
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
