const form = document.getElementById('signupForm');

form.addEventListener('submit', function(e) {
  if (confirmPassword()) {
      return true;
  } else {
      e.stopPropagation();
      e.preventDefault();
      return false;
  }
});

function confirmPassword(){
    let password = document.getElementById('content_form').value;
    let confirmPassword = document.getElementById('content_confirm').value;
    let errorText = document.getElementById('error_text');
    let passwordRegex = /^[a-zA-Z][0-9a-zA-Z]{7,24}$/;

    if (passwordRegex.test(password.value)) {
        if (password == confirmPassword) {
            errorText.innerText = "";
            return true;
        } else {
            errorText.innerText = "パスワードが一致しません。";
            return false;
        }
    } else {
        errorText.innerText = "このパスワードは使えません。";
        return false;
    }
}
