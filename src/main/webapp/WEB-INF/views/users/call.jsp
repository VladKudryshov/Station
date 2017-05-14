<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<%@ page contentType="text/html;charset=utf-8" %>
<!doctype html>
<html lang="en">

<head>
    <title>Dashboard | Services</title>
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
    <!-- CALL CSS -->
    <link rel="stylesheet" href="/assets/css/call.css">
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
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
                            <h3 class="panel-title"><spring:message code="link.calls"/></h3>
                        </div>
                        <div class="panel-body">


                            <script language="JavaScript" type="text/javascript">
                                var base = 60;
                                var clocktimer,dateObj,dh,dm,ds,ms;
                                var readout='';
                                var h=1,m=1,tm=1,s=0,ts=0,ms=0,init=0;

                                function ClearСlock() {
                                    clearTimeout(clocktimer);
                                    h=1;m=1;tm=1;s=0;ts=0;ms=0;
                                    init=0;
                                    readout='00:00:00';
                                    document.getElementById("start-call").innerHTML =readout;
                                }

                                function StartTIME() {
                                    var cdateObj = new Date();
                                    var t = (cdateObj.getTime() - dateObj.getTime())-(s*1000);
                                    if (t>999) { s++; }
                                    if (s>=(m*base)) {
                                        ts=0;
                                        m++;
                                    } else {
                                        ts=parseInt((ms/100)+s);
                                        if(ts>=base) { ts=ts-((m-1)*base); }
                                    }
                                    if (m>(h*base)) {
                                        tm=1;
                                        h++;
                                    } else {
                                        tm=parseInt((ms/100)+m);
                                        if(tm>=base) { tm=tm-((h-1)*base); }
                                    }
                                    ms = Math.round(t/10);
                                    if (ms>99) {ms=0;}
                                    if (ts>0) { ds = ts; if (ts<10) { ds = '0'+ts; }} else { ds = '00'; }
                                    dm=tm-1;
                                    if (dm>0) { if (dm<10) { dm = '0'+dm; }} else { dm = '00'; }
                                    dh=h-1;
                                    if (dh>0) { if (dh<10) { dh = '0'+dh; }} else { dh = '00'; }
                                    readout = dh + ':' + dm + ':' + ds;
                                    document.getElementById("timer").innerHTML = readout;
                                    clocktimer = setTimeout("StartTIME()",1000);
                                }
                                //Функция запуска и остановки
                                function StartStop() {
                                        if(init==0) {
                                            ClearСlock();
                                            dateObj = new Date();
                                            StartTIME();
                                            init = 1;
                                            document.getElementById("start-call").className= "btn btn-danger btn-lg";
                                            document.getElementById("start-call").innerHTML= "Stop call";
                                        }else{
                                            init = 0;
                                            clearTimeout(clocktimer);
                                            $.ajax({
                                                type: "POST",
                                                url: "/call",
                                                data: readout,
                                                contentType: "application/json; charset=utf-8",
                                                dataType: "json"
                                            });
                                            document.location.href="/call";
                                        }
                                }
                            </script>
                            <!--Form for stopwatch-->
                            <div class="call-view">
                                <h1><spring:message code="page.callTime"/> - <span id="timer">00:00:00</span></h1>
                                <button type="button" id="start-call" onclick="StartStop()" class="btn btn-primary btn-lg"><spring:message code="table.button.call"/></button>
                            </div>

                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th><spring:message code="table.Date"/></th>
                                        <th><spring:message code="table.Time"/></th>
                                        <th><spring:message code="table.cost"/></th>
                                        <th><spring:message code="table.paid"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="call" items="${listCalls}" varStatus="id">
                                            <tr>
                                                <td>${id.count}</td>
                                                <td>${call.date}</td>
                                                <td>${call.time}</td>
                                                <td>${call.cost}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${call.payment.paid}">
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


