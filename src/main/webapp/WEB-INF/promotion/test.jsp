<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Promotions</title>

    <style>
        .custom-product-search-modal{
            position: fixed;
            top:0;
            left:0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto;
            z-index: 1000;

        }

        .custom-product-search-modal .custom-modal-content{
            border: 1px;
            border-color: none;
            border-radius: 15px;
            background-color: white;
            position: relative;
            align: center;
            margin: 0 auto;
            top: 50px;
            width: 500px;
            height: 600px;
            font-size: 1rem;
            overflow-y: auto;

        }

        .custom-product-search-modal .custom-modal-content .custom-modal-header{
            margin: 0 auto;
            display: flex;
            border-bottom: 2px solid;
            justify-content:center;

        }

        .custom-modal-header #input{
            position:relative;
            width: 100%;
            max-width: 200px;
            border-radius: 20px;
            border: 1px solid #ccc;
        }

        .custom-product-search-modal .custom-modal-content .custom-search-box{
            display: flex;
            align-items: center;
            justify-content: center;


        }

        .custom-product-search-modal .custom-grid-container{
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-template-rows: 1fr 1fr;
            list-style: none;
        }

        .custom-product-search-modal .custom-grid-container .item{
            border: none;
            text-align: center;
            padding: 10px;
        }

        .custom-product-search-modal .custom-grid-container .custom-select-btn{
            margin: 0 auto;
            text-align: center;
            justify-content: center;
        }





    </style>
</head>
<body>

    <div id="custom-search-modal" class="custom-product-search-modal" role="dialog" style="display: block;">
        <div class="custom-modal-content">

            <div class="custom-modal-header">
                <h5 class="custom-modal-title"><strong>제품 검색</strong></h5>
                <button type="button" class="custom-close-btn">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="custom-modal-body">

                <div class="custom-search-box">
                    <form action="" method="get">
                        <input type="text" placeholder="검색" id="custom-search-input"><i class="icon-search"></i>
                        <button class="custom-modal-search-button" type="button">
                            <i class="icon-search"></i>
                        </button>
                    </form>
                </div>

                <div class="custom-item-list">
                    <ul class="custom-grid-container">
                        <li class="item" id="item1">
                            <img id="custom-product-img" src="https://via.placeholder.com/150" alt="샘플이미지">
                            <p id="custom-product-name">음료1</p>
                            <p id="custom-product-price" value=3000>3,000원</p>
                        </li>
                        <li class="item" id="item2">
                            <img id="custom-product-img" src="https://via.placeholder.com/150" alt="샘플이미지">
                            <p id="custom-product-name">음료10</p>
                            <p id="custom-product-price" value=4000>4,000원</p>
                        </li>
                        <li class="item" id="item3">
                            <img id="custom-product-img" src="https://via.placeholder.com/150" alt="샘플이미지">
                            <p id="custom-product-name">음료100</p>
                            <p id="custom-product-price" value=5000>5,000원</p>
                        </li>
                    </ul>

                    <button type="button" class="custom-select-btn">선택</button>
                </div>


            </div>
        </div>
    </div>



</body>
</html>