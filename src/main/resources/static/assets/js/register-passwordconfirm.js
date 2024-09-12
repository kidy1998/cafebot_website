document.getElementById('exampleInputPasswordConfirm1').addEventListener('input', function() {
  const password = document.getElementById('exampleInputPassword1').value;
  const confirmPassword = this.value;
  const statusSpan = document.getElementById('passwordMatchStatus');

  if (confirmPassword === password) {
    /*statusSpan.textContent = '✔ 비밀번호가 일치합니다';*/
    statusSpan.className = 'match';
  } else {
    /*statusSpan.textContent = 'x 비밀번호가 일치하지 않습니다';*/
    statusSpan.className = 'no-match';
  }
});