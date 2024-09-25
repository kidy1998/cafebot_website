<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <div style="padding: 20px;"><a href="../../?lang=eng" style="text-decoration : none; text-color: black;"><h2>Admin Page</h2></a></div>

            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="collapse" href="../../page/main" aria-expanded="false"
                        aria-controls="form-elements">
                        <i class="material-symbols-outlined">bar_chart_4_bars</i>
                        <span class="menu-title">Sales Data Statistics</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="collapse" href="#" aria-expanded="false"
                       aria-controls="form-elements">
                        <i class="material-symbols-outlined">inventory_2</i>
                        <span class="menu-title">Inventory Management</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="collapse" href="#promotions" aria-expanded="false"
                                                               aria-controls="promotions">
                        <i class="material-symbols-outlined">campaign</i>
                        <span class="menu-title">CafeBot Promotion</span>
                    </a>
                    <div class="collapse" id="promotions">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link"
                                                    href="../../api/promotion-discount/main?lang=eng">Product Promotion</a></li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="#">Promotion</a></li>
                            <li class="nav-item"><a class="nav-link" href="#">Notice</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="collapse" href="#tables" aria-expanded="false"
                       aria-controls="tables">
                        <i class="material-symbols-outlined">settings</i>
                        <span class="menu-title">Settings</span>
                    </a>
                    <div class="collapse" id="tables">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link" href="#">My Info Management</a></li>

                            <li class="nav-item">
                                <a class="nav-link" data-bs-toggle="collapse" href="#cafebotlocations" aria-expanded="false"
                                                                           aria-controls="cafebotlocations">
                                    <span class="menu-title">Store Info Management</span>
                                </a>
                                <div class="show" id="cafebotlocations">
                                    <ul class="nav flex-column sub-menu" style="padding-left: 10px;">
                                        <li style="list-style-type: none;" ><a class="nav-link" href="#"><span class="selectedMenuIcon">></span> &nbsp CafeBot Promotion Status</a></li>
                                        <li style="list-style-type: none;" ><a class="nav-link" href="../../settings/dasom-locations/main?lang=eng"><span class="selectedMenuIcon" id="cafebotLocation_icon">></span> &nbsp CafeBot Location Settings</a></li>
                                        <li style="list-style-type: none;" ><a class="nav-link" href="#"><span class="selectedMenuIcon">></span> &nbsp Unlink</a></li>
                                    </ul>
                                </div>
                            </li>

                            <li class="nav-item"><a class="nav-link" href="#">CafeBot Device Management</a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/page/user/login">Login</a>
                    <a class="nav-link" href="/page/user/register">Sign Up</a>

                    <c:choose>
                        <c:when test="${sessionScope.userId != null}">
                            <a class="nav-link" href="/api/user/logout">Logout</a>
                        </c:when>
                    </c:choose>

                </li>
            </ul>
        </nav>
