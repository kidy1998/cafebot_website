/*function change_btn(e, period) {
    change_color_btn(e);

    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    const today = new Date();
    startDateInput.valueAsDate = today;

    if (period == 'month'){
        const endDate = new Date(today);
        endDate.setMonth(today.getMonth()+1);
        endDateInput.valueAsDate = endDate;
    }else if (period === '2weeks') {
         const endDate = new Date(today);
         endDate.setDate(today.getDate() + 14);
         endDateInput.valueAsDate = endDate;
    } else if (period === '1week') {
         const endDate = new Date(today);
         endDate.setDate(today.getDate() + 7);
         endDateInput.valueAsDate = endDate;
    } else {
         startDateInput.value = '';
         endDateInput.value = '';
    }

}

function change_color_btn(e){
    const buttons = document.querySelectorAll('.btn.btn-outline-secondary.btn-sm');
    const customFreqInput = document.getElementById('custom_freq_val');
    const customDiscountInput = document.getElementById('custom_discount_val');
    const customIntroduceInput = document.getElementById('custom_introduce_val');

    buttons.forEach(button => button.classList.remove('active'));
    e.target.classList.add('active');

    if (e.target.id === 'freq_own_btn'){
        customFreqInput.style.display='block';
    }
    else if(e.target.id === 'discount_own_btn'){
        customDiscountInput.style.display='block';
    }
    else if (e.target.id === 'introduce_own_btn'){
        customIntroduceInput.style.display='block';
    }
    else{
        customFreqInput.style.display= 'none';
        customDiscountInput.style.display='none';
        customIntroduceInput.style.display='none';
    }
}


function set_time(startTimeId, endTimeId, timeElementId){
    const startTimeInput = document.getElementById(startTimeId).value;
    const endTimeInput = document.getElementById(endTimeId).value;
    const timeElement = document.getElementById(timeElementId);

    if (startTimeInput && endTimeInput) {
        const startTime = new Date(`1970-01-01T${startTimeInput}:00`);
        const endTime = new Date(`1970-01-01T${endTimeInput}:00`);

        let timeDifference = endTime - startTime;
        if (timeDifference < 0) {
            timeDifference += 24 * 60 * 60 * 1000; // add 24 hours if endTime is before startTime
        }

        const hours = Math.floor(timeDifference / (1000 * 60 * 60));
        const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));

        timeElement.innerText = `${hours}시간`;
    } else {
        timeElement.innerText = 'Please enter both start and end times.';
    }

}

function set_time_event(){
    set_time('startTime', 'endTime', 'timeElement');

}

function set_time_ment(){
    set_time('ment-startTime', 'ment-endTime', 'ment-timeElement');

}

document.getElementById('startTime').addEventListener('input',set_time_event);
document.getElementById('endTime').addEventListener('input',set_time_event);
document.getElementById('ment-startTime').addEventListener('input',set_time_ment);
document.getElementById('ment-endTime').addEventListener('input',set_time_ment);*/



/*//검색 모달
 document.addEventListener('DOMContentLoaded', function(){
        // 검색 아이콘을 클릭하면 모달을 표시
            const beverageModal = document.querySelector('#beverageModal');
            const searchIcon = document.querySelector('.modal_btn');
            const closeBtn = document.getElementById(".btn-secondary");

            searchIcon.addEventListener('click', function() {
            $('#beverageModal').show();

         });

        // 모달의 닫기 버튼을 클릭하면 모달을 숨김
              const closeButton = document.querySelector('#beverageModal .close');
              const secondaryButton = document.querySelector('#beverageModal .btn-secondary');

              closeButton.addEventListener('click', function() {
              $('#beverageModal').hide();
              });

              secondaryButton.addEventListener('click', function() {
              $('#beverageModal').hide();
              });
        });*/