<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>
</head>
<body>
    <div class="card-body">
        <h4 class="card-title">Completed Promotion</h4>
        <div class="completed-table">
            <table class="table completed-promotion-list">
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
                    <c:forEach items="${completed_promotion_list}" var="promotion" varStatus="status">
                        <c:if test="${status.index<5}">
                            <tr>
                                <td>${promotion.status}</td>
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

            <c:set var="listSize" value="${fn:length(completed_promotion_list)}"/>
            <div style="text-align: center;" >
                <c:if test="${listSize > 100}">
                    <button class="rotated-arrow" type="button" onclick="openAllListModal()">
                        <image style="width: 20px; height: 100%" src="${pageContext.request.contextPath}/assets/images/pngwing.com.svg"/>
                    </button>
                </c:if>
            </div>
        </div>

        <div id="additionalContentModal" class="modal-addc">
            <div class="modal-popup-addc">
                <ul>
                    <li><span>Condition</span><span id="modal-additional-cond">Content 1</span></li>
                    <li><span>Description</span><span id="modal-product-desc">Content 2</span></li>
                    <li><span>AI Message</span><span id="modal-ment">Content 3</span></li>
                </ul>
                <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">Close</button>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>

</body>
</html>