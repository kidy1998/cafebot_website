<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
                        <button class="dropdown-item preview-item" onclick="switchLanguage('kor')" style="">
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

                // confirm 창으로 lang 값 확인
                

            
                // 새 URL로 이동
                window.location.href = currentUrl;
                
            }

        </script>
    </nav>