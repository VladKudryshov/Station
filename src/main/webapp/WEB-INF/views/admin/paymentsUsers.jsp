    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=utf-8" %>
<!doctype html>
<html lang="en">

<head>
    <title>Dashboard | Payments Users</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="/assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="/assets/css/main.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/assets/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="/"><img src="/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span> ${pageContext.request.userPrincipal.name}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
                            <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                            <form id="logoutForm" method="POST" action="/logout">
                            </form>
                            <li><a onclick="document.forms['logoutForm'].submit()"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <jsp:include page="menu.jsp"/>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">
        <div class="main-content">
            <div class="container-fluid">
                <div class="row">

                    <!-- TABLE HOVER -->
                    <div class="panel">
                        <div class="panel-heading">
                            <h3 class="panel-title"><spring:message code="page.listPayments"/></h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th><spring:message code="table.username"/></th>
                                    <th><spring:message code="table.titleService"/></th>
                                    <th><spring:message code="table.cost"/></th>
                                    <th><spring:message code="table.payDay"/></th>
                                    <th><spring:message code="table.paid"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="payment" items="${listPayments}">
                                    <tr>
                                        <td>${payment.id}</td>
                                        <td><a href="/user/${payment.user.id}/info">${payment.user.username}</a></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${payment.contract!=null}">
                                                    <c:if test="${cookie.get('myLocaleCookie').value eq 'en' or cookie.get('myLocaleCookie') eq null }">
                                                        ${payment.contract.service.titleEn}
                                                    </c:if>
                                                    <c:if test="${cookie.get('myLocaleCookie').value eq 'ru'}">
                                                        ${payment.contract.service.titleRu}
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <spring:message code="table.titleCall"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${payment.cost}</td>
                                        <td>${payment.paymentDate}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${payment.paid}">
                                                    <span class="label label-success"><spring:message code="table.paid.success"/></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="label label-warning"><spring:message code="table.paid.wait"/></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>


                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END TABLE HOVER -->
                </div>
            </div>
        </div>
    </div>
    <!-- END MAIN -->
    <div class="clearfix"></div>
    <footer>
        <div class="container-fluid">
            <p class="copyright">&copy; 2017 <a href="https://www.themeineed.com" target="_blank">Theme I Need</a>. All Rights Reserved.</p>
        </div>
    </footer>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="/assets/vendor/jquery/jquery.min.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="/assets/vendor/chartist/js/chartist.min.js"></script>
<script src="/assets/scripts/klorofil-common.js"></script>
</body>

</html>


