<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>
</head>
<body>
    <div class="card-body">

        <h4 class="card-title">Product Promotion</h4>
        <div id="all-table">
            <table class="table all-promotion-list">
                <thead>
                    <tr>
                        <th>Status</th>
                        <th>Category</th>
                        <th>Product</th>
                        <th>Regular Price</th>
                        <th>Discounted Price</th>
                        <th>Promotion Period</th>
                        <th>Promotion Time</th>
                        <th>Speech Time</th>
                        <th>Frequency</th>
                        <th>Conditions</th>
                        <th>Edit</th>
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
                                            <c:when test="${promotion.status eq 'Scheduled'}"></c:when>
                                            <c:otherwise>
                                                <div class="dropdown-content">
                                                    <a class ="dropdown-item" data-value="In Progress" onclick="changeStatus(${promotion.menuPromoId}, 'In Progress')">In Progress</a>
                                                    <a class ="dropdown-item" data-value="Stopped" onclick="changeStatus(${promotion.menuPromoId}, 'Stopped')">Stopped</a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                </td>
                                <td>${promotion.category}</td>
                                <td>${promotion.menu}</td>
                                <td>${promotion.price} KRW</td>
                                <td><!--<span style="color: red">(-${promotion.discVal})&nbsp</span>-->${promotion.discPrice}KRW</td>
                                <td>${promotion.startDate} ~ ${promotion.endDate}</td>
                                <td>${promotion.startTime} ~ ${promotion.endTime}</td>
                                <td>${promotion.mentStartTime} ~ ${promotion.mentEndTime}</td>
                                <td>${promotion.interval} min</td>
                                <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="openAdditionalContent(${promotion.boolAddCond},${promotion.boolAddDesc},'${promotion.addDiscCond}','${promotion.addMenuDesc}','${promotion.ment}')">View</button></td>
                                <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="loadUpdateContent(${promotion.menuPromoId}, `eng`)">Edit</button></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>

            <c:set var="listSize" value="${fn:length(all_promotion_list)}"/>
            <div style="text-align: center;" >
                <c:if test="${listSize > 3}">
                    <button class="rotated-arrow" id="all_list_btn" type="button">
                        <p style="width: 50px; height: 100%;">More</p>
                        <!-- <image style="width: 20px; height: 100%" src="${pageContext.request.contextPath}/assets/images/pngwing.com.svg"/> -->
                    </button>
                </c:if>
            </div>
        </div>

        <div id="allListModal" class="custom-modal" tabindex="-1" role="dialog">
            <div class="all-list-modal-content">
                <div class="modal-header">
                    <button type="button" style="margin-left: auto" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <table class="table all-promotion-list">
                        <thead>
                            <tr>
                                <th>Status</th>
                                <th>Category</th>
                                <th>Product</th>
                                <th>Regular Price</th>
                                <th>Discounted Price</th>
                                <th>Promotion Period</th>
                                <th>Promotion Time</th>
                                <th>Speech Time</th>
                                <th>Frequency</th>
                                <th>Conditions</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${all_promotion_list}" var="promotion" varStatus="status">
                                <tr>
                                    <td>
                                        <div class="my_dropdown">
                                            <a class="dropbtn"><span id="selectedItem"> ${promotion.status} </span></a>
                                            <c:choose>
                                                <c:when test="${promotion.status eq 'Scheduled'}"></c:when>
                                                <c:otherwise>
                                                    <div class="dropdown-content">
                                                        <a class ="dropdown-item" data-value="In Progress" onclick="changeStatus(${promotion.menuPromoId}, 'In Progress')">In Progress</a>
                                                        <a class ="dropdown-item" data-value="Stopped" onclick="changeStatus(${promotion.menuPromoId}, 'Stopped')">Stopped</a>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <td>${promotion.category}</td>
                                    <td>${promotion.menu}</td>
                                    <td>${promotion.price} KRW</td>
                                    <td><!--<span style="color: red">(-${promotion.discVal})&nbsp</span>-->${promotion.discPrice}KRW</td>
                                    <td>${promotion.startDate} ~ ${promotion.endDate}</td>
                                    <td>${promotion.startTime} ~ ${promotion.endTime}</td>
                                    <td>${promotion.mentStartTime} ~ ${promotion.mentEndTime}</td>
                                    <td>${promotion.interval} min</td>
                                    <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="openAdditionalContent(${promotion.boolAddCond},${promotion.boolAddDesc},'${promotion.addDiscCond}','${promotion.addMenuDesc}','${promotion.ment}')">View</button></td>
                                    <td><button type="button" class="btn btn-outline-primary btn-sm" onclick="loadUpdateContent(${promotion.menuPromoId}, `eng`)">Edit</button></td>
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
                    <li><span>Condition</span><span id="modal-additional-cond">Content 1</span></li>
                    <li><span>Description</span><span id="modal-product-desc">Content 2</span></li>
                    <li><span>AI Message</span><span id="modal-ment">Content 3</span></li>
                </ul>
                <div id="modal-footer">
                    <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">Close</button>
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
