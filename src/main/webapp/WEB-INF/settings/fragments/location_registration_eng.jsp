<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Cafebot Location Registration</h4>
                <form action="/settings/dasom-locations/register?lang=eng" method="post" name="updateForm">
                 <div class = "dasom-location-register-wrapper">
                    <div class="dasom-location-radio">
                        <p class="title">1. set the location of the cafebot within the cafe</p>
                        <label class="custom-label first-label" for="entrance">
                            <input type="radio" name="location"
                                   id="entrance" value="Near Entrance" checked>
                            Near Entrance
                        </label>
                        <label class="custom-label" for="counter">
                            <input type="radio" name="location"
                                   id="counter" value="Near Counter">
                            Near Counter
                        </label>
                        <label class="custom-label" for="pickupTable">
                            <input type="radio" name="location"
                                   id="pickupTable" value="Near PickupTable">
                            Near PickupTable
                        </label>
                        <label class="custom-label" for="goodsDisplay">
                            <input type="radio" name="location"
                                   id="goodsDisplay" value="Near the goods stand">
                                   Near the goods stand
                        </label>
                        <label class="custom-label" for="other">
                            <input type="radio" name="location"
                                   id="other" value="Other">
                                   Other
                        </label>
                    </div>

                    <div class="dasom-location-container">
                        <div class="dasom-location-register">
                            <p class="title">2. If there are products for sale, please select a number.</p>
                            <div class="main-content">
                                <ul class="location-list">
                                    <li>
                                        <p>①</p>
                                        <p>Left</p>
                                    </li>
                                    <li>
                                        <i class="material-symbols-outlined">robot_2</i>
                                        <p>Cafebot</p>
                                    </li>
                                    <li>
                                        <p>④</p>
                                        <p>Right</p>
                                    </li>
                                    <li>
                                        <p>②</p>
                                        <p>Left Front</p>
                                    </li>
                                    <li>
                                        <p>③</p>
                                        <p>Front</p>
                                    </li>
                                    <li>
                                        <p>⑤</p>
                                        <p>Right Front</p>
                                    </li>
                                </ul>
                            </div>
                        </div>


                        <div class="dasom-location-input">
                            <p class="title">3. Please select which product is being deployed.<br> (Select up to 2)</p>
                            <div class="location-group">
                                <label for="input1">① Left</label>

                                <c:set var="inputValue" value="" />

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown1" name="leftSide" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown1">
                                    <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="Bakery">Bakery</li>
                                        <li class="nav-item dropdown" data-value="Dessert">Dessert</li>
                                        <li class="nav-item dropdown" data-value="Sandwich">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="Salad">Salad</li>
                                        <li class="nav-item dropdown" data-value="Counter">Counter</li>
                                        <li class="nav-item dropdown" data-value="Dairy Products">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="bottled drink">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="Cake">Cake</li>
                                        <li class="nav-item dropdown" data-value="window seat">window seat</li>
                                        <li class="nav-item dropdown" data-value="Custom input" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input4">② Left Front</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown4" name="leftFront" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown4">
                                     <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="Bakery">Bakery</li>
                                        <li class="nav-item dropdown" data-value="Dessert">Dessert</li>
                                        <li class="nav-item dropdown" data-value="Sandwich">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="Salad">Salad</li>
                                        <li class="nav-item dropdown" data-value="Counter">Counter</li>
                                        <li class="nav-item dropdown" data-value="Dairy Products">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="bottled drink">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="Cake">Cake</li>
                                        <li class="nav-item dropdown" data-value="window seat">window seat</li>
                                        <li class="nav-item dropdown" data-value="Custom input" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input5">③ Front</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown5" name="front" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown5">
                                     <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="Bakery">Bakery</li>
                                        <li class="nav-item dropdown" data-value="Dessert">Dessert</li>
                                        <li class="nav-item dropdown" data-value="Sandwich">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="Salad">Salad</li>
                                        <li class="nav-item dropdown" data-value="Counter">Counter</li>
                                        <li class="nav-item dropdown" data-value="Dairy Products">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="bottled drink">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="Cake">Cake</li>
                                        <li class="nav-item dropdown" data-value="window seat">window seat</li>
                                        <li class="nav-item dropdown" data-value="Custom input" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="location-group">
                                <label for="input3">④ Right</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown3" name="rightSide" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown3">
                                     <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="Bakery">Bakery</li>
                                        <li class="nav-item dropdown" data-value="Dessert">Dessert</li>
                                        <li class="nav-item dropdown" data-value="Sandwich">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="Salad">Salad</li>
                                        <li class="nav-item dropdown" data-value="Counter">Counter</li>
                                        <li class="nav-item dropdown" data-value="Dairy Products">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="bottled drink">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="Cake">Cake</li>
                                        <li class="nav-item dropdown" data-value="window seat">window seat</li>
                                        <li class="nav-item dropdown" data-value="Custom input" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>


                            <div class="location-group">
                                <label for="input6">⑤ Right Front</label>

                                <input type="text"
                                       class="custom-input form-control dropdown-bordered dropdown-toggle"
                                       id="inputdropdown6" name="rightFront" disabled
                                       data-bs-toggle="dropdown"
                                       aria-expanded="false"
                                       value="">
                                <div class="dropdown-menu dropdown-menu-list navbar-dropdown preview-list pb-0 pt-0"
                                     aria-labelledby="messageDropdown" id="dropdown6">
                                     <ul class="dropdown-options">
                                        <li class="nav-item dropdown" data-value="Bakery">Bakery</li>
                                        <li class="nav-item dropdown" data-value="Dessert">Dessert</li>
                                        <li class="nav-item dropdown" data-value="Sandwich">Sandwich</li>
                                        <li class="nav-item dropdown" data-value="Salad">Salad</li>
                                        <li class="nav-item dropdown" data-value="Counter">Counter</li>
                                        <li class="nav-item dropdown" data-value="Dairy Products">Dairy Products</li>
                                        <li class="nav-item dropdown" data-value="bottled drink">bottled drink</li>
                                        <li class="nav-item dropdown" data-value="Cake">Cake</li>
                                        <li class="nav-item dropdown" data-value="window seat">window seat</li>
                                        <li class="nav-item dropdown" data-value="Custom input" onclick="openAdditionalContent()">Custom input</li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="form-buttons">
                        <!-- 삭제 버튼은 제품 할인 영역에서 "수정" 버튼 눌렀을 시 생김
                        <button type="button" class="btn btn-outline-primary">삭제
                        </button>-->
                        <button type="submit" class="btn btn-primary">Save
                        </button>
                    </div>
                </div>
               </form>
            </div>
            <input type="text" hidden id="temp-input">

            <div id="userInputModal" class="modal-addc">
                <div class="modal-popup-addc">
                    <ul>
                        <li><span>Direct input</span><span id="modal-product-desc" style="size: 15px;">**directly enter the product you wish to introduce.</span></li>
                        <li><span><input type="text" id ="user-input"></span></li>

                    </ul>
                    <div id="modal-footer">
                    <button type="button" class="close-btn-addc" onclick="closeAdditionalContent()">Submit</button>
                    </div>
                </div>
            </div>


        </div>
    </body>
</html>