<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cafebot Location Settings</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/feather/feather.css">
    <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/typicons/typicons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- End plugin css for this page -->

    <!-- 알림창 관련 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- inject:css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/beverageDiscountpage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/settings.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png"/>

</head>
<body>

<div class="container-scroller">
    <nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex align-items-top flex-row">

        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-start">
           
            <div style="margin-left:20px;">
                <a class="navbar-brand brand-logo-mini" href="../../page/main">
                    <h4> Admin Page</h4>
                </a>
            </div>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-top">
          
            <ul class="navbar-nav ms-auto">

                <li class="nav-item dropdown d-none d-lg-block">
                    <a class="nav-link dropdown-bordered dropdown-toggle dropdown-toggle-split" id="messageDropdown"
                       href="#" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="ti-world icon-md text-info d-flex align-self-start me-3"></i>
                        <span class="selectedItem"> Language </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0"
                         aria-labelledby="messageDropdown" style="z-index: 1050;">
                        <div class="dropdown-divider"></div>
                        <!-- 한국어 선택 -->
                        <button class="dropdown-item preview-item" onclick="switchLanguage('kor')" >
                            <div class="preview-item-content flex-grow py-2">
                                <span class="preview-subject ellipsis fw-medium text-dark">Korean</span>
                            </div>
                        </button>
                        <!-- 영어 선택 -->
                        <button class="dropdown-item preview-item" onclick="switchLanguage('eng')">
                            <div class="preview-item-content flex-grow py-2">
                                <span class="preview-subject ellipsis fw-medium text-dark">English</span>
                            </div>
                        </button>
                    </div>
                </li>
                
                <li class="nav-item d-none d-lg-block">
                    <c:choose>
                       
                        <c:when test="${sessionScope.userId == null}">
                            <p id="login-user-info"><strong>Welcome. Guest!</strong></p>
                        </c:when>

                        <c:otherwise>
                            <p id="login-user-info"><strong>Welcome. ${sessionScope.userName}!</strong></p>
                        </c:otherwise>
                    </c:choose>
                </li>

            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                    data-bs-toggle="offcanvas">
                <span class="mdi mdi-menu"></span>
            </button>
        </div>

    </nav>

        <div class="container-fluid page-body-wrapper">
             <%@ include file="../promotion/fragments/nav-menu_eng.jsp" %>

    <div class="main-panel">
        <div class="content-wrapper dasomLocation">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Cafebot Location Lists</h4>
                    <span><button id="prevButton" class="hidden" onclick="showPreviousItems()" style="border: none; background-color:white;"><image style="width: 40px; height: 100%" src="${pageContext.request.contextPath}/assets/images/free-icon-arrow-right-6423875left.svg"/></button><span>
                        <c:forEach items="${all_robot_location_category_list}" var="robot_location" varStatus="status">
                            <ul class="location-info dropdown-options hidden" id="settings${robot_location.id}">
                                <h5><strong>${robot_location.location}</strong></h5>
                                <br>

                                <!-- 활성화 선택을 위한 radio 버튼 -->
                                <label style="display: inline-block; margin-right: 10px;">
                                    Activate
                                </label>

                                <input type="radio" name="locationToggle" id="toggleOn${status.index}"
                                        <c:if test="${robot_location.use eq 'true'}">checked</c:if>
                                        onclick="confirmToggleClick('${status.index}', '${robot_location.id}', 'eng')">
                              
                                <br><br>

                                <label style="display: inline-block; margin-right: 10px;">LeftSide</label>
                                <c:choose>
                                    <c:when test="${not empty robot_location.leftSide}">
                                        <ul style="display: inline-block; padding-left: 0; list-style: none;">
                                            <c:forEach items="${robot_location.leftSide}" var="lse" varStatus="status">
                                                <li style="display: inline-block; margin-right: 10px;"><span>${lse}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                </c:choose>
                                <br>
                                <label style="display: inline-block; margin-right: 10px;">Left Front</label>
                                <c:choose>
                                    <c:when test="${not empty robot_location.leftFront}">
                                        <ul style="display: inline-block; padding-left: 0; list-style: none;">
                                            <c:forEach items="${robot_location.leftFront}" var="lfe" varStatus="status">
                                                <li style="display: inline-block; margin-right: 10px;"><span>${lfe}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                </c:choose>
                                <br>
                                <label style="display: inline-block; margin-right: 10px;">FrontSide</label>
                                <c:choose>
                                    <c:when test="${not empty robot_location.front}">
                                        <ul style="display: inline-block; padding-left: 0; list-style: none;">
                                            <c:forEach items="${robot_location.front}" var="fe" varStatus="status">
                                                <li style="display: inline-block; margin-right: 10px;"><span>${fe}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                </c:choose>
                                <br>
                                <label style="display: inline-block; margin-right: 10px;">RightSide</label>
                                <c:choose>
                                    <c:when test="${not empty robot_location.rightSide}">
                                        <ul style="display: inline-block; padding-left: 0; list-style: none;">
                                            <c:forEach items="${robot_location.rightSide}" var="rse" varStatus="status">
                                                <li style="display: inline-block; margin-right: 10px;"><span>${rse}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                </c:choose>
                                <br>
                                <label style="display: inline-block; margin-right: 10px;">Right Front</label>
                                <c:choose>
                                    <c:when test="${not empty robot_location.rightFront}">
                                        <ul style="display: inline-block; padding-left: 0; list-style: none;">
                                            <c:forEach items="${robot_location.rightFront}" var="rfe" varStatus="status">
                                                <li style="display: inline-block; margin-right: 10px;"><span>${rfe}</span></li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                </c:choose>
                                <br>
                                <button class="btn-primary edit-location" onclick="loadUpdateLocationContent(${robot_location.id}, 'eng')">
                                    <i class="material-symbols-outlined">pin_drop</i>
                                    Edit
                                </button>
                                <br>
                              
                                
                            </ul>
                        </c:forEach>
                    <span><button id="nextButton" class="hidden" onclick="showNextItems()" style="border: none; background-color:white;"><image style="width: 40px; height: 100%" src="${pageContext.request.contextPath}/assets/images/free-icon-arrow-right-6423875.svg"/></button></span>
                </div>
            </div>
            <br>
            <br>
            <div id="location_input">
                <%@ include file = "fragments/location_registration_eng.jsp" %>
            </div>
<!-- locationupdate or locationregisteration  -->
        </div>
        </div>
    </div>
</div>


    <% String message = (String) request.getAttribute("message"); %>
    <% if ("register".equals(message)) { %>
        <script>
            Swal.fire({
                text: "Location has been registered.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: 'confirm'
            });
        </script>
    <% } else if ("update".equals(message)) { %>
        <script>
            Swal.fire({
                text: "Location has been updated.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: 'confirm'
            });
        </script>
    <% } else if ("delete".equals(message)) { %>
        <script>
           Swal.fire({
                text: "Location has been deleted.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: 'confirm'
            });
        </script>
    <% }  else if ("location".equals(message)) { %>
        <script>
            Swal.fire({
                text: "Location has been changed.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: 'confirm'
            });
        </script>
    <% } else { %>

    <% } %>


    <script>

        function switchLanguage(lang) {

            let currentUrl = window.location.href;

            if (currentUrl.includes("?")) {
                // 기존 lang 파라미터를 대체
                if (currentUrl.includes("lang=")) {
                    currentUrl = currentUrl.replace(/lang=\w*/, `lang=` + lang);
                } else {
                    currentUrl += `&lang=` + lang;
                }
            } else {
                currentUrl += `?lang=` + lang;
            }

            // 새 URL로 이동
            window.location.href = currentUrl;
            
        }

    </script>

<!-- content-wrapper ends -->
<!-- partial:../../partials/_footer.html -->
<footer class="footer">
    <div class="d-sm-flex justify-content-center justify-content-sm-between">
                    <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Premium <a
                            href="https://www.bootstrapdash.com/" target="_blank">Bootstrap admin template</a> from BootstrapDash.</span>
        <span class="float-none float-sm-end d-block mt-1 mt-sm-0 text-center">Copyright © 2023. All rights reserved.</span>
    </div>
</footer>
<!-- partial -->
</div>
<!-- main-panel ends -->
</div>
<!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<!-- plugins:js -->
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Include Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/dasom_location.js"></script>


<script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<script src="${pageContext.request.contextPath}/assets/vendors/typeahead.js/typeahead.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendors/select2/select2.min.js"></script>
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="${pageContext.request.contextPath}/assets/js/off-canvas.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/settings.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/hoverable-collapse.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/todolist.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<script src="${pageContext.request.contextPath}/assets/js/file-upload.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/typeahead.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/select2.js"></script>


<!-- End custom js for this page-->
</body>
</html>