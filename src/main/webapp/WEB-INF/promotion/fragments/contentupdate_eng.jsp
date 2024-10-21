<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>


    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

</head>
<body>
    <div class="card-body">
        <h4 class="card-title">Edit Promotion</h4>

        <form action="/api/promotion-discount/update?lang=eng" method="post">
            <div class="table-responsive first-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Category</th>
                        <th>Product</th>
                        <th>Regular Price (Discounted Price)</th>
                        <th>Discount Value (KRW)</th>
                        <th>Promotion Period</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem"> ${thepromo.category} </span></a>
                                <!-- <div class="dropdown-content">
                                    <a class="dropdown-item" data-value="Beverage">음료</a> 
                                    <a class="dropdown-item" data-value="Bakery">베이커리</a>
                                    <a class="dropdown-item" data-value="Cake">케이크</a>
                                    <a class="dropdown-item" data-value="Set Product">세트상품</a>
                                </div> -->
                            </div>

                            <input type="hidden" id="categoryInput" name="menuPromoId" value="${thepromo.menuPromoId}">
                            <!-- category hidden input -->
                            <input type="hidden" id="categoryInput" name="category" value="BEVERAGE">


                        </td>
                        <td>
                            <div class="input-container">
                                <button type="button" class="search-button custom-input form-control"
                                        id="product_search_btn">${thepromo.menu}
                                        <!-- <i class="icon-search" onclick="openMenuModal()"></i> -->
                                </button>
                                <input type="hidden" id="menunameInput" name="menu" value="${thepromo.menu}">

                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                <p id="productPrice">${thepromo.price} KRW</p>
                                <input type="hidden" id="priceInput" name="price" value="${thepromo.price}">

                                <p id="discountPrice">(${thepromo.discPrice} KRW)</p>
                                <input type="hidden" id="discPriceInput" name="discPrice" value="${thepromo.discPrice}">

                            </div>
                        </td>
                        <td>
                            <div class="discount-section">
                                -&nbsp<input type="number" class="custom-input form-control"
                                       id="discountPriceInput" name="discVal" value="${thepromo.discVal}" placeholder="${thepromo.discVal}">

                                <button type="button" class="btn btn-outline-primary btn-sm"
                                        id="confirmDiscountPriceBtn" onclick="onclickConfirmDiscountPriceBtn()">Confirm
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="discount-section date">
                                <input type="date" class="custom-input form-control" id="startDate" name="startDate" value="${thepromo.startDate}">

                                &nbsp~&nbsp
                                <input type="date" class="custom-input form-control" id="endDate" name="endDate" value="${thepromo.endDate}">
                                <label class="custom-label">

                                    <input type="checkbox" name="boolIsAlways" is="isAlwaysCheckbox" ${thepromo.boolIsAlways eq 'true' ? 'checked' : ''}>
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

                                <input id="eventStartTime" name="startTime" value="${thepromo.startTime}" type="time" class="timepicker text-center">
                                &nbsp~&nbsp
                                <input id="eventEndTime" name="endTime" value="${thepromo.endTime}" type="time" class="timepicker text-center"> 
                                

                                <!-- <label class="custom-label" for="sameTimeCheckbox">
                                    <input type="checkbox" id="sameTimeCheckbox" name="boolEqlStoreOpr" ${thepromo.boolEqlStoreOpr eq 'true' ? 'checked' : ''}>
                                    &nbspSame as store hours
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="discount-section time">

                                <input id="mentStartTime" name="mentStartTime" value="${thepromo.mentStartTime}"  type="time" class="timepicker text-center">

                                &nbsp~&nbsp

                                <input id="mentStartTime" name="mentEndTime" value="${thepromo.mentEndTime}"  type="time" class="timepicker text-center">

                                <!-- <label class="custom-label">
                                    <input type="checkbox" name"boolEqlEventStart" ${thepromo.boolEqlEventStart eq 'true' ? 'checked' : ''}>
                                    &nbspSame as event time
                                </label> -->
                            </div>
                        </td>
                        <td>
                            <div class="my_dropdown">
                                <a class="dropbtn"><span id="selectedItem">${thepromo.interval}-min Interval</span></a>
                                <div class="dropdown-content">
                                    <a class="dropdown-item" data-value="5-min Interval">5-min Interval</a>
                                    <a class="dropdown-item" data-value="10-min Interval">10-min Interval</a>
                                    <a class="dropdown-item" data-value="15-min Interval">15-min Interval</a>
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
                            <th>Add Discount Condition</th>
                            <th>Product Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div class="discount-section radio">
                                    <label class="custom-label first-label" for="discountOptionNone">
                                        <input type="radio" name="boolAddCond"
                                               id="discountOptionNone"
                                               onchange="changeAdditionalOption(false, 'addCondId')" 
                                               value="false" ${thepromo.boolAddCond eq 'false' ? 'checked' : ''}>
                                        None
                                    </label>
                                    <label class="custom-label" for="discountOptionYes">
                                        <input type="radio" name="boolAddCond"
                                               id="discountOptionYes"
                                               onchange="changeAdditionalOption(true, 'addCondId')" 
                                               value="true" ${thepromo.boolAddCond eq 'true' ? 'checked' : ''}>
                                        Yes
                                    </label>
                                    <!-- <input type="hidden" id="isAddCondInput" value="false" name="boolAddCond"> -->
                                    <input type="text" class="custom-input form-control" id="addCondId" value="${thepromo.addDiscCond}" name="addDiscCond" ${thepromo.boolAddCond eq 'true' ? '' : 'disabled'}>
                                </div>
                            </td>
                            <td>
                                <div class="discount-section radio">
                                    <label class="custom-label first-label" for="descriptionNone">
                                        <input type="radio" name="boolAddDesc"
                                               id="descriptionNone"
                                               onchange="changeAdditionalOption(false, 'addMenuDescId')" 
                                               value="false" ${thepromo.boolAddDesc eq 'false' ? 'checked' : ''}>
                                        None
                                    </label>
                                    <label class="custom-label" for="descriptionYes">
                                        <input type="radio" name="boolAddDesc"
                                               id="descriptionYes"
                                               onchange="changeAdditionalOption(true, 'addMenuDescId')" 
                                               value="true" ${thepromo.boolAddDesc eq 'true' ? 'checked' : ''}>
                                        Yes
                                    </label>
                                    <!-- <input type="hidden" id="isAddDescInput" value="false" name="boolAddDesc"> -->
                                    <input type="text" class="custom-input form-control" id="addMenuDescId" value="${thepromo.addMenuDesc}" name="addMenuDesc" ${thepromo.boolAddDesc eq 'true' ? '' : 'disabled'}>
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
                            <th>Test Progress<span class="info">* Check how the CafeBot speaks!</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <!-- <input type="hidden" class="ment-text-input" name="ment" id="ment-text"> -->
                             <!-- textarea with actual content, not using 'value' attribute -->
                            <textarea id="ment-textarea" name="ment">${thepromo.ment}</textarea>
                            <div class="button-container">
                                <button type="button" onclick="createMent()" class="btn btn-outline-light btn-sm">Generate Message
                                </button>
                                <!-- <button type="button" class="btn btn-outline-light btn-sm">Load
                                </button> -->
                                <button type="button" class="btn btn-outline-light btn-sm">Edit
                                </button>
                            </div>
                        </td>
                        <td class="test">
                            <button type="button" class="btn btn-primary">CafeBot Promotion Test
                            </button>
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="form-buttons">
                
                <button type="submit" class="btn btn-primary">
                    Edit
                </button>

                <button type="button" id="menupromotion-delete-btn" class="btn btn-outline-primary">
                    <a href="/api/promotion-discount/delete?id=${thepromo.menuPromoId}&lang=eng" 
                        onclick="return confirm('Are you sure you want to delete this promotion?');" style="text-decoration: none;">
                        Delete
                    </a>
                </button>

            </div>
        </form>
        </div>

        <div id="testmodaal" disabled>

        </div>


    </div>

    <script>

        $(document).ready(function() {
            // Timepicker initialization with 30-minute interval and 24-hour format
            console.log("timepicker initialized");
            $('.timepicker').timepicker({
                timeFormat: 'HH:mm',      // 24-hour format
                interval: 30,             // 30-minute intervals
                minTime: '00:00',         // Start time
                maxTime: '23:59',         // End time
                defaultTime: '00:00',     // Default start time
                startTime: '00:00',       // Timepicker start time
                dynamic: false,           // Don't adjust for dynamic input
                dropdown: true,           // Use dropdown menu
                scrollbar: true           // Allow scroll in dropdown
            });
        });

    
    </script>

</body>
</html>
