<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<form action="${pageContext.request.contextPath}/locale" method="post">

  <div class="dropdown text-end">
    <a class="dropdown-toggle btn btn-sm btn-outline-secondary" href="#" id="Dropdown" role="button"
       data-bs-toggle="dropdown" aria-expanded="false">${locale.substring(0, 2)}</a>

    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="Dropdown">
      <li>
        <button class="dropdown-item" type="submit" name="lang"
                value="en_US">English</button>
      </li>
      <li>
        <hr class="dropdown-divider"/>
      </li>
      <li>
        <button class="dropdown-item" type="submit" name="lang"
                value="ru_RU">Русский</button>
      </li>
    </ul>
  </div>
</form>

