function validateForm() {
    const petIdInput = document.getElementById('petId');
    const vetIdInput = document.getElementById('vetId');
    const treatmentIdInput = document.getElementById('treatmentId');
    const dateInput = document.getElementById('date');

    const errorPetId = document.getElementById('errorPetId');
    const errorVetId = document.getElementById('errorVetId');
    const errorTreatmentId = document.getElementById('errorTreatmentId');
    const errorDate = document.getElementById('errorDate');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([petIdInput, vetIdInput, treatmentIdInput,dateInput], [errorPetId, errorVetId, errorTreatmentId,errorDate], errorsSummary);
    let valid = true;

    if (!checkRequired(petIdInput.value) || petIdInput.value<0) {
        valid = false;
        petIdInput.classList.add("error-input");
        errorPetId.innerText = "Pole jest wymagane";
    }

    if (!checkRequired(vetIdInput.value) || vetIdInput.value<0) {
        valid = false;
        vetIdInput.classList.add("error-input");
        errorVetId.innerText = "Pole jest wymagane";
    }

    if (!checkRequired(treatmentIdInput.value)) {
        valid = false;
        treatmentIdInput.classList.add("error-input");
        errorTreatmentId.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(treatmentIdInput.value, 2, 60)) {
        valid = false;
        treatmentIdInput.classList.add("error-input");
        errorTreatmentId.innerText = "Pole powinno zawierać od 5 do 60 znaków";
    }

    if (!checkRequired(dateInput.value)) {
        valid = false;
        dateInput.classList.add("error-input");
        errorDate.innerText = "Pole jest wymagane";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }
    return valid;
}
