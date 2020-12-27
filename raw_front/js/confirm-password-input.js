'use strict';
(function () {
    let password = document.getElementById('password')
    let confirmPassword = document.getElementById('confirm_password')

    let validatePassword = function () {
        if (password.value !== confirmPassword.value) {
            confirmPassword.setCustomValidity('Passwords do not match')
        } else {
            confirmPassword.setCustomValidity('')
        }
    }

    password.addEventListener('change', validatePassword)
    confirmPassword.addEventListener('keyup', validatePassword)
})()
