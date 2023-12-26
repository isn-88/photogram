<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>



<c:set var="default_locale"
       value="${requestScope.lang != null ? requestScope.lang : 'ru_RU'}"/>
<c:set var="locale" value="${sessionScope.lang != null ? sessionScope.lang : default_locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations"/>


