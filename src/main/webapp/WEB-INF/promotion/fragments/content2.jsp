<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">


</head>
<body>
    <div class="card-body">
        <h4 class="card-title">제품홍보 등록</h4>

        <form action="/api/promotion-discount/register" method="post" id="promotionForm">
            <div class="table-responsive first-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>품목</th>
                        <th>제품</th>
                        <th>정가(할인가)</th>
                        <th>할인 값(원)</th>
                        <th>행사기간</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem"> 선택 </span></a>
                                <div class="dropdown-content">
                                    <a class ="dropdown-item" data-value="음료" >음료</a>
                                    <a class ="dropdown-item" data-value="베이커리">베이커리</a>
                                    <a class ="dropdown-item" data-value="케이크" >케이크</a>
                                    <a class ="dropdown-item" data-value="세트상품">세트상품</a>
                                </div>
                            </div>

                            <input type="hidden" id="thiscategoryInput00" name="menuPromoId" value=1>
                            <!-- category hidden input -->
                            <input type="hidden" id="categoryInput" name="category" value="BEVERAGE">
                        </td>
                        <td>
                            <div class="input-container">
                                <!--<input type="text" class="custom-input form-control search" disabled>-->
                                <button type="button" class="search-button custom-input form-control"
                                        id="product_search_btn"><i
                                        class="icon-search" onclick="openMenuModal()"></i>
                                </button>
                                <input type="hidden" id="menunameInput" name="menu" >

                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                <p id="productPrice">6,000원</p>
                                <input type="hidden" id="priceInput" name="price" value=6000>

                                <p id="discountPrice"></p>
                                <input type="hidden" id="discPriceInput" name="discPrice" value=1000>

                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                - &nbsp <input type="number" class="custom-input form-control"
                                       id="discountPriceInput" name="discVal">
                                <button type="button" class="btn btn-outline-primary btn-sm"
                                        id="confirmDiscountPriceBtn" onclick="onclickConfirmDiscountPriceBtn()">확인
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section date">
                                <input type="date" class="custom-input form-control" id="startDate" name="startDate">

                                &nbsp~&nbsp
                                <input type="date" class="custom-input form-control" id="endDate" name="endDate">
                                <label class="custom-label">
                                    <input type="checkbox" name="boolIsAlways" id="isAlwaysCheckbox">
                                    &nbsp상시
                                </label>
                            </div>
                        </td>

                    </tr>

                    </tbody>
                </table>
            </div>


            <div class="table-responsive second-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>행사시간</th>
                        <th>멘트 발화시간</th>
                        <th>발화 횟수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="discount-section time">
                                <input id="eventStartTime" name="startTime"   type="time" class="timepicker text-center">

                                &nbsp~&nbsp

                                <input id="eventEndTime" name="endTime"  type="time" class="timepicker text-center">

                                <!-- <label class="custom-label" for="sameTimeCheckbox">
                                    <input type="checkbox" id="sameTimeCheckbox" name="boolEqlStoreOpr">
                                    &nbsp영업시간과 동일
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="discount-section time">

                                <input id="speechStartTime" name="mentStartTime"  type="time" class="timepicker text-center">

                                &nbsp~&nbsp

                                <input id="speechEndTime" name="mentEndTime"   type="time" class="timepicker text-center">
                                <!-- <label class="custom-label">
                                    <input type="checkbox" name="boolEqlEventStart">
                                    &nbsp행사시간과 동일
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem"> 선택 </span></a>
                                <div class="dropdown-content">
                                    <a class ="dropdown-item" data-value="10분 간격">10분 간격</a>
                                    <a class ="dropdown-item" data-value="20분 간격">20분 간격</a>
                                    <a class ="dropdown-item" data-value="30분 간격">30분 간격</a>
                                </div>
                            </div>

                            <input type="hidden" id="intervalInput" name="interval" value=10>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>

            <div class="table-responsive third-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>할인 조건 추가</th>
                        <th>제품 소개</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="discount-section radio">
                                <label class="custom-label first-label" for="discountOptionNone">
                                    <input type="radio" name="boolAddCond"
                                        onchange="changeAdditionalOption(false, 'addCondId')" 
                                        id="discountOptionNone" value="false" checked>
                                    없음
                                </label>
                                <label class="custom-label" for="discountOptionYes">
                                    <input type="radio" name="boolAddCond"
                                        onchange="changeAdditionalOption(true, 'addCondId')" 
                                        id="discountOptionYes" value="true">
                                    있음
                                </label>
                                <!-- <input type="hidden" id="isAddCondInput" value="false" name="boolAddCond"> -->
                                <input type="text" class="custom-input form-control" id="addCondId" name="addDiscCond" disabled>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section radio">
                                <label class="custom-label first-label" for="descriptionNone">
                                    <input type="radio" name="boolAddDesc"
                                        onchange="changeAdditionalOption(false, 'addMenuDescId')" 
                                        id="descriptionNone" value="false" checked>
                                    없음
                                </label>
                                <label class="custom-label" for="descriptionYes">
                                    <input type="radio" name="boolAddDesc"
                                        onchange="changeAdditionalOption(true, 'addMenuDescId')" 
                                        id="descriptionYes" value="true">
                                    있음
                                </label>
                                <!-- <input type="hidden" id="isAddDescInput" value="false" name="boolAddDesc"> -->
                                <input type="text" class="custom-input form-control" id="addMenuDescId" name="addMenuDesc" disabled>

                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="table-responsive fourth-tableg">
                <table class="table">
                    <thead>
                        <tr>
                            <th>AI 카페봇 멘트 생성
                            </th>
                           <!-- <th>테스트 진행 <span class="info">* 카페봇이 어떻게 말하는지 확인해보세요!</span> 
                            </th> --> 
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                           
                            <textarea id="ment-textarea" name="ment"></textarea>
                            <div class="button-container">
                                <button type="button" onclick="createMent()" class="btn btn-outline-light btn-sm">멘트생성
                                </button>
                                <!-- <button type="button" class="btn btn-outline-light btn-sm">불러오기
                                </button> -->
                                <button type="button" class="btn btn-outline-light btn-sm">수정
                                </button>
                            </div>
                        </td>
                        <td class="test">
                            <!-- <button type="button" class="btn btn-primary" onclick="mentTest()">카페봇 홍보 테스트
                            </button> -->
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="form-buttons">
                <!-- 삭제 버튼은 제품 할인 영역에서 "수정" 버튼 눌렀을 시 생김
                <button type="button" class="btn btn-outline-primary">삭제
                </button>-->
                <button type="submit" class="btn btn-primary">등록
                </button>
            </div>
        </form>
        </div>

        <div id="testmodaal" disabled>

        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
    <script>
        $('.timepicker').timepicker({
            timeFormat: 'HH:mm',
            interval: 30,
            minTime: '00',
            maxTime: '23:30pm',
            startTime: '00:00',
            dynamic: false,
            dropdown: true,
            scrollbar: true
        });
    </script>
    <script>
        document.getElementById('startDate').addEventListener('change', function() {
            var startDateVal = this.value;
            var endDateInput = document.getElementById('endDate');

            console.log(startDateVal);

            endDateInput.min = startDateVal;

            if (endDateInput.value < startDateVal) {
                endDateInput.value = startDateVal;
            }
        });

    </script>


</body>
</html>