<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotion Registration</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">


</head>
<body>
    <div class="card-body">
        <h4 class="card-title">Register Promotion</h4>

        <form action="/api/promotion-discount/register?lang=eng" method="post" id="promotionFormEng">
            <div class="table-responsive first-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Category</th>
                        <th>Product</th>
                        <th>Price(Discounted Price)</th>
                        <th>Discount(KRW)</th>
                        <th>Promotion Period</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem"> Select </span></a>
                                <div class="dropdown-content">
                                    <a class="dropdown-item" data-value="Beverage">Beverage</a>
                                    <a class="dropdown-item" data-value="Bakery">Bakery</a>
                                    <a class="dropdown-item" data-value="Cake">Cake</a>
                                    <a class="dropdown-item" data-value="Set Product">Set Product</a>
                                </div>
                            </div>

                            <!-- <input type="hidden" id="thiscategoryInput00" name="menuPromoId" value=1> -->
                            <!-- category hidden input -->
                            <input type="hidden" id="categoryInput" name="category" value="BEVERAGE">
                        </td>
                        <td>
                            <div class="input-container">
                                <!--<input type="text" class="custom-input form-control search" disabled>-->
                                <button type="button" class="search-button custom-input form-control"
                                        id="product_search_btn"><i
                                        class="icon-search" onclick="openMenuModal('eng')"></i>
                                </button>
                                <input type="hidden" id="menunameInput" name="menu">

                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                <p id="productPrice">6,000 KRW</p>
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
                                        id="confirmDiscountPriceBtn" onclick="onclickConfirmDiscountPriceBtn()">Confirm
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
                                    &nbspAlways
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
                        <th>Event Time</th>
                        <th>Speech Time</th>
                        <th>Frequency</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="discount-section time">
                                <input id="eventStartTime" name="startTime" type="time" class="timepicker text-center">

                                &nbsp~&nbsp

                                <input id="eventEndTime" name="endTime" type="time" class="timepicker text-center">

                                <!-- <label class="custom-label" for="sameTimeCheckbox">
                                    <input type="checkbox" id="sameTimeCheckbox" name="boolEqlStoreOpr">
                                    &nbspSame as store hours
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="discount-section time">

                                <input id="speechStartTime" name="mentStartTime" type="time" class="timepicker text-center">

                                &nbsp~&nbsp

                                <input id="speechEndTime" name="mentEndTime" type="time" class="timepicker text-center">
                                <!-- <label class="custom-label">
                                    <input type="checkbox" name="boolEqlEventStart">
                                    &nbspSame as event time
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem"> Select </span></a>
                                <div class="dropdown-content">
                                    <a class="dropdown-item" data-value="10-min Interval">10-min Interval</a>
                                    <a class="dropdown-item" data-value="20-min Interval">20-min Interval</a>
                                    <a class="dropdown-item" data-value="30-min Interval">30-min Interval</a>
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
                        <th>Add Discount Condition(Optional)</th>
                        <th>Product Description</th>
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
                                    None
                                </label>
                                <label class="custom-label" for="discountOptionYes">
                                    <input type="radio" name="boolAddCond"
                                        onchange="changeAdditionalOption(true, 'addCondId')" 
                                        id="discountOptionYes" value="true">
                                    Yes
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
                                    None
                                </label>
                                <label class="custom-label" for="descriptionYes">
                                    <input type="radio" name="boolAddDesc"
                                        onchange="changeAdditionalOption(true, 'addMenuDescId')" 
                                        id="descriptionYes" value="true">
                                    Yes
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
                            <th>Generate AI CafeBot Message</th>
                            <!-- <th>Test Progress<span class="info">* Check how the CafeBot speaks!</span>
                            </th> -->
                        </tr> 
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                           
                            <textarea id="ment-textarea" name="ment"></textarea>
                            <div class="button-container">
                                <button type="button" onclick="createMent('en')" class="btn btn-outline-light btn-sm">Generate Message
                                </button>
                                <!-- <button type="button" class="btn btn-outline-light btn-sm">Load
                                </button> -->
                                <button type="button" class="btn btn-outline-light btn-sm">Edit
                                </button>
                            </div>
                        </td>
                        <td class="test">
                            <!-- <button type="button" class="btn btn-primary">CafeBot Promotion Test
                            </button> -->
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="form-buttons">
                <!-- Delete button appears when 'Edit' button is clicked -->
                <button type="submit" class="btn btn-primary">Register
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
