<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">카페봇 위치 등록</h4>
                <form action="/settings/dasom-locations/register?lang=kor" method="post" name="updateForm">
                 <div class = "dasom-location-register-wrapper">
                    <div class="dasom-location-radio">
                        <p class="title">1. 카페 안에서 카페봇이 있는 위치를 설정해 주세요.</p>
                        <label class="custom-label first-label" for="entrance">
                            <input type="radio" name="location"
                                   id="entrance" value="입구 근처" checked>
                            입구 근처
                        </label>
                        <label class="custom-label" for="counter">
                            <input type="radio" name="location"
                                   id="counter" value="계산대 근처">
                            계산대 근처
                        </label>
                        <label class="custom-label" for="pickupTable">
                            <input type="radio" name="location"
                                   id="pickupTable" value="픽업테이블 근처">
                            픽업테이블 근처
                        </label>
                        <label class="custom-label" for="goodsDisplay">
                            <input type="radio" name="location"
                                   id="goodsDisplay" value="굿즈 매대 근처">
                            굿즈 매대 근처
                        </label>
                        <label class="custom-label" for="other">
                            <input type="radio" name="location"
                                   id="other" value="기타">
                            기타
                        </label>
                    </div>

                    <div class="dasom-location-container">
                        <div class="dasom-location-register">
                            <p class="title">2. 카페봇 주변에 판매제품이 전개되어 있다면 번호를 선택해 주세요</p>
                            <div class="main-content">
                                <ul class="location-list">
                                    <li>
                                        <p>①</p>
                                        <p>왼쪽</p>
                                    </li>
                                    <li>
                                        <i class="material-symbols-outlined">robot_2</i>
                                        <p>카페봇</p>
                                    </li>
                                    <li>
                                        <p>④</p>
                                        <p>오른쪽</p>
                                    </li>
                                    <li>
                                        <p>②</p>
                                        <p>왼쪽 앞</p>
                                    </li>
                                    <li>
                                        <p>③</p>
                                        <p>앞</p>
                                    </li>
                                    <li>
                                        <p>⑤</p>
                                        <p>오른쪽 앞</p>
                                    </li>
                                </ul>
                            </div>
                        </div>


                        <div class="dasom-location-input">
                            <p class="title">3. 어떤 제품이 전개되어 있는지 선택해 주세요.<br> (최대 2개까지 선택)</p>
                            <div class="location-group">
                                <label for="input1">① 왼쪽</label>

                                <c:set var="inputValue" value="" />

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown1" name="leftSide" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown1">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">베이커리</li>
                                        <li class="nav-item dropdown" data-value="디저트">디저트</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">샌드위치</li>
                                        <li class="nav-item dropdown" data-value="샐러드">샐러드</li>
                                        <li class="nav-item dropdown" data-value="계산대">계산대</li>
                                        <li class="nav-item dropdown" data-value="유제품">유제품</li>
                                        <li class="nav-item dropdown" data-value="병음료">병음료</li>
                                        <li class="nav-item dropdown" data-value="케이크">케이크</li>
                                        <li class="nav-item dropdown" data-value="창가자리">창가자리</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">직접입력</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input4">② 왼쪽 앞</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown4" name="leftFront" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown4">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">베이커리</li>
                                        <li class="nav-item dropdown" data-value="디저트">디저트</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">샌드위치</li>
                                        <li class="nav-item dropdown" data-value="샐러드">샐러드</li>
                                        <li class="nav-item dropdown" data-value="계산대">계산대</li>
                                        <li class="nav-item dropdown" data-value="유제품">유제품</li>
                                        <li class="nav-item dropdown" data-value="병음료">병음료</li>
                                        <li class="nav-item dropdown" data-value="케이크">케이크</li>
                                        <li class="nav-item dropdown" data-value="창가자리">창가자리</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">직접입력</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input5">③ 앞</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown5" name="front" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown5">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">베이커리</li>
                                        <li class="nav-item dropdown" data-value="디저트">디저트</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">샌드위치</li>
                                        <li class="nav-item dropdown" data-value="샐러드">샐러드</li>
                                        <li class="nav-item dropdown" data-value="계산대">계산대</li>
                                        <li class="nav-item dropdown" data-value="유제품">유제품</li>
                                        <li class="nav-item dropdown" data-value="병음료">병음료</li>
                                        <li class="nav-item dropdown" data-value="케이크">케이크</li>
                                        <li class="nav-item dropdown" data-value="창가자리">창가자리</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">직접입력</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input3">④ 오른쪽</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown3" name="rightSide" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown3">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">베이커리</li>
                                        <li class="nav-item dropdown" data-value="디저트">디저트</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">샌드위치</li>
                                        <li class="nav-item dropdown" data-value="샐러드">샐러드</li>
                                        <li class="nav-item dropdown" data-value="계산대">계산대</li>
                                        <li class="nav-item dropdown" data-value="유제품">유제품</li>
                                        <li class="nav-item dropdown" data-value="병음료">병음료</li>
                                        <li class="nav-item dropdown" data-value="케이크">케이크</li>
                                        <li class="nav-item dropdown" data-value="창가자리">창가자리</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">직접입력</li>
                                    </ul>
                                </div>
                            </div>


                            <div class="location-group">
                                <label for="input6">⑤ 오른쪽 앞</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown6" name="rightFront" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown6">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="베이커리">베이커리</li>
                                        <li class="nav-item dropdown" data-value="디저트">디저트</li>
                                        <li class="nav-item dropdown" data-value="샌드위치">샌드위치</li>
                                        <li class="nav-item dropdown" data-value="샐러드">샐러드</li>
                                        <li class="nav-item dropdown" data-value="계산대">계산대</li>
                                        <li class="nav-item dropdown" data-value="유제품">유제품</li>
                                        <li class="nav-item dropdown" data-value="병음료">병음료</li>
                                        <li class="nav-item dropdown" data-value="케이크">케이크</li>
                                        <li class="nav-item dropdown" data-value="창가자리">창가자리</li>
                                        <li class="nav-item dropdown" data-value="직접입력" onclick="openAdditionalContent()">직접입력</li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="form-buttons">
                        <!-- 삭제 버튼은 제품 할인 영역에서 "수정" 버튼 눌렀을 시 생김
                        <button type="button" class="btn btn-outline-primary">삭제
                        </button>-->
                        <button type="submit" class="btn btn-primary">저장
                        </button>
                    </div>
                </div>
               </form>
            </div>
            <input type="text" hidden id="temp-input">

            <div id="userInputModal" class="modal-addc">
                <div class="modal-popup-addc">
                    <ul>
                        <li><span>직접입력</span><span id="modal-product-desc" style="size: 15px;">**소개하고 싶은 제품을 직접 입력하세요.</span></li>
                        <li><span><input type="text" id ="user-input"></span></li>

                    </ul>
                    <div id="modal-footer">
                    <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">입력</button>
                    </div>
                </div>
            </div>


        </div>
    </body>
</html>