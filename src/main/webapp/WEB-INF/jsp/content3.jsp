<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>
</head>
<body>
    <div id="content">
        =============

        <table class="table completed-promotion-list">
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
                    <th>빈도수</th>
                    <th>조건 및 멘트</th>
                    <th>편집</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${completed_promotion_list}" var="promotion">
                    <tr>
                        <td>${promotion.status}</td>
                        <td>${promotion.category}</td>
                        <td>${promotion.menu}</td>
                        <td>${promotion.price}</td>
                        <td>${promotion.discPrice}</td>
                        <td>${promotion.startDate}</td>
                        <td>${promotion.endDate}</td>
                        <td>${promotion.startTime}</td>
                        <td>${promotion.endTime}</td>
                        <td>${promotion.mentStartTime}</td>
                        <td>${promotion.mentEndTime}</td>
                        <td>${promotion.freq}</td>
                        <td>${promotion.ment}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</body>
</html>
