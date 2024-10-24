<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">CafeBot Location Update</h4>
                <form action="/settings/dasom-locations/update?lang=eng" method="post" name="updateForm">
                 <div class="dasom-location-register-wrapper">
                    <div class="dasom-location-radio">
                        <input name="id" value="${theLocation.id}" hidden>
                        <p class="title">1. Please set the location of CafeBot within the café.</p>
                        <label class="custom-label first-label" for="entrance">
                            <input type="radio" name="location"
                                   id="entrance" value="입구 근처" ${theLocation.location eq '입구 근처' ? 'checked' : ''}>
                            Near Entrance
                        </label>
                        <label class="custom-label" for="counter">
                            <input type="radio" name="location"
                                   id="counter" value="계산대 근처" ${theLocation.location eq '계산대 근처' ? 'checked' : ''}>
                            Near Counter
                        </label>
                        <label class="custom-label" for="pickupTable">
                            <input type="radio" name="location"
                                   id="pickupTable" value="픽업테이블 근처" ${theLocation.location eq '픽업테이블 근처' ? 'checked' : ''}>
                            Near Pickup Table
                        </label>
                        <label class="custom-label" for="goodsDisplay">
                            <input type="radio" name="location"
                                   id="goodsDisplay" value="굿즈 매대 근처" ${theLocation.location eq '굿즈 매대 근처' ? 'checked' : ''}>
                            Near Goods Display
                        </label>
                        <label class="custom-label" for="other">
                            <input type="radio" name="location"
                                   id="other" value="기타" ${theLocation.location eq '기타' ? 'checked' : ''}>
                            Other
                        </label>
                    </div>

                    <div class="dasom-location-container">
                        <div class="dasom-location-register">
                            <p class="title">2. If there are products displayed around CafeBot, please select the number.</p>
                            <div class="main-content">
                                <ul class="location-list">
                                    <li class = "${not empty theLocation.leftSide ? 'selected' : ''}">
                                        <p>①</p>
                                        <p>Left</p>
                                    </li>
                                    <li>
                                        <i class="material-symbols-outlined">robot_2</i>
                                        <p>CafeBot</p>
                                    </li>
                                    <li class = "${not empty theLocation.rightSide ? 'selected' : ''}">
                                        <p>④</p>
                                        <p>Right</p>
                                    </li>
                                    <li class = "${not empty theLocation.leftFront ? 'selected' : ''}">
                                        <p>②</p>
                                        <p>Front Left</p>
                                    </li>
                                    <li class = "${not empty theLocation.front ? 'selected' : ''}">
                                        <p>③</p>
                                        <p>Front</p>
                                    </li>
                                    <li class = "${not empty theLocation.rightFront ? 'selected' : ''}">
                                        <p>⑤</p>
                                        <p>Front Right</p>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="dasom-location-input">
                            <p class="title">3. Please select the type of products displayed.<br> (Up to 2 selections)</p>

                            <div class="location-group">
                                <label for="input1">① Left</label>

                                <c:choose>
                                    <c:when test="${fn:length(theLocation.leftSide) == 2}">
                                        <c:set var="inputValue" value="${theLocation.leftSide.get(0)}, ${theLocation.leftSide.get(1)}" />
                                    </c:when>
                                    <c:when test="${fn:length(theLocation.leftSide) == 1}">
                                        <c:set var="inputValue" value="${theLocation.leftSide.get(0)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="inputValue" value="" />
                                    </c:otherwise>
                                </c:choose>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown1" name="leftSide"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="${inputValue}" ${not empty theLocation.leftSide ? '' : 'disabled'}>
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown1">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">Bakery</li>
                                        <li class="nav-item dropdown" data-value="디저트">Dessert</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="샐러드">Salad</li>
                                        <li class="nav-item dropdown" data-value="계산대">Counter</li>
                                        <li class="nav-item dropdown" data-value="유제품">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="병음료">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="케이크">Cake</li>
                                        <li class="nav-item dropdown" data-value="창가자리">window seat</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input4">② Front Left</label>

                                <c:choose>
                                    <c:when test="${fn:length(theLocation.leftFront) == 2}">
                                        <c:set var="inputValue" value="${theLocation.leftFront.get(0)}, ${theLocation.leftFront.get(1)}" />
                                    </c:when>
                                    <c:when test="${fn:length(theLocation.leftFront) == 1}">
                                        <c:set var="inputValue" value="${theLocation.leftFront.get(0)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="inputValue" value="" />
                                    </c:otherwise>
                                </c:choose>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown4" name="leftFront"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="${inputValue}" ${not empty theLocation.leftFront ? '' : 'disabled'}>
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown4">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">Bakery</li>
                                        <li class="nav-item dropdown" data-value="디저트">Dessert</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="샐러드">Salad</li>
                                        <li class="nav-item dropdown" data-value="계산대">Counter</li>
                                        <li class="nav-item dropdown" data-value="유제품">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="병음료">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="케이크">Cake</li>
                                        <li class="nav-item dropdown" data-value="창가자리">window seat</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input5">③ Front</label>

                                <c:choose>
                                    <c:when test="${fn:length(theLocation.front) == 2}">
                                        <c:set var="inputValue" value="${theLocation.front.get(0)}, ${theLocation.front.get(1)}" />
                                    </c:when>
                                    <c:when test="${fn:length(theLocation.front) == 1}">
                                        <c:set var="inputValue" value="${theLocation.front.get(0)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="inputValue" value="" />
                                    </c:otherwise>
                                </c:choose>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown5" name="front"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="${inputValue}" ${not empty theLocation.front ? '' : 'disabled'}>
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown5">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">Bakery</li>
                                        <li class="nav-item dropdown" data-value="디저트">Dessert</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="샐러드">Salad</li>
                                        <li class="nav-item dropdown" data-value="계산대">Counter</li>
                                        <li class="nav-item dropdown" data-value="유제품">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="병음료">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="케이크">Cake</li>
                                        <li class="nav-item dropdown" data-value="창가자리">window seat</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input3">④ Right</label>

                                <c:choose>
                                    <c:when test="${fn:length(theLocation.rightSide) == 2}">
                                        <c:set var="inputValue" value="${theLocation.rightSide.get(0)}, ${theLocation.rightSide.get(1)}" />
                                    </c:when>
                                    <c:when test="${fn:length(theLocation.rightSide) == 1}">
                                        <c:set var="inputValue" value="${theLocation.rightSide.get(0)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="inputValue" value="" />
                                    </c:otherwise>
                                </c:choose>


                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown3" name="rightSide"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="${inputValue}" ${not empty theLocation.rightSide ? '' : 'disabled'}>
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown3">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">Bakery</li>
                                        <li class="nav-item dropdown" data-value="디저트">Dessert</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="샐러드">Salad</li>
                                        <li class="nav-item dropdown" data-value="계산대">Counter</li>
                                        <li class="nav-item dropdown" data-value="유제품">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="병음료">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="케이크">Cake</li>
                                        <li class="nav-item dropdown" data-value="창가자리">window seat</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>


                            <div class="location-group">
                                <label for="input6">⑤ Front Right</label>

                                <c:choose>
                                    <c:when test="${fn:length(theLocation.rightFront) == 2}">
                                        <c:set var="inputValue" value="${theLocation.rightFront.get(0)}, ${theLocation.rightFront.get(1)}" />
                                    </c:when>
                                    <c:when test="${fn:length(theLocation.rightFront) == 1}">
                                        <c:set var="inputValue" value="${theLocation.rightFront.get(0)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="inputValue" value="" />
                                    </c:otherwise>
                                </c:choose>


                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown6" name="rightFront"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="${inputValue}" ${not empty theLocation.rightFront ? '' : 'disabled'}>
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown6">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">Bakery</li>
                                        <li class="nav-item dropdown" data-value="디저트">Dessert</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="샐러드">Salad</li>
                                        <li class="nav-item dropdown" data-value="계산대">Counter</li>
                                        <li class="nav-item dropdown" data-value="유제품">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="병음료">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="케이크">Cake</li>
                                        <li class="nav-item dropdown" data-value="창가자리">window seat</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">Custom input</li>
                                        <input type="text" class="hidden" id="directInput"></input>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-buttons">
                       
                        <button type="submit" class="btn btn-primary">
                            Update
                        </button>

                        <button type="button" class="btn btn-outline-primary">
                            <a href="/settings/dasom-locations/delete?id=${theLocation.id}&lang=eng" 
                                onclick="return confirm('Do you want to delete this location?');" style="text-decoration: none;">
                                Delete
                            </a>
                        </button>

                    </div>
                </div>
               </form>
            </div>

            <div id="userInputModal" class="modal-addc">
                <div class="modal-popup-addc">
                    <ul>
                        <li><span>Custom Input</span><span id="modal-product-desc" style="size: 15px;">**Enter the product you want to introduce.</span></li>
                        <li><span><input type="text" id ="user-input"></span></li>

                    </ul>
                    <div id="modal-footer">
                    <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/dasom_location.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </body>
</html>
