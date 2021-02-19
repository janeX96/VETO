function validateForm() {
    const petIdInput = document.getElementById('pet');
    const vetIdInput = document.getElementById('vet');
    const treatmentIdInput = document.getElementById('description');
    const dateInput = document.getElementById('date');

    const errorPetId = document.getElementById('errorPet');
    const errorVetId = document.getElementById('errorVet');
    const errorTreatmentId = document.getElementById('errorDescription');
    const errorDate = document.getElementById('errorDate');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([petIdInput, vetIdInput, treatmentIdInput,dateInput], [errorPetId, errorVetId, errorTreatmentId,errorDate], errorsSummary);
    let valid = true;

    if (!checkRequired(petIdInput.value) || petIdInput.value<0) {
        valid = false;
        petIdInput.classList.add("error-input");
        errorPetId.innerText = "Pole jest wymagane";
    }
    else
        petIdInput.classList.remove("error-input");

    if (!checkRequired(vetIdInput.value) || vetIdInput.value<0) {
        valid = false;
        vetIdInput.classList.add("error-input");
        errorVetId.innerText = "Pole jest wymagane";
    }
    else
        vetIdInput.classList.remove("error-input");

    if (!checkRequired(treatmentIdInput.value)) {
        valid = false;
        treatmentIdInput.classList.add("error-input");
        errorTreatmentId.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(treatmentIdInput.value, 5, 60)) {
        valid = false;
        treatmentIdInput.classList.add("error-input");
        errorTreatmentId.innerText = "Pole powinno zawierać od 5 do 60 znaków";
    }
    else
        treatmentIdInput.classList.remove("error-input");

    if (!checkRequired(dateInput.value)) {
        valid = false;
        dateInput.classList.add("error-input");
        errorDate.innerText = "Pole jest wymagane";
    }
    else
        dateInput.classList.remove("error-input");

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }
    return valid;
}
