function validateForm() {
    const nameInput = document.getElementById('name');
    const typeInput = document.getElementById('type');
    const ownerFirstNameInput = document.getElementById('ownerFirstName');
    const ownerLastNameInput = document.getElementById('ownerLastName');
    const phoneInput = document.getElementById('phone');

    const errorName = document.getElementById('errorName');
    const errorType = document.getElementById('errorType');
    const errorOwnerFirstName = document.getElementById('errorOwnerFirstName');
    const errorOwnerLastName = document.getElementById('errorOwnerLastName');
    const errorPhone = document.getElementById('errorPhone');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([nameInput, typeInput, ownerFirstNameInput, ownerLastNameInput, phoneInput],
        [errorName, errorType, errorOwnerFirstName, errorOwnerLastName, errorPhone], errorsSummary);
    let valid = true;


    if (!checkRequired(nameInput.value)) {
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(nameInput.value, 2, 60)) {
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(typeInput.value)) {
        valid = false;
        typeInput.classList.add("error-input");
        errorType.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(typeInput.value, 2, 60)) {
        valid = false;
        typeInput.classList.add("error-input");
        errorType.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(ownerFirstNameInput.value)) {
        valid = false;
        ownerFirstNameInput.classList.add("error-input");
        errorOwnerFirstName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(ownerFirstNameInput.value, 2, 60)) {
        valid = false;
        ownerFirstNameInput.classList.add("error-input");
        errorOwnerFirstName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(ownerLastNameInput.value)) {
        valid = false;
        ownerLastNameInput.classList.add("error-input");
        errorOwnerLastName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(ownerLastNameInput.value, 2, 60)) {
        valid = false;
        ownerLastNameInput.classList.add("error-input");
        errorOwnerLastName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(phoneInput.value)) {
        valid = false;
        phoneInput.classList.add("error-input");
        errorPhone.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(phoneInput.value, 2, 60)) {
        valid = false;
        phoneInput.classList.add("error-input");
        errorPhone.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }

    return valid;
}
