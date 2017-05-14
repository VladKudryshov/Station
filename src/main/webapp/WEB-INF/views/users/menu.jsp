<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav">
                <li><a href="/" ><i class="lnr lnr-home"></i> <span><spring:message code="link.account"/></span></a></li>

                <li>
                    <a href="#subService" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span><spring:message code="link.servicesUser"/></span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="subService" class="collapse ">
                        <ul class="nav">
                            <li><a href="/service/catalog" class=""><spring:message code="link.services.catalog"/></a></li>
                            <li><a href="/service/connected" class=""><spring:message code="link.services.added"/></a></li>
                        </ul>
                    </div>
                </li>

                <li><a href="/call" class=""><i class="lnr lnr-phone-handset"></i> <span><spring:message code="link.calls"/></span></a></li>
                <li><a href="/payment" class=""><i class="lnr lnr-license"></i> <span><spring:message code="link.paymentsUser"/></span></a></li>
            </ul>
        </nav>
    </div>
</div>