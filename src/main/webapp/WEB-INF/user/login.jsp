<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>로그인</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/feather/feather.css">
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
    <!-- inject:css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loginpage.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png"/>
</head>
<body>
 <div class="container-scroller">
     <div class="login container-fluid page-body-wrapper full-page-wrapper">
         <div class="content-wrapper d-flex align-items-center auth px-0">
             <div class="row w-100 mx-0">
                 <div class="col-lg-4 mx-auto">
                     <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                         <h4>로그인</h4>
                         <h6 class="fw-light"></h6>
                         <form action="${pageContext.request.contextPath}/api/user/login" method="post" class="pt-3" id="loginForm">
                             <div class="form-group">

                                 <div>
                                     <label for="exampleInputStoreCode1">매장 코드</label>
                                 </div>
                                 <input name="code" type="text" class="form-control form-control-lg" id="exampleInputStoreCode1" placeholder="매장 코드를 정확히 입력해주세요">
                                 <br>
                                 <div>
                                     <label for="exampleInputPhonenum1">전화번호</label>
                                 </div>
                                 <input name="phoneNum" type="text" class="form-control form-control-lg" id="exampleInputPhonenum1" placeholder="전화번호를 정확히 입력해주세요">
                                 <br>
                                 <div>
                                     <label for="exampleInputPassword1">비밀번호</label>
                                 </div>
                                 <input name="password" type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="비밀번호를 정확히 입력해주세요">
                                 <br>
                                 <div class="password-reset-container">
                                     <a href="/page/user/resetpassword" class="password-reset-link">비밀번호 재설정</a>
                                 </div>
                             </div>

                         </form>

                         <!-- Error Modal -->
                         <div id="errorModal" class="modal fade" tabindex="-1" role="dialog" >
                             <div class="modal-dialog" role="document">
                                 <div class="modal-content">
                                     <div class="modal-header">
                                         <h5 class="modal-title"><strong>오류</strong></h5>
                                         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                             <span aria-hidden="true">&times;</span>
                                         </button>
                                     </div>
                                      <div class="modal-body">

                                              <p id="modalErrorMessage"><c:choose>
                                                  <c:when test="${not empty error}">${error}</c:when>
                                                  <c:otherwise>모든 필드를 올바르게 채워주세요.</c:otherwise>
                                              </c:choose></p>

                                     </div>
                                 </div>
                             </div>
                         </div>
                         <br>
                         <div class="d-grid gap-2">
                             <button type="button" class="btn btn-primary btn-lg fw-medium auth-form-btn">로그인</button>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!-- content-wrapper ends -->
     </div>
     <!-- page-body-wrapper ends -->
 </div>


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


<% String message = request.getParameter("message"); %>
<% if ("login_required".equals(message)) { %>
    <script>
        alert("로그인 후 이용하세요!");
    </script>
<% } %>



<!-- container-scroller -->
<!-- plugins:js -->
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Include Bootstrap JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>



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