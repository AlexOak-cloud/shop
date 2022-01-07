const form  = document.getElementsByTagName('form')[0];
const username = document.getElementById('username');
const usernameError = document.querySelector('#username + span.error');

username.addEventListener('input', function (event) {
    if (username.validity.valid) {
        usernameError.textContent = '';
        usernameError.className = 'error';
    } else {
        showError();
    }
});

form.addEventListener('submit', function (event) {
    if(!username.validity.valid) {
        showError();
        event.preventDefault();
    }
});

function showError() {
    if(username.validity.valueMissing) {
        usernameError.textContent = 'Длина';
    } else if(username.validity.typeMismatch) {
        usernameError.textContent = 'Тип';
    } else if(username.validity.tooShort) {


        usernameError.textContent = `Email should be at least ${ email.minLength } characters; you entered ${ email.value.length }.`;
    }


    usernameError.className = 'error active';
}

