let currentStartIndex = 0;
const itemsPerPage = 3;

function showItems(startIndex) {

    const items = document.querySelectorAll('.location-info');

    items.forEach((item, index) => {
        item.classList.add('hidden');
        item.classList.remove('showlist');
        if (index >= startIndex && index < startIndex + itemsPerPage) {
            item.classList.remove('hidden');
            item.classList.add('showlist');
        }
    });

    // 이전 버튼 제어
    if (currentStartIndex <= 0) {
        document.getElementById('prevButton').classList.add('hidden');
    } else {
        document.getElementById('prevButton').classList.remove('hidden');
    }

    // 다음 버튼 제어
    if (currentStartIndex + itemsPerPage >= items.length) {
        document.getElementById('nextButton').classList.add('hidden');
    } else {
        document.getElementById('nextButton').classList.remove('hidden');
    }
}

function showNextItems() {
    const items = document.querySelectorAll('.location-info');
    currentStartIndex += itemsPerPage;
    showItems(currentStartIndex);
}

function showPreviousItems() {
    const items = document.querySelectorAll('.location-info');
    currentStartIndex -= itemsPerPage;
    showItems(currentStartIndex);
}

// 페이지 로드 시 처음 3개의 항목을 표시
showItems(currentStartIndex);


function userInputModal(){


}


function openAdditionalContent(){
    const modal = document.querySelector('.modal-addc');
    modal.classList.add('on');
}

function closeAdditionalContent(){

    const modal = document.querySelector('.modal-addc');
    modal.classList.remove('on');

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

function initiateLocationSettings(){
    var graphicSelections= document.querySelectorAll(".dasom-location-register li");
    for (var i=0; i<graphicSelections.length; i++){
        if (graphicSelections[i].classList.contains('selected')){
            graphicSelections[i].classList.remove('selected');
        }
    }
    var inputLists = document.querySelectorAll(".dasom-location-input input");
    for (var i=0; i<inputLists.length; i++){
        inputLists[i].value="";
        inputLists[i].setAttribute('disabled', 'disabled');
    }
}

var selectedItems_5_v;
var selectedItems_4_v;
var selectedItems_6_v;
var selectedItems_1_v;
var selectedItems_3_v;

function updateSelectedItems(selectedItems, selectedText,selectedMenu, parentDivId, type) {


    if (type==0){
        switch (parentDivId) {
            case "dropdown1":
                selectedItems = selectedItems_1;
                break;

            case "dropdown3":
                selectedItems = selectedItems_3;
                break;

            case "dropdown4":
                selectedItems = selectedItems_4;
                break;

            case "dropdown5":
                selectedItems = selectedItems_5;
                break;

            case "dropdown6":
                selectedItems = selectedItems_6;
                break;
        }

    }else{
        switch (parentDivId) {
            case "dropdown1":
                selectedItems = selectedItems_1_v;
                break;

            case "dropdown3":
                selectedItems = selectedItems_3_v;
                break;

            case "dropdown4":
                selectedItems = selectedItems_4_v;
                break;

            case "dropdown5":
                selectedItems = selectedItems_5_v;
                break;

            case "dropdown6":
                selectedItems = selectedItems_6_v;
                break;
        }
    }

    // 선택된 항목이 이미 있는지 확인
    if (selectedItems.includes(selectedText)) {
        // 이미 선택된 항목이면, 선택 해제
        selectedItems = selectedItems.filter(function(text) {
            return text !== selectedText;
        });
        if (selectedMenu=="직접입력"){
            document.querySelector(`.dropdown-menu#${parentDivId} .nav-item[data-value="${selectedMenu}"]`).classList.remove('selected');
        }else{
            document.querySelector(`.dropdown-menu#${parentDivId} .nav-item[data-value="${selectedText}"]`).classList.remove('selected');
        }
    } else {
        if (selectedItems.length < 2) {
            // 새로운 항목 선택
            selectedItems.push(selectedText);
            if (selectedMenu=="직접입력"){
                document.querySelector(`.dropdown-menu#${parentDivId} .nav-item[data-value="${selectedMenu}"]`).classList.add('selected');
            }else{
                document.querySelector(`.dropdown-menu#${parentDivId} .nav-item[data-value="${selectedText}"]`).classList.add('selected');
            }

        }
    }

    // 선택된 항목들을 input 필드에 표시
    var selectedInput = document.getElementById('input' + parentDivId);
    console.log(selectedItems);
    selectedInput.value = selectedItems.join(', ');


    if (type==0){
        switch (parentDivId) {
            case "dropdown1":
                selectedItems_1 = selectedItems;
                break;
            case "dropdown3":
                selectedItems_3 = selectedItems;
                break;
            case "dropdown4":
                selectedItems_4 = selectedItems;
                break;
            case "dropdown5":
                selectedItems_5 = selectedItems;
                break;
            case "dropdown6":
                selectedItems_6 = selectedItems;
                break;
            }
    }
    else{
        // 선택된 항목 배열 업데이트
        switch (parentDivId) {
            case "dropdown1":
                selectedItems_1_v = selectedItems;
                break;
            case "dropdown3":
                selectedItems_3_v = selectedItems;
                break;
            case "dropdown4":
                selectedItems_4_v = selectedItems;
                break;
            case "dropdown5":
                selectedItems_5_v = selectedItems;
                break;
            case "dropdown6":
                selectedItems_6_v = selectedItems;
                break;
        }

    }

}


///////////////////////////////////
    var selectedItems_5 = [];
    var selectedItems_4 = [];
    var selectedItems_6 = [];
    var selectedItems_1 = [];
    var selectedItems_3 = [];

    document.querySelectorAll('.dropdown-menu .nav-item').forEach(function(item) {
        item.addEventListener('click', function() {

            var selectedMenu = this.getAttribute('data-value');
            var selectedText = this.getAttribute('data-value');
            var parentDivId = this.closest('.dropdown-menu').id;

            var selectedItems=[];


            if (selectedText=="직접입력"){
                openAdditionalContent();

                const userInputField = document.getElementById('user-input');
                userInputField.style.display = 'inline'; // 입력 필드를 보이게 함
                userInputField.focus(); // 입력 필드에 포커스

                $('.close-btn-addc').one('click', function(){
                    selectedText = userInputField.value.trim(); // 공백 제거

                    updateSelectedItems(selectedItems,selectedText,selectedMenu, parentDivId,0);
                    document.getElementById('user-input').value="";
                });

            }else{
                 updateSelectedItems(selectedItems,selectedText,selectedMenu, parentDivId,0);
            }



        });
    });


    var radl=document.updateForm.location;
    var prev = null;
    for (var i =0; i<radl.length; i++){
        radl[i].addEventListener('change', function(){
            if (this !== prev){
                prev=this;
            }

            initiateLocationSettings();

        });

    }

    // 여러 개의 location-list li 항목을 클릭할 수 있도록 수정한 코드
   $('.location-list li').click(function() {

       var text = $(this).text().trim();

       console.log(text);
       if (text === '카페봇') {
           return; // '다솜' 텍스트를 가진 요소는 클릭을 막음
       }

       var index = $(this).index() + 1;
       var input = $('#inputdropdown' + index);

       if ($(this).hasClass('selected')) { // 이미 선택된 경우
            console.log("already selected");
           // 선택 해제
           $(this).removeClass('selected');
           // input을 비활성화
           input.val("");
           input.prop('disabled', true);

       } else {
            console.log("not selected");
           // 클릭한 li 요소 선택
           $(this).addClass('selected');
           // 해당 input 활성화
           input.prop('disabled', false);
           console.log(input);
       }
   });
///////////////////////////////////


function inputEventListener() {

    selectedItems_5_v = [];
    selectedItems_4_v = [];
    selectedItems_6_v = [];
    selectedItems_1_v = [];
    selectedItems_3_v = [];

    document.querySelectorAll('.dropdown-menu .nav-item').forEach(function(item) {
        item.addEventListener('click', function() {

            var selectedMenu = this.getAttribute('data-value');
            var selectedText = this.getAttribute('data-value');

            var parentDivId = this.closest('.dropdown-menu').id;
            var selectedItems_v=[];

            if (selectedText=="직접입력"){
                openAdditionalContent();

                const userInputField = document.getElementById('user-input');
                userInputField.style.display = 'inline'; // 입력 필드를 보이게 함
                userInputField.focus(); // 입력 필드에 포커스

                $('.close-btn-addc').one('click', function(){
                    selectedText = userInputField.value.trim(); // 공백 제거

                    updateSelectedItems(selectedItems_v,selectedText,selectedMenu, parentDivId,1);
                    document.getElementById('user-input').value="";
                });

            }else{
                 updateSelectedItems(selectedItems_v,selectedText,selectedMenu, parentDivId,1);
            }

        });
    });


    var radl=document.updateForm.location;
    var prev = null;
    for (var i =0; i<radl.length; i++){
        radl[i].addEventListener('change', function(){
            if (this !== prev){
                prev=this;
            }

            initiateLocationSettings();

        });

    }

    // 여러 개의 location-list li 항목을 클릭할 수 있도록 수정한 코드
   $('.location-list li').click(function() {
       var text = $(this).text().trim();

       console.log(text);
       if (text === '카페봇') {
           return; // '다솜' 텍스트를 가진 요소는 클릭을 막음
       }
       var index = $(this).index() + 1;
       var input = $('#inputdropdown' + index);

       if ($(this).hasClass('selected')) { // 이미 선택된 경우
            console.log("already selected");
           // 선택 해제
           $(this).removeClass('selected');
           // input을 비활성화
           input.val("");
           input.prop('disabled', true);
       } else {
            console.log("not selected");
           // 클릭한 li 요소 선택
           $(this).addClass('selected');
           // 해당 input 활성화
           input.prop('disabled', false);
           console.log(input);
       }
   });
}

function confirmToggleClick(index, locationId, lang) {
    
    // 위치 변경을 확인하는 창을 띄움
    const isConfirmed = confirm("위치를 변경하시겠습니까?");
    
    if (isConfirmed) {
        // 사용자가 확인을 누르면 handleToggleClick 함수 실행
        handleToggleClick(index, locationId, lang);
    } else {
        console.log("위치 변경이 취소되었습니다.");
    }
}

function handleToggleClick(index, locationId, isChecked) {
    // 현재 클릭한 radio 버튼을 가져옴
    const radio = document.getElementById('toggleOn' + index);

    if (radio) {
        radio.checked = true;  // 클릭된 radio 버튼을 체크
        console.log("Radio button state updated: " + radio.checked);  // 디버깅용 체크 상태 확인
    } else {
        console.error("Radio button not found for index: " + index);
        return;
    }

    // 리다이렉트 URL 생성
    const newState = radio.checked;  // 새로운 radio 버튼 상태
    const url = '/settings/dasom-locations/use?use=' + newState + '&id=' + locationId + '&lang=' + lang;

    // 페이지 리다이렉트
    window.location.href = url;
}


// // 언어 선택 드롭다운 초기화
// document.addEventListener('DOMContentLoaded', function () {
//     // Bootstrap 드롭다운 수동 초기화
//     var dropdowns = document.querySelectorAll('.dropdown-toggle');
//     dropdowns.forEach(function(dropdown) {
//         new bootstrap.Dropdown(dropdown);
//         console.log("드롭다운 초기화");
//     });
// });




document.addEventListener('DOMContentLoaded', function() {

    var dropdownOptions = document.querySelectorAll('.dropdown-options li');



    // Add click event listener to each dropdown item
    dropdownOptions.forEach(function(option) {
        option.addEventListener('click', function() {


            // Get the value from the clicked item
            var selectedValue = option.getAttribute('data-value');

            // Find the input associated with the dropdown
            var inputField = option.closest('.location-group').querySelector('input.custom-input');

            // Set the value of the input field
            // inputField.value = selectedValue;

            // Optionally, close the dropdown after selection
            var dropdownMenu = option.closest('.dropdown-menu');
            dropdownMenu.classList.remove('show');
        });
    });

    var inputFields = document.querySelectorAll('input.custom-input');
    inputFields.forEach(function(input) {
        input.addEventListener('click', function() {
            var dropdownMenu = this.closest('.location-group').querySelector('.dropdown-menu');
            dropdownMenu.classList.add('show');
        });
    });

    document.addEventListener('click', function(event) {
        if (!event.target.closest('.location-group')) {
            var openDropdowns = document.querySelectorAll('.dropdown-menu.show');
            openDropdowns.forEach(function(menu) {
                menu.classList.remove('show');
            });
        }
    });
});


//수정페이지로 이동하는 js
function loadUpdateLocationContent(locationId, lang){

    var xhr = new XMLHttpRequest();

    xhr.open('GET','/settings/dasom-locations/updatepage?id='+locationId+'&lang='+lang, true);

    // 요청 성공할 경우 onload 설정
    xhr.onload=function(){
        if(xhr.status >= 200 && xhr.status<400){
            document.getElementById('location_input').innerHTML = xhr.responseText;

            var allSpans = document.querySelectorAll('[id^="settings"]');
            allSpans.forEach(function(span) {
                span.style.backgroundColor = ''; // 배경색 초기화
            });

            // 여기서 해당 id를 가진 요소의 배경색을 변경
            var locationSpan = document.getElementById('settings' + locationId);
            if(locationSpan) {
                locationSpan.style.backgroundColor = '#F5DEB3'; // 연한 갈색
            }

            inputEventListener();


        }else{
            console.error("제품할인 수정 화면 페이지 로드에 실패하였습니다");
        }
    };

    // 요청 에러가 날 경우 onerror 설정
    xhr.onerror = function() {
        console.error("Connection error");
    };
    xhr.send();

}


document.addEventListener('DOMContentLoaded', function() {


    // 드롭다운 메뉴를 클릭할 때 show 클래스를 토글하는 함수
    function toggleDropdown(event) {

      const dropdownToggle = event.target.closest('.dropdown-toggle');
      if (!dropdownToggle) return;

      // dropdownToggle과 같은 부모 요소를 공유하는 dropdown-menu-toggle 를 찾기
      const dropdownMenu = dropdownToggle.nextElementSibling;
      console.log(dropdownMenu);

      // dropdownMenu가 존재하고, classList를 사용할 수 있는지 확인
      if (dropdownMenu && dropdownMenu.classList.contains('dropdown-menu-right')) {
          
          // 다른 열려있는 드롭다운 메뉴를 닫기 (optional: 페이지에서 하나만 열리도록 할 때)
          document.querySelectorAll('.dropdown-menu-right.show').forEach(function(menu) {
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
      const isClickInside = event.target.closest('.dropdown-toggle, .dropdown-menu-right');
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



});
