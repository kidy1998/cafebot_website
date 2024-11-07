document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.dropbtn').addEventListener('click', function() {
        document.querySelector('.dropdown-content').classList.toggle('show');
    });
});



function testFunc(){

    document.querySelector('.dropbtn').addEventListener('click', function() {
        document.querySelector('.dropdown-content').classList.toggle('show');
    });

    // 검색 아이콘을 클릭하면 모달을 표시=
    const closeBtn = document.getElementById(".btn-secondary");

    const allListBtn = document.querySelector('#all_list_btn');
    if (allListBtn!=null){
        allListBtn.addEventListener('click', function(){

            $('#allListModal').show();

            const alllistcloseButton = document.querySelector('#allListModal .close');

            alllistcloseButton.addEventListener('click', function(){
                $('#allListModal').hide();
            });

        });
    }

    var closeModalBtn = document.getElementById('closeModalBtn');
    if (closeModalBtn) {
        closeModalBtn.addEventListener('click', function() {
            $('#myModal').modal('hide');
        });
    } else {
        console.error('Close modal button not found.');
    }
}


document.addEventListener('DOMContentLoaded', function() {


      // 드롭다운 메뉴를 클릭할 때 show 클래스를 토글하는 함수
      function toggleDropdown(event) {

        const dropdownToggle = event.target.closest('.dropdown-toggle');
        if (!dropdownToggle) return;

        // dropdownToggle과 같은 부모 요소를 공유하는 dropdown-menu를 찾기
        const dropdownMenu = dropdownToggle.nextElementSibling;
        console.log(dropdownMenu);

        // dropdownMenu가 존재하고, classList를 사용할 수 있는지 확인
        if (dropdownMenu && dropdownMenu.classList.contains('dropdown-menu')) {
            
            // 다른 열려있는 드롭다운 메뉴를 닫기 (optional: 페이지에서 하나만 열리도록 할 때)
            document.querySelectorAll('.dropdown-menu.show').forEach(function(menu) {
                if (menu !== dropdownMenu) {
                    menu.classList.remove('show');
                }
            });

            // dropdownMenu에 show 클래스를 추가하거나 제거
            dropdownMenu.classList.toggle('show');

        } else {
            console.error('Dropdown menu not found or does not have the correct class.');
        }

        // 이벤트가 전파되지 않도록 방지 (드롭다운 외부를 클릭하면 닫히도록)
        event.stopPropagation();
    }



    // 전역 클릭 핸들러 (드롭다운 외부 클릭 시 닫기)
    document.addEventListener('click', function(event) {
        const isClickInside = event.target.closest('.dropdown-toggle, .dropdown-menu');
        if (!isClickInside) {
            document.querySelectorAll('.dropdown-menu.show').forEach(function(menu) {
                menu.classList.remove('show');
            });
        }
    });

    // 드롭다운 토글 요소에 클릭 이벤트 추가
    document.querySelectorAll('.dropdown-toggle').forEach(function(dropdown) {
        dropdown.addEventListener('click', toggleDropdown);
    });

    // 드롭다운 항목 클릭 시 선택된 텍스트로 업데이트하는 코드
    document.querySelectorAll('.dropdown-item').forEach(function(item) {

        item.addEventListener('click', function() {
            var selectedText = this.getAttribute('data-value');
            var parentDropdown = this.closest('.dropdown-content').previousElementSibling;
            parentDropdown.querySelector('span').textContent = selectedText;



            if (parentDropdown.querySelector('span').textContent !== "선택"){

                console.log("선택");

                if (selectedText=="음료"){
                    document.getElementById('categoryInput').value="BEVERAGE";
                }
                else if(selectedText=="베이커리"){
                   document.getElementById('categoryInput').value="BAKERY";
                }
                else if(selectedText=="케이크"){
                   document.getElementById('categoryInput').value="CAKE";
                }
                else if(selectedText=="세트상품"){
                   document.getElementById('categoryInput').value="SET";
                }
                else if(selectedText=="5분 간격"){
                   document.getElementById('intervalInput').value=5;
                }
                else if(selectedText=="10분 간격" || selectedText=="10-min Interval"){
                    console.log("선택");
                   document.getElementById('intervalInput').value=10;
                }
                else if(selectedText=="15분 간격"){
                   document.getElementById('intervalInput').value=15;
                }
                else if(selectedText=="20분 간격" || selectedText=="20-min Interval"){
                    console.log("선택");
                   document.getElementById('intervalInput').value=20;
                }
                else if(selectedText=="30분 간격" || selectedText=="30-min Interval"){
                    console.log("선택");
                   document.getElementById('intervalInput').value=30;
                }

            }
        });
    });

    testFunc();

    // 검색 input 박스에 입력된 값으로 리스트를 필터링합니다.
    function filterProductList(query) {
        // 모든 리스트 항목을 선택합니다.
        var items = document.querySelectorAll('#itemList li');
        // 각 항목을 반복하면서 필터링합니다.
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            var productNameId = 'productName' + item.id.replace('item', '');
            var productName = document.getElementById(productNameId).innerText;
            // 검색어가 없으면 모든 항목을 표시합니다.
            if (query === '') {
                item.style.display = '';
            } else if (productName.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
                // 제품명이 입력된 값과 일치하면 항목을 표시합니다.
                item.style.display = '';
            } else {
                // 일치하지 않으면 항목을 숨깁니다.
                item.style.display = 'none';
            }
        }
    }

    // // 할인가 입력 후 확인 버튼 클릭 시 할인가 영역에 할인된 금액 표시
    // document.getElementById('confirmDiscountPriceBtn').addEventListener('click', function() {

    //     var discountPriceInput = document.getElementById('discountPriceInput').value;
    //     var ProductPriceText = document.getElementById('productPrice').innerText;
    //     var discountPrice = parseInt(ProductPriceText.replace('원', '').replace(',', ''));

    //     if (!isNaN(discountPriceInput) && discountPriceInput.trim() !== '') {
    //         var discountedPrice = discountPrice-discountPriceInput;
    //         document.getElementById('discountPrice').innerText = `(${discountedPrice.toLocaleString()}원)`;
    //         document.getElementById('discountPriceInput').value=discountedPrice;

    //     }
    // });

});

function onclickConfirmDiscountPriceBtn() {
    // 할인할 금액을 숫자로 추출
    var discountValue = parseInt(document.getElementById('discountPriceInput').value, 10);
    console.log("Entered discount value: ", discountValue); // 디버그용 콘솔 로그

    // 제품 가격을 숫자로 추출
    var productPriceText = document.getElementById('productPrice').innerText;
    var productPrice = parseInt(productPriceText.replace('원', '').replace(',', ''), 10);
    console.log("Product price: ", productPrice); // 디버그용 콘솔 로그

    if (!isNaN(discountValue) && discountValue >= 0 && discountValue < productPrice) {
        // 할인된 가격 계산
        var discountedPrice = productPrice - discountValue;
        console.log("Discounted price: ", discountedPrice); // 디버그용 콘솔 로그

        // 할인된 가격을 표시
        document.getElementById('discountPrice').innerText = `(${discountedPrice.toLocaleString()} 원)`;

        // 할인된 가격을 히든 필드에 설정
        document.getElementById('discPriceInput').value = discountedPrice;
    } else {
        Swal.fire({
            text: "유효한 할인 금액을 입력하세요!",
            position: 'top', // 원하는 위치로 설정
            confirmButtonText: '확인'
        });
    }
}




function reload(){
    var xhr = new XMLHttpRequest();
    console.log("reload function");

    xhr.open('GET','/api/promotion-discount/main',true);
    xhr.onload=function(){
        if(xhr.status >= 200 && xhr.status<400){
            console.error("제품할인 메인 페이지 로드에 성공하였습니다.");
        }else{
            console.error("제품할인 메인 페이지 로드에 실패하였습니다");
        }
    };
    xhr.onerror = function(){
        console.error("[Menu Promotion Main Page - GET] Connection Error");
    }

    xhr.send();
}


function openMenuDetail(menuId){

    $.ajax({
        url: '/menu',
        method: 'GET',
        dataType: 'json',
        data: {
            id: menuId
        },
        success: function (response) {
            const menuDetailElement = document.getElementById('menuDetail');
            menuDetailElement.classList.remove('hidden');
            menuDetailElement.classList.add('visible');

            const modalContentElement = document.getElementById('modalContent');
            modalContentElement.classList.remove('visible');
            modalContentElement.classList.add('hidden');

            document.getElementById('intro').innerHTML = `
                <img src="${response.menuDetail.imgUrl}" style="width: 100px; height: 100%;">
                <p>${response.menuDetail.name}</p>
                <p>price &nbsp &nbsp ${response.menuDetail.price} KRW</p>
                <p>Cost Price &nbsp &nbsp ${response.menuDetail.basePrice} KRW</p>
                <br>
                <p>${response.menuDetail.desc}</p>
            `;

            // Populate ingredients table
            const ingredTableBody = document.getElementById('ingredTableBody');
            ingredTableBody.innerHTML = ''; // Clear existing content

            response.ingredList.forEach(function(ingred) {
                const row = `
                    <tr>
                        <td>
                            <p>${ingred.name} &nbsp;&nbsp; ${ingred.quant}${ingred.unit}</p>
                        </td>
                        <td>
                            <p>${ingred.price} KRW</p>
                        </td>
                    </tr>
                `;
                ingredTableBody.innerHTML += row;
            });

            // Optionally, show the ingredients table
            document.getElementById('ingred').style.display = 'block';


        },
        error: function () {
            Swal.fire({
                text: "검색중 오류가 발생했습니다.",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: '확인'
            });
        }
    });

}

function openMenuModal(lang){

    console.log("openModal");

    var xhr = new XMLHttpRequest();
    // 어떤 요청을 보내는지 객체 초기화
    xhr.open('GET','/menu/all?lang=' + lang , true);

    // 요청 성공할 경우 onload 설정
    xhr.onload=function(){
        if(xhr.status >= 200 && xhr.status<400){
            document.getElementById('testmodaal').innerHTML = xhr.responseText;

            testFunc();
            $('#productModal').show();

            $(document).ready(function () {
                $('.modal-search-button').on('click', function () {
                    var searchQuery = $('#searchInput').val();

                    $.ajax({
                        url: '/menu/search',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            search: searchQuery
                        },
                        success: function (response) {
                            var menuList = response.menu_list;

                            // Clear the existing items
                            $('#itemList').empty();

                            // Populate the itemList with the returned menu items
                            menuList.forEach(function(menu) {
                                var listItem = `
                                    <li class="item" id="item${menu.id}" menu-add-desc="${menu.desc}">
                                        <img style="width: 100px; height: 100%" src="${menu.imgUrl}" alt="샘플이미지">
                                        <p class="title" id="productName${menu.id}">${menu.name}</p>
                                        <p id="productPrice${menu.id}">${menu.price} KRW&nbsp &nbsp <button style ="border: none; background-color: white;" onclick="openMenuDetail(${menu.id})">
                                        </p>
                                    </li>`;
                                $('#itemList').append(listItem);
                            });

                            // 모달의 닫기 버튼을 클릭하면 모달을 숨김
                            const closeButton = document.querySelector('#productModal .close');

                            if(closeButton){
                                closeButton.addEventListener('click', function() {
                                    $('#productModal').hide();
                                });
                            }else{
                                console.error('Close modal button not found.');

                            }
                            // 제품 검색 모달의 제품 검색 기능
                            var images = document.querySelectorAll('#itemList img');
                            var selectedProductName = '';
                            var selectedProductPrice = '';

                            // 각각의 이미지에 클릭 이벤트를 추가합니다.
                            for (var i = 0; i < images.length; i++) {
                                images[i].addEventListener('click', function() {

                                // 클릭한 이미지의 부모 요소(li)의 id를 사용하여 p 태그를 찾음
                                var parentId = this.parentElement.id;
                                var item=document.getElementById(parentId);

                                var productNameId = 'productName' + parentId.replace('item', '');
                                var productNameElement = document.getElementById(productNameId);

                                var productPriceId = 'productPrice' + parentId.replace('item', '');
                                var productPriceElement = document.getElementById(productPriceId);

                                selectedProductName = productNameElement.innerText;
                                selectedProductPrice = productPriceElement.innerText;
                                openMenuDetail(parentId.replace('item',''));


                                // 더보기 버튼을 숨기기
                                var loadMoreBtn = document.getElementById('loadMoreBtn');
                                // loadMoreBtn.addEventListener('click',openMenuDetail,false);
                                // loadMoreBtn.idParam=parentId.replace('item','');

                                document.getElementById("descriptionYes").checked=true;

                                document.getElementById("addMenuDescId").disabled=false;

                                var menudesc=item.getAttribute("menu-add-desc");
                                var adddescitem=document.getElementById("addMenuDescId");
                                adddescitem.value="";
                                adddescitem.value=menudesc;

                                // 제품할인 등록 영역의 버튼에 제품명 설정
                                var discountProductBtn = document.getElementById('product_search_btn');
                                // discountProductBtn.innerText = selectedProductName;

                                var productPrice =  document.getElementById('productPrice');
                                productPrice.innerText = selectedProductPrice;

                                });
                            }

                           // 공통 함수 정의
                            function handleSelectButtonClick(isEnglish = false) {
                                var ProductSearchBtn = document.getElementById('product_search_btn');

                                // 아이콘 HTML 생성
                                var iconHtml = isEnglish 
                                    ? `<i class="icon-search" onclick="openMenuModal('eng')"></i>` 
                                    : `<i class="icon-search" onclick="openMenuModal()"></i>`;

                                if (ProductSearchBtn) {
                                    // 기존 내용 지우기
                                    $(ProductSearchBtn).empty();

                                    // 새로운 내용 추가
                                    $(ProductSearchBtn).append(selectedProductName);
                                    $(ProductSearchBtn).append(iconHtml);
                                }

                                // 입력 필드 업데이트
                                var menunameInput = document.getElementById('menunameInput');
                                if (menunameInput) {
                                    menunameInput.value = selectedProductName;
                                }

                                var ProductPrice = document.getElementById('productPrice');
                                if (ProductPrice) {
                                    ProductPrice.innerText = selectedProductPrice;
                                }

                                var priceInput = document.getElementById('priceInput');
                                if (priceInput) {
                                    priceInput.value = parseInt(selectedProductPrice.replace(/,/g, ''));
                                }

                                // 모달 창 닫기
                                $('#productModal').hide();
                            }

                            // `selectBtn` 이벤트 리스너 추가 (존재할 경우에만)
                            var selectBtn = document.getElementById('selectBtn');
                            if (selectBtn) {
                                selectBtn.addEventListener('click', function() {
                                    handleSelectButtonClick(false); // 한국어 모드
                                });
                            }

                            // `selectBtnEng` 이벤트 리스너 추가 (존재할 경우에만)
                            var selectBtnEng = document.getElementById('selectBtnEng');
                            if (selectBtnEng) {
                                selectBtnEng.addEventListener('click', function() {
                                    handleSelectButtonClick(true); // 영어 모드
                                });
                            }


                            // 검색 input 박스에 입력된 값으로 리스트를 필터링합니다.
                            function filterProductList(query) {
                                // 모든 리스트 항목을 선택합니다.
                                var items = document.querySelectorAll('#itemList li');
                                // 각 항목을 반복하면서 필터링합니다.
                                for (var i = 0; i < items.length; i++) {
                                    var item = items[i];
                                    var productNameId = 'productName' + item.id.replace('item', '');
                                    var productName = document.getElementById(productNameId).innerText;
                                    // 검색어가 없으면 모든 항목을 표시합니다.
                                    if (query === '') {
                                        item.style.display = '';
                                    } else if (productName.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
                                        // 제품명이 입력된 값과 일치하면 항목을 표시합니다.
                                        item.style.display = '';
                                    } else {
                                        // 일치하지 않으면 항목을 숨깁니다.
                                        item.style.display = 'none';
                                    }
                                }
                            }


                            testFunc();

                        },
                        error: function () {
                            Swal.fire({
                                text: "검색중 오류가 발생했습니다",
                                position: 'top', // 원하는 위치로 설정
                                confirmButtonText: '확인'
                            });
                        }
                    });
                });
            });


            // 모달의 닫기 버튼을 클릭하면 모달을 숨김
            const closeButton = document.querySelector('#productModal .close');

            if(closeButton){
                closeButton.addEventListener('click', function() {
                    $('#productModal').hide();
                });
            }else{
                console.error('Close modal button not found.');

            }
            // 제품 검색 모달의 제품 검색 기능
            var images = document.querySelectorAll('#itemList img');
            var selectedProductName = '';
            var selectedProductPrice = '';

            // 각각의 이미지에 클릭 이벤트를 추가합니다.
            for (var i = 0; i < images.length; i++) {
                images[i].addEventListener('click', function() {

                // 클릭한 이미지의 부모 요소(li)의 id를 사용하여 p 태그를 찾음
                var parentId = this.parentElement.id;
                var item=document.getElementById(parentId);

                var productNameId = 'productName' + parentId.replace('item', '');
                var productNameElement = document.getElementById(productNameId);

                var productPriceId = 'productPrice' + parentId.replace('item', '');
                var productPriceElement = document.getElementById(productPriceId);

                selectedProductName = productNameElement.innerText;
                selectedProductPrice = productPriceElement.innerText;

                openMenuDetail(parentId.replace('item',''));

                var loadMoreBtn = document.getElementById('loadMoreBtn');
              

                document.getElementById("descriptionYes").checked=true;

                document.getElementById("addMenuDescId").disabled=false;

                var menudesc=item.getAttribute("menu-add-desc");
                var adddescitem=document.getElementById("addMenuDescId");
                adddescitem.value="";
                adddescitem.value=menudesc;

                // 제품할인 등록 영역의 버튼에 제품명 설정
                var discountProductBtn = document.getElementById('product_search_btn');
                // discountProductBtn.innerText = selectedProductName;

                var productPrice =  document.getElementById('productPrice');
                productPrice.innerText = selectedProductPrice;

                });
            }

            // 공통 함수 정의
            function handleSelectButtonClick(isEnglish = false) {
                var ProductSearchBtn = document.getElementById('product_search_btn');

                // 아이콘 HTML 생성
                var iconHtml = isEnglish 
                    ? `<i class="icon-search" onclick="openMenuModal('eng')"></i>` 
                    : `<i class="icon-search" onclick="openMenuModal()"></i>`;

                if (ProductSearchBtn) {
                    // 기존 내용 지우기
                    $(ProductSearchBtn).empty();

                    // 새로운 내용 추가
                    $(ProductSearchBtn).append(selectedProductName);
                    $(ProductSearchBtn).append(iconHtml);
                }

                // 입력 필드 업데이트
                var menunameInput = document.getElementById('menunameInput');
                if (menunameInput) {
                    menunameInput.value = selectedProductName;
                }

                var ProductPrice = document.getElementById('productPrice');
                if (ProductPrice) {
                    ProductPrice.innerText = selectedProductPrice;
                }

                var priceInput = document.getElementById('priceInput');
                if (priceInput) {
                    priceInput.value = parseInt(selectedProductPrice.replace(/,/g, ''));
                }

                // 모달 창 닫기
                $('#productModal').hide();
            }

            // `selectBtn` 이벤트 리스너 추가 (존재할 경우에만)
            var selectBtn = document.getElementById('selectBtn');
            if (selectBtn) {
                selectBtn.addEventListener('click', function() {
                    handleSelectButtonClick(false); // 한국어 모드
                });
            }

            // `selectBtnEng` 이벤트 리스너 추가 (존재할 경우에만)
            var selectBtnEng = document.getElementById('selectBtnEng');
            if (selectBtnEng) {
                selectBtnEng.addEventListener('click', function() {
                    handleSelectButtonClick(true); // 영어 모드
                });
            }





            // 검색 input 박스에 입력된 값으로 리스트를 필터링합니다.
            function filterProductList(query) {
                // 모든 리스트 항목을 선택합니다.
                var items = document.querySelectorAll('#itemList li');
                // 각 항목을 반복하면서 필터링합니다.
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var productNameId = 'productName' + item.id.replace('item', '');
                    var productName = document.getElementById(productNameId).innerText;
                    // 검색어가 없으면 모든 항목을 표시합니다.
                    if (query === '') {
                        item.style.display = '';
                    } else if (productName.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
                        // 제품명이 입력된 값과 일치하면 항목을 표시합니다.
                        item.style.display = '';
                    } else {
                        // 일치하지 않으면 항목을 숨깁니다.
                        item.style.display = 'none';
                    }
                }
            }


        }else{
            console.error("제품할인 상태 변경에 실패하였습니다");
        }
    };

    xhr.onerror = function(){
        console.error("[MENU LIST - GET] Connection Error");
    }

    xhr.send();

}

// function changeStatus(menuPromoId, selectedText){
//     var xhr = new XMLHttpRequest();

//     xhr.open('PATCH','/api/promotion-discount/changestatus?id='+menuPromoId+'&status='+selectedText,true);
//     xhr.onload=function(){
//         if(xhr.status >= 200 && xhr.status<400){
//             console.error("제품할인 상태 변경에 성공하였습니다.");
//         }else{
//             console.error("제품할인 상태 변경에 실패하였습니다");
//         }
//     };
//     xhr.onerror = function(){
//         console.error("[Menu Promotion Change Status - PATCH] Connection Error");
//     }

//     xhr.send();
// }

/* 상태변경 드롭다운 클릭 시 상태 변경하는 api 요청 보내는 함수 */
function changeStatus(menuPromoId, status) {
    var xhr = new XMLHttpRequest();
    var url = '/api/promotion-discount/status?id=' + menuPromoId + '&status=' + status;
    xhr.open('PATCH', url, true);
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 400) {
            console.log("제품할인 상태 변경에 성공하였습니다.");
            // 상태 변경 성공 후 새로고침
            reload();
        } else {
            console.error("제품할인 상태 변경에 실패하였습니다");
        }
    };
    xhr.onerror = function() {
        console.error("[Menu Promotion Change Status- PATCH] Connection Error");
    };

    xhr.send();
}



function openAdditionalContent(boolAddCond, boolAddDesc, addDiscCond, addMenuDesc, ment){
    console.log("open");

    document.getElementById('modal-additional-cond').innerText = boolAddCond ? addDiscCond : "";
    document.getElementById('modal-product-desc').innerText = boolAddDesc ? addMenuDesc : "";
    document.getElementById('modal-ment').innerText = ment;

    //const modal = document.querySelector('.modal-addc');
    const modal = document.getElementById('additionalContentModal');
    modal.classList.add('on');
}

function closeAdditionalContent(){
    console.log("close");

    // const modal = document.querySelector('.modal-addc');
    const modal = document.getElementById('additionalContentModal');
    modal.classList.remove('on');

}

//커스텀 셀렉트 박스
let selectFlag;
$('.custom-select').on('click', function() {
  $(this).toggleClass('selected');
  if($(this).hasClass('selected')) {
    $('.custom-select-list').show();
  } else {
    $('.custom-select-list').hide();
  }
})

$('.custom-select').on('focusin', function() {
  $('.custom-select-list').show();
});

$('.custom-select').on('focusout', function() {
  if(!selectFlag) {
    $('.custom-select-list').hide();
  }
  $(this).removeClass('selected');
});

$('.custom-select-option').on('mouseenter', function() {
  selectFlag = true;
});

$('.custom-select-option').on('mouseout', function() {
  selectFlag = false;
});

$('.custom-select-option').on('click', function() {
  let value = $(this).attr('value');

  $('.custom-select-text').text($(this).text());
  $('.select-origin').val(value);
  $('.custom-select-list').hide();

  $('.select-origin').find('option').each(function(index, el) {
    if($(el).attr('value') == value) {
      $(el).attr('selected', 'selected');
    } else {
      $(el).removeAttr('selected');
    }
  });
});


function changeAdditionalOption(isEnabled, inputId) {

    const inputField = document.getElementById(inputId);
    if (isEnabled) {
        inputField.disabled = false;  // 입력 필드를 활성화
    } else {
        inputField.disabled = true;   // 입력 필드를 비활성화
        inputField.value = '';        // 비활성화될 때 필드의 값을 초기화
    }
}




// 라디오버튼 "없음" 클릭시 input창 disable
function toggleInput(radioName, inputName) {

    document.querySelectorAll(`input[name='${radioName}']`).forEach(function(radio) {
        radio.addEventListener('click', function() {
            var selectedValue = document.querySelector(`input[name='${radioName}']:checked`).value;

            // 입력 필드의 활성화/비활성화 상태를 결정
            if (selectedValue === "yes") {
                document.querySelector(`input[name='${inputName}']`).disabled = false;
                var valueofisadddescinput=document.getElementById('isAddDescInput');
                var valueofisaddcondinput=document.getElementById('isAddCondInput');

                if (radioName=="isAddDesc-k"){
                    valueofisadddescinput.value='true';
                }else{
                    valueofisaddcondinput.value='true';
                }

                console.log(valueofisadddescinput);

            } else {
                document.querySelector(`input[name='${inputName}']`).value="";
                document.querySelector(`input[name='${inputName}']`).disabled = true;
            }
        });
    });

}

function loadUpdateContent(menuPromoId, lang) {
    const url = `/api/promotion-discount/updatepage?id=${menuPromoId}&lang=${lang}`;
    const xhr = new XMLHttpRequest();

    console.log("페이지 로드 요청 전송");

    xhr.open('GET', url, true);

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 400) {
            // 로드된 HTML을 특정 요소에 삽입
            document.getElementById('content2').innerHTML = xhr.responseText;

            // HTML 로드 후 공통 기능 초기화
            initializePageFunctions();
        } else {
            console.error("제품할인 수정 화면 페이지 로드에 실패하였습니다");
        }
    };

    xhr.onerror = function() {
        console.error("Connection error");
    };

    xhr.send();
}



function loadRegistrationPage(lang) {
    const url = `/api/promotion-discount/registerpage?lang=${lang}`;

    fetch(url, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.text(); // JSP 페이지의 HTML을 텍스트로 받아옴
    })
    .then(html => {
        // 로드한 HTML을 특정 요소에 삽입 (예: id가 'content2'인 요소)
        document.getElementById('content2').innerHTML = html;

        // 로드 후 추가 작업 실행
        initializePageFunctions();
    })
    .catch(error => {
        console.error("페이지 로드 중 오류 발생:", error);
    });
}

// 페이지 로드 후 초기화하는 함수(contentUpdate, content2에 사용)
function initializePageFunctions() {
    // 드롭다운 메뉴 클릭 시 show 클래스를 토글
    document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            const dropdownMenu = this.nextElementSibling;
            if (dropdownMenu && dropdownMenu.classList.contains('dropdown-menu')) {
                dropdownMenu.classList.toggle('show');
            }
        });
    });

    // 드롭다운 외부 클릭 시 열려있는 드롭다운 닫기
    document.addEventListener('click', function(event) {
        if (!event.target.closest('.dropdown')) {
            document.querySelectorAll('.dropdown-menu.show').forEach(menu => {
                menu.classList.remove('show');
            });
        }
    });

    // 드롭다운 항목 클릭 시 선택된 텍스트 업데이트 및 값 설정
    document.querySelectorAll('.dropdown-item').forEach(item => {
        item.addEventListener('click', function() {
            const selectedText = this.getAttribute('data-value');
            const parentDropdown = this.closest('.dropdown-content').previousElementSibling;
            parentDropdown.querySelector('span').textContent = selectedText;

            if (selectedText) {
                updateInputValues(selectedText);
            }
        });
    });

    // 날짜 선택 시 범위 제한 설정
    document.getElementById('startDate').addEventListener('change', function() {
        const startDateVal = this.value;
        const endDateInput = document.getElementById('endDate');
        endDateInput.min = startDateVal;
        if (endDateInput.value < startDateVal) {
            endDateInput.value = startDateVal;
        }
    });

    // 검색 input 박스에 입력된 값으로 리스트를 필터링
    const searchInput = document.getElementById('searchInput');
    if(searchInput){
        searchInput.addEventListener('input', function() {
            filterProductList(this.value);
        });
    }
    
}

//입력한 값으로 업데이트
function updateInputValues(selectedText) {

    if (selectedText=="음료"){
        document.getElementById('categoryInput').value="BEVERAGE";
    }
    else if(selectedText=="베이커리"){
       document.getElementById('categoryInput').value="BAKERY";
    }
    else if(selectedText=="케이크"){
       document.getElementById('categoryInput').value="CAKE";
    }
    else if(selectedText=="세트상품"){
       document.getElementById('categoryInput').value="SET";
    }
    else if(selectedText=="5분 간격"){
    document.getElementById('intervalInput').value=5;
    }
    else if(selectedText=="10분 간격" || selectedText=="10-min Interval"){
    document.getElementById('intervalInput').value=10;
    }
    else if(selectedText=="15분 간격"){
    document.getElementById('intervalInput').value=15;
    }
    else if(selectedText=="20분 간격" || selectedText=="20-min Interval"){
    document.getElementById('intervalInput').value=20;
    }
    else if(selectedText=="30분 간격" || selectedText=="30-min Interval"){
    document.getElementById('intervalInput').value=30;
    }
}


function filterProductList(query) {
    const items = document.querySelectorAll('#itemList li');
    items.forEach(item => {
        const productNameId = 'productName' + item.id.replace('item', '');
        const productName = document.getElementById(productNameId).innerText;

        item.style.display = productName.toLowerCase().includes(query.toLowerCase()) ? '' : 'none';
    });
}







/* 제품할인 리스트를 전부 보여주는 모달창 띄우는 함수 */


document.addEventListener('DOMContentLoaded', function() {
    toggleInput("isAddDesc-k", "addMenuDesc");
    toggleInput("isAddCond-k", "addDiscCond");
});


function createMent(lang){

    $.ajax({
        url: '/openai/ment?lang=' + lang,
        method: 'POST',
        dataType: 'json',
        data: {
            menu: $('[name="menu"]').val(),
            price: $('[name="price"]').val(),
            discType: "CASH",
            discVal: $('[name="discVal"]').val(),
            startDate: $('[name="startDate"]').val(),
            endDate: $('[name="endDate"]').val(),
            startTime: $('[name="startTime"]').val(),
            endTime: $('[name="endTime"]').val(),
            isAddDiscCond: $('[name="boolAddCond"]').val(),
            addDiscCond: $('[name="addDiscCond"]').val(),
            isAddMenuDesc: $('[name="isAddDesc-k"]').is(':checked'),
            addMenuDesc: $('[name="addMenuDesc"]').val(),
            isAlways : $('[name="boolIsAlways"]').is(':checked')
        },
        success: function (response) {
            console.log(response.ment);
            // document.getElementById("ment-text").value=response.ment;
            document.getElementById("ment-textarea").value=response.ment;
        },
        error: function () {

            if(lang == 'en'){
                Swal.fire({
                    title: "There is an error while searching!",
                    text: "complete all entries except discount conditions!",
                    position: 'top', // 원하는 위치로 설정
                    confirmButtonText: 'confirm'
                });

            }else{
                Swal.fire({
                    title: "검색 중 오류가 발생했습니다!",
                    text: "할인 조건을 제외한 모든 입력을 완료해주세요!",
                    position: 'top', // 원하는 위치로 설정
                    confirmButtonText: '확인'
                });
            }
        }
    });

}

function mentTest() {
    // <textarea>의 값을 가져옴
    const mentText = document.getElementById("ment-textarea").value;
    
    console.log('멘트정보:', mentText);

    // 서버에 POST 요청을 보냄 (fetch API 사용)
    fetch('/api/websocket/sendMessage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'  // @ModelAttribute를 사용하기 위한 설정
        },
        body: new URLSearchParams({
            ment: mentText
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}



/*  제품 등록 시 유효성을 검사하는 함수   */

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('promotionFormKor');

    form.addEventListener('submit', function (event) {
        // 필수 입력 요소들 (name으로 접근)
        const category = document.querySelector('input[name="category"]').value;
        const menu = document.querySelector('input[name="menu"]').value;
        const discPrice = document.querySelector('input[name="discPrice"]').value;  //할인하려는 가격
        const startTime = document.querySelector('input[name="startTime"]').value;
        const endTime = document.querySelector('input[name="endTime"]').value;
        const mentStartTime = document.querySelector('input[name="mentStartTime"]').value;
        const mentEndTime = document.querySelector('input[name="mentEndTime"]').value;
        const interval = document.querySelector('input[name="interval"]').value;
        const discVal = document.querySelector('input[name="discVal"]').value;

        // 검증 로직
        if (!category || !menu || !discPrice || !startTime || !endTime || !mentStartTime || !mentEndTime || !interval || !ment || !discVal) {
            Swal.fire({
                title: "제출 양식을 지켜주세요!",
                text: "할인 조건을 제외한 모든 정보를 입력하세요!",
                position: 'top', // 원하는 위치로 설정
                confirmButtonText: '확인'
            });
            event.preventDefault(); // 제출을 막음
            return;
        }

        // 추가적인 검증이 필요한 경우 여기에 추가 가능
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('promotionFormEng');

    if(form){

        form.addEventListener('submit', function (event) {
            // 필수 입력 요소들 (name으로 접근)
            const category = document.querySelector('input[name="category"]').value;
            const menu = document.querySelector('input[name="menu"]').value;
            const discPrice = document.querySelector('input[name="discPrice"]').value;  //할인하려는 가격
            const startTime = document.querySelector('input[name="startTime"]').value;
            const endTime = document.querySelector('input[name="endTime"]').value;
            const mentStartTime = document.querySelector('input[name="mentStartTime"]').value;
            const mentEndTime = document.querySelector('input[name="mentEndTime"]').value;
            const interval = document.querySelector('input[name="interval"]').value;
            const discVal = document.querySelector('input[name="discVal"]').value;
    
            // 검증 로직
            if (!category || !menu || !discPrice || !startTime || !endTime || !mentStartTime || !mentEndTime || !interval || !ment || !discVal) {
                Swal.fire({
                    title: "Please follow the form!",
                    text: "Please enter all information except discount conditions!",
                    position: 'top', // 원하는 위치로 설정
                    confirmButtonText: 'Confirm'
                });
                event.preventDefault(); // 제출을 막음
                return;
            }
    
            // 추가적인 검증이 필요한 경우 여기에 추가 가능
        });

    }
   
});

