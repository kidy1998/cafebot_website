<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>제품 할인 등록/수정페이지</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/feather/feather.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
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
    <!-- End plugin css for this page -->
    <script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>

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
    <!-- partial:../../partials/_navbar.html -->
    <%@ include file="../promotion/fragments/top-nav.jsp" %>
    <!-- partial -->
        <div class="container-fluid page-body-wrapper">
            <!-- partial:../../partials/_sidebar.html -->
            <%@ include file="../promotion/fragments/nav-menu.jsp" %>

    <!-- partial -->

    <div class="main-panel">

        <div class="content-wrapper">
            <div class="row">
                <div class="col-lg-12 grid-margin">
                    <div id ="test">
                        <div class="card discount-register">
                            <%@ include file="fragments/content1.jsp" %>
                        </div>

                        <div id="content2" class="card discount-register">
                            <%@ include file="fragments/content2.jsp" %>
                        </div>

                        <div class="card discount-register">
                            <%@ include file="fragments/content3.jsp" %>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>


    <% String message = (String) request.getAttribute("message"); %>
    <% if ("register".equals(message)) { %>
        <script>
            Swal.fire({
                text: "메뉴가 등록되었습니다.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: '확인'
             });
        </script>
    <% } else if ("update".equals(message)) { %>
        <script>
            Swal.fire({
                text: "메뉴가 수정되었습니다.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: '확인'
            });
        </script>
    <% } else if ("delete".equals(message)) { %>
        <script>
            Swal.fire({
                text: "메뉴가 삭제되었습니다.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: '확인'
            });
        </script>
    <% } else { %>

    <% } %>



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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/beverageDiscount.js"></script>

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
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<!-- End custom js for this page-->
</body>
</html>