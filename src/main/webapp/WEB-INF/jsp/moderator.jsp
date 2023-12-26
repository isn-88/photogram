<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="su.itpro.photogram.model.enums.ComplainStatus" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/icons.all.min.css" rel="stylesheet">
  <title>Moderator</title>
</head>
<body>


<%@ include file="navigation-moder.jsp" %>


<section style="padding-top: 80px; padding-bottom: 10px;">
  <div class="container">

    <div class="row justify-content-center mt-3">
      <div class="col-12">
        <div class="card shadow">
          <div class="card-header text-center">
            <span class="fs-4">Поиск жалоб на публикации</span>
          </div>

          <div class="card-body">
            <div class="table-responsive">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col" hidden></th>
                  <th scope="col">Пользователь</th>
                  <th scope="col">Публикация</th>
                  <th scope="col">Статус</th>
                  <th scope="col">Сообщение</th>
                  <th scope="col">Время создания</th>
                  <th scope="col">Время обработки</th>
                  <th scope="col">Действие</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                  <td hidden>
                    <form action="${pageContext.request.contextPath}/moderator/find"
                          method="post" id="formModeratorFind">
                    </form>
                  </td>
                  <td></td>
                  <td></td>
                  <td>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="isOpened"
                             value="${ComplainStatus.OPEN}"
                             id="isOpened" form="formModeratorFind">
                      <label class="form-check-label"
                             for="isOpened">Открыто</label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="isClosed"
                             value="${ComplainStatus.CLOSE}"
                             id="isClosed" form="formModeratorFind">
                      <label class="form-check-label"
                             for="isClosed">Закрыто</label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="isApproved"
                             value="${ComplainStatus.APPROVED}"
                             id="isApproved" form="formModeratorFind">
                      <label class="form-check-label"
                             for="isApproved">Обработано</label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="isRejected"
                             value="${ComplainStatus.REJECTED}"
                             id="isRejected" form="formModeratorFind">
                      <label class="form-check-label"
                             for="isRejected">Отклонено</label>
                    </div>
                  </td>
                  <td>
                  </td>
                  <td></td>
                  <td>
                  </td>

                  <td>
                    <div class="col text-center">
                      <button class="btn btn-outline-secondary btn-sm"
                              type="submit" form="formModeratorFind">Найти</button>
                    </div>
                  </td>

                </tr>
                <c:forEach var="complaint" items="${requestScope.complaintDtoList}">
                  <tr>
                    <td hidden="">
                      <form action="${pageContext.request.contextPath}/moderator/action"
                            method="post"
                            id="formAction${complaint.accountId()}&${complaint.postId()}">
                      </form>
                    </td>
                    <td>
                      <span class="fst-italic">${complaint.username()}</span>
                    </td>
                    <td>
                      <a href="${pageContext.request.contextPath}/post/${complaint.postId()}">
                        Post</a>
                    </td>
                    <td><c:out value="${complaint.status().name()}"/></td>
                    <td><c:out value="${complaint.message()}"/></td>
                    <td><c:out value="${complaint.createTime()}"/></td>
                    <td><c:out value="${complaint.closeTime()}"/></td>

                    <td>
                      <div class="col text-center">
                        <div class="form-check text-start">
                          <input class="form-check-input" type="radio"
                                 id="rejectedRadio${complaint.accountId()}&${complaint.postId()}"
                                 name="complaintResult" value="${ComplainStatus.REJECTED}"
                                 form="formAction${complaint.accountId()}&${complaint.postId()}"
                                 checked>
                          <label class="form-check-label"
                                 for="rejectedRadio${complaint.accountId()}&${complaint.postId()}"
                          >Отклонить жалобу</label>
                        </div>
                        <div class="form-check text-start">
                          <input class="form-check-input" type="radio"
                                 id="approvedRadio${complaint.accountId()}&${complaint.postId()}"
                                 name="complaintResult" value="${ComplainStatus.APPROVED}"
                                 form="formAction${complaint.accountId()}&${complaint.postId()}">
                          <label class="form-check-label"
                                 for="approvedRadio${complaint.accountId()}&${complaint.postId()}"
                          >Заблокировать публикацию</label>
                        </div>

                        <label>
                          <input name="accountId" value="${complaint.accountId()}"
                                 id="inputAccount${complaint.accountId()}&${complaint.postId()}"
                                 form="formAction${complaint.accountId()}&${complaint.postId()}"
                                 hidden/>
                        </label>

                        <label>
                          <input name="postId" value="${complaint.postId()}"
                                 id="inputPost${complaint.accountId()}&${complaint.postId()}"
                                 form="formAction${complaint.accountId()}&${complaint.postId()}"
                                 hidden/>
                        </label>

                        <button type="submit"
                                form="formAction${complaint.accountId()}&${complaint.postId()}"
                                class="btn btn-outline-danger btn-sm">Выполнить
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
</section>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
