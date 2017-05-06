<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav">
                <li><a href="/" ><i class="lnr lnr-home"></i> <span>My account</span></a></li>

                <li>
                    <a href="#subService" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Services</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="subService" class="collapse ">
                        <ul class="nav">
                            <li><a href="/service/catalog" class="">Catalog services</a></li>
                            <li><a href="/service/connected" class="">Added sevices</a></li>
                        </ul>
                    </div>
                </li>

                <li><a href="/call" class=""><i class="lnr lnr-phone-handset"></i> <span>Calls</span></a></li>
                <li><a href="/payment" class=""><i class="lnr lnr-license"></i> <span>Payments</span></a></li>
            </ul>
        </nav>
    </div>
</div>