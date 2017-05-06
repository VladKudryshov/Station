<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav">
                <li><a href="/" class="active"><i class="lnr lnr-home"></i> <span><spring:message code="link.account"/></span></a></li>
                <li>
                    <a href="#subUsers" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span><spring:message code="link.usersApp"/></span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="subUsers" class="collapse ">
                        <ul class="nav">
                            <li><a href="/user" class=""><spring:message code="link.userList"/></a></li>
                            <li><a href="/user/add" class=""><spring:message code="link.userAdd"/></a></li>
                            <li><a href="/user/unpaid" class=""><spring:message code="link.unpaidUser"/></a></li>
                            <li><a href="/user/blocked" class=""><spring:message code="link.blockedUser"/></a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a href="#subServices" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span><spring:message code="link.services"/></span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="subServices" class="collapse ">
                        <ul class="nav">
                            <li><a href="/service" class=""><spring:message code="link.serviceList"/></a></li>
                            <li><a href="/service/add" class=""><spring:message code="link.serviceAdd"/></a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="/payment/all" class=""><i class="lnr lnr-license"></i> <span><spring:message code="link.payments"/></span></a></li>
            </ul>
        </nav>
    </div>
</div>