<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title><spring:message code="outh.login"/> </title>

    <link href="/assets/css/logged.css" rel="stylesheet">
    <link href="/assets/css/master.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <div class="lang">
        <a href="?locale=ru">ru</a>
        <a href="?locale=en">en</a>
    </div>

    <div class="login">
        <h1>
            <spring:message code="outh.nameForm"/>
        </h1>

        <form modelAttribute="userForm" method="POST"  action="/login">
            <input name="username" type="text" class="form-control" placeholder="<spring:message code="outh.login"/>"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="<spring:message code="outh.password"/>"/>
            <span>${error}</span>
            <button type="submit" class="btn btn-primary btn-block btn-large"><spring:message code="outh.enter"/></button>
        </form>
        <br>
    </div>
</div>
<!-- /container -->
</body>
</html>