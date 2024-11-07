<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>


</head>
<body>
    <div class="card-body">

        <h4 class="card-title">제품 홍보</h4>
        <div id="all-table">
            <table class="table all-promotion-list">
                <thead>
                    <tr>
                        <th>상태</th>
                        <th>품목</th>
                        <th>제품</th>
                        <th>정가</th>
                        <th>할인가</th>
                        <th>행사기간</th>
                        <th>행사시간</th>
                        <th>멘트발화시간</th>
                        <th>발화 간격</th>
                        <th>조건 및 멘트</th>
                        <th>편집</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${all_promotion_list}" var="promotion" varStatus="status">
                        <c:if test="${status.index < 3}">
                            <tr>
                                <td>

                                    <div class="my_dropdown">
                                        <a class="dropbtn"><span id="selectedItem"> ${promotion.status} </span></a>
                                        <c:choose>
                                            <c:when test="${promotion.status eq '예정'}"></c:when>
                                            <c:otherwise>
                                                <div class="dropdown-content">
                                                    <a class ="dropdown-item" data-value="진행중" onclick= "changeStatus(${promotion.menuPromoId}, `진행중`)">진행중</a>
                                                    <a class ="dropdown-item" data-value="중지" onclick= "changeStatus(${promotion.menuPromoId}, `중지`)">중지</a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                </td>
                                <td>${promotion.category}</td>
                                <td>${promotion.menu}</td>
                                <td>${promotion.price}원</td>
                                <td><!--<span style="color: red">(-${promotion.discVal})&nbsp</span>-->${promotion.discPrice}원</td> <!--discPrice : 할인이 적용된 가격-->
                                <td>${promotion.startDate} ~ ${promotion.endDate}</td>
                                <td>${promotion.startTime} ~ ${promotion.endTime}</td>
                                <td>${promotion.mentStartTime} ~ ${promotion.mentEndTime}</td>
                                <td>${promotion.interval}분</td>
                                <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="openAdditionalContent(${promotion.boolAddCond},${promotion.boolAddDesc},
                                '${promotion.addDiscCond}','${promotion.addMenuDesc}','${promotion.ment}')">보기</button></td>
                                <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="loadUpdateContent(${promotion.menuPromoId},`kor`)">편집</button></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>

            <c:set var="listSize" value="${fn:length(all_promotion_list)}"/>
            <div style="text-align: center;" >
                <c:if test="${listSize > 3}">
                    <button class="rotated-arrow" id="all_list_btn" type="button">
                        <p style="width: 50px; height: 100%;">더 보기</p>
                        <!-- <image style="width: 20px; height: 100%" src="${pageContext.request.contextPath}/assets/images/pngwing.com.svg"/> -->
                    </button>
                </c:if>
            </div>
        </div>

        <div id="allListModal" class="custom-modal" tabindex="-1" role="dialog">

                <div class="all-list-modal-content">

                    <div class="modal-header">
                        <button type="button" style="margin-left: auto" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <table class="table all-promotion-list">
                            <thead>
                                <tr>
                                    <th>상태</th>
                                    <th>품목</th>
                                    <th>제품</th>
                                    <th>정가</th>
                                    <th>할인가</th>
                                    <th>행사기간</th>
                                    <th>행사시간</th>
                                    <th>멘트발화시간</th>
                                    <th>발화 간격</th>
                                    <th>조건 및 멘트</th>
                                    <th>편집</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${all_promotion_list}" var="promotion" varStatus="status">
                                    <tr>
                                        <td>

                                            <div class="my_dropdown">
                                                <a class="dropbtn"><span id="selectedItem"> ${promotion.status} </span></a>
                                                <c:choose>
                                                    <c:when test="${promotion.status eq '예정'}"></c:when>
                                                    <c:otherwise>
                                                        <div class="dropdown-content">
                                                            <a class ="dropdown-item" data-value="진행중" onclick= "changeStatus(${promotion.menuPromoId}, `진행중`)">진행중</a>
                                                            <a class ="dropdown-item" data-value="중지" onclick= "changeStatus(${promotion.menuPromoId}, `중지`)">중지</a>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                        </td>
                                        <td>${promotion.category}</td>
                                        <td>${promotion.menu}</td>
                                        <td>${promotion.price}원</td>
                                        <td><!--<span style="color: red">(-${promotion.discVal})&nbsp</span>-->${promotion.discPrice}원</td>
                                        <td>${promotion.startDate} ~ ${promotion.endDate}</td>
                                        <td>${promotion.startTime} ~ ${promotion.endTime}</td>
                                        <td>${promotion.mentStartTime} ~ ${promotion.mentEndTime}</td>
                                        <td>${promotion.interval}분</td>
                                        <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="openAdditionalContent(${promotion.boolAddCond},${promotion.boolAddDesc},'${promotion.addDiscCond}','${promotion.addMenuDesc}','${promotion.ment}')">보기</button></td>
                                        <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="loadUpdateContent(${promotion.menuPromoId},`kor`)">편집</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
        </div>

        <div id="additionalContentModal" class="modal-addc">
            <div class="modal-popup-addc">
                <ul>
                    <li><span>할인 조건 추가</span><span id="modal-additional-cond">내용1</span></li>
                    <li><span>제품 소개</span><span id="modal-product-desc">내용2</span></li>
                    <li><span>AI 멘트 생성</span><span id="modal-ment">내용3</span></li>
                </ul>
                <div id="modal-footer">
                <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">닫기</button>
                </div>
            </div>
        </div>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/beverageDiscount.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



    <script>

        document.addEventListener('DOMContentLoaded', function() {
            
            const allListModal = document.querySelector('#allListModal');
            const allListBtn = document.querySelector('#all_list_btn');

            if (allListBtn && allListModal) {  // 두 요소가 모두 존재하는지 확인
                allListBtn.addEventListener('click', function() {
                    $('#allListModal').show();
                });
            } else {
                console.error('Element #all_list_btn or #allListModal not found');
            }
        });


    </script>
</body>
</html>