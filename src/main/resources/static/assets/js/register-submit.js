function validateAndSubmit() {
  // Get form elements
  const name = document.getElementById('exampleInputUsername1').value;
  const phoneNum = document.getElementById('exampleInputPhonenum1').value;
  const email = document.getElementById('exampleInputEmail1').value;
  const password = document.getElementById('exampleInputPassword1').value;
  const passwordConfirm = document.getElementById('exampleInputPasswordConfirm1').value;
  const passwordMatchStatus = document.getElementById('passwordMatchStatus').className;

  // Check if all fields are filled
  if (!name || !phoneNum || !email) {
    showModal('모든 필드를 올바르게 채워주세요.');
    return;
  }

  // Check if password is at least 6 characters
  else if (password.length < 6) {
    showModal('비밀번호는 6자리 이상이어야 합니다.');
    return;
  }

  // Check if passwords match
  else if (passwordMatchStatus !== 'match') {
    showModal('비밀번호가 일치하지 않습니다.');
    return;
  }

  // Submit the form
  document.getElementById('registerForm').submit();
}

function showModal(message) {
  // Get the modal
  const modal = document.getElementById('errorModal');

  // Change the modal message
  modal.querySelector('.modal-body p').textContent = message;

  // Show the modal
  $(modal).modal('show');
}

document.querySelector('.modal .close').addEventListener('click', function(){
    $('#errorModal').modal('hide');
});