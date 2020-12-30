'use strict';
(function () {
    window.confirmTwoInputs = function (first, second, comparedFields) {
        let input = document.getElementById(first)
        let confirmInput = document.getElementById(second)

        let validateInputs = function () {
            if (input.value !== confirmInput.value) {
                confirmInput.setCustomValidity(`${comparedFields} do not match`)
            } else {
                confirmInput.setCustomValidity('')
            }
        }
        input.addEventListener('change', validateInputs)
        confirmInput.addEventListener('keyup', validateInputs)
    }
})()
