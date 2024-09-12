<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>
</head>
<body>
    <div id="content">
        <div class="card-body">
            <h4 class="card-title">제품할인 등록</h4>
            <div class="table-responsive first-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>품목</th>
                        <th>제품</th>
                        <th>정가</th>
                        <th>할인가(원)</th>
                        <th>행사기간</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <ul class="navbar-nav custom-input form-control">
                                <li class="nav-item dropdown d-lg-block">
                                    <a class="dropdown-bordered dropdown-toggle"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        <span id="selectedItem"> 선택 </span>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                         aria-labelledby="messageDropdown">
                                        <a class="dropdown-item preview-item" data-value="음료">
                                            <div class="preview-item-content flex-grow">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    음료</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="베이커리">
                                            <div class="preview-item-content flex-grow">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    베이커리</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="케이크">
                                            <div class="preview-item-content flex-grow">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    케이크</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="세트상품">
                                            <div class="preview-item-content flex-grow">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    세트상품</p>
                                            </div>
                                        </a>
                                    </div>
                                </li>
                            </ul>

                        </td>
                        <td>
                            <div class="input-container">
                                <!--<input type="text" class="custom-input form-control search" disabled>-->
                                <button class="search-button custom-input form-control"
                                        id="product_search_btn"><i
                                        class="icon-search"></i>
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                <p id="productPrice">6,000원</p>
                                <p id="discountPrice"></p>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                <input type="number" class="custom-input form-control"
                                       id="discountPriceInput">
                                <button type="button" class="btn btn-outline-primary btn-sm"
                                        id="confirmDiscountPriceBtn">확인
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section date">
                                <input type="date" class="custom-input form-control" id="startDate">

                                &nbsp~&nbsp
                                <input type="date" class="custom-input form-control" id="endDate">
                                <label class="custom-label">
                                    <input type="checkbox">
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
                                <input type="time" class="custom-input form-control" id="eventStartTime" name="eventStartTime">

                                &nbsp~&nbsp
                                <input type="time" class="custom-input form-control" id="eventEndTime" name="eventEndTime">
                                <label class="custom-label" for="sameTimeCheckbox">
                                    <input type="checkbox" id="sameTimeCheckbox" name="sameTimeCheckbox">
                                    &nbsp영업시간과 동일
                                </label>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section time">
                                <input type="time" class="custom-input form-control" id="speechStartTime">

                                &nbsp~&nbsp
                                <input type="time" class="custom-input form-control">
                                <label class="custom-label">
                                    <input type="checkbox">
                                    &nbsp행사시간과 동일
                                </label>
                            </div>
                        </td>
                        <td>
                            <ul class="navbar-nav custom-input form-control">
                                <li class="nav-item dropdown d-lg-block">
                                    <a class="dropdown-bordered dropdown-toggle"
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        <span class="selectedItem"> 선택 </span>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list pb-0 pt-0"
                                         aria-labelledby="messageDropdown">
                                        <a class="dropdown-item preview-item" data-value="5분 간격">
                                            <div class="preview-item-content flex-grow py-2">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    5분 간격</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="10분 간격">
                                            <div class="preview-item-content flex-grow py-2">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    10분 간격</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="15분 간격">
                                            <div class="preview-item-content flex-grow py-2">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    15분 간격</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="20분 간격">
                                            <div class="preview-item-content flex-grow py-2">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    20분 간격</p>
                                            </div>
                                        </a>
                                        <a class="dropdown-item preview-item" data-value="30분 간격">
                                            <div class="preview-item-content flex-grow py-2">
                                                <p class="preview-subject ellipsis fw-medium text-dark">
                                                    30분 간격</p>
                                            </div>
                                        </a>
                                    </div>


                                </li>
                            </ul>

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
                                    <input type="radio" name="discountCondition"
                                           id="discountOptionNone" value="none" checked>
                                    없음
                                </label>
                                <label class="custom-label" for="discountOptionYes">
                                    <input type="radio" name="discountCondition"
                                           id="discountOptionYes" value="yes">
                                    있음
                                </label>
                                <input type="text" class="custom-input form-control" name="discountConditionText" disabled>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section radio">
                                <label class="custom-label first-label" for="descriptionNone">
                                    <input type="radio" name="description" id="descriptionNone" value="none" checked>없음
                                </label>
                                <label class="custom-label" for="descriptionYes">
                                    <input type="radio" name="description" id="descriptionYes" value="yes">있음
                                </label>
                                <input type="text" class="custom-input form-control" name="descriptionText" disabled>
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
                        <th>AI 다솜 멘트 생성<span class="info">* AI 멘트는 하루에 3번까지만 가능합니다. (0/3)</span>
                        </th>
                        <th>테스트 진행<span class="info">* 다솜이 어떻게 말하는지 확인해보세요!</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <textarea></textarea>
                            <div class="button-container">
                                <button type="button" class="btn btn-outline-light btn-sm">멘트생성
                                </button>
                                <button type="button" class="btn btn-outline-light btn-sm">불러오기
                                </button>
                                <button type="button" class="btn btn-outline-light btn-sm">수정
                                </button>
                            </div>
                        </td>
                        <td class="test">
                            <button type="button" class="btn btn-primary">다솜 홍보 테스트
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="form-buttons">
                <!-- 삭제 버튼은 제품 할인 영역에서 "수정" 버튼 눌렀을 시 생김
                <button type="button" class="btn btn-outline-primary">삭제
                </button>-->
                <button type="button" class="btn btn-primary">등록
                </button>
            </div>
        </div>
    </div>
</body>
</html>