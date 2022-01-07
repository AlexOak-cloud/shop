const username = document.getElementById('username');
const usernameError = document.querySelector('#username + span.error');
const pass1 = document.getElementById('pass1');
const pass1Error = document.querySelector('#pass1 + span.error');
const name = document.getElementById('name');
const nameError = document.querySelector('#name + span.error');
const surname = document.getElementById('surname');
const surnameError = document.querySelector('#surname + span.error');
const number = document.getElementById('number');
const numberError = document.querySelector('#number + span.error');
const mail = document.getElementById('mail');
const mailError = document.querySelector('#mail + span.error');


username.addEventListener('input', function (event) {
    if (username.validity.valid) {
        usernameError.className = 'error';
    } else {
        showError();
    }
});
pass1.addEventListener('input', function (event) {
    if (pass1.validity.valid) {
        pass1Error.textContent = '';
        pass1Error.className = 'error';
    } else {
        showError();
    }
});
name.addEventListener('input', function (event) {
    if (name.validity.valid) {
        nameError.textContent = '';
        nameError.className = 'error';
    } else {
        showError();
    }
});
surname.addEventListener('input', function (event) {
    if (surname.validity.valid) {
        surnameError.textContent = '';
        surnameError.className = 'error';
    } else {
        showError();
    }
});
number.addEventListener('input', function (event) {
    if (number.validity.valid) {
        numberError.textContent = '';
        numberError.className = 'error';
    } else {
        showError();
    }
});
mail.addEventListener('input', function (event) {
    if (mail.validity.valid) {
        mailError.textContent = '';
        mailError.className = 'error';
    } else {
        showError();
    }
});


