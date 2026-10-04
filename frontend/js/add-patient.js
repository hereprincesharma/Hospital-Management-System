const patientForm = document.getElementById("patientForm");
const message = document.getElementById("message");

patientForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    const patientData = {
        name: document.getElementById("name").value,
        age: document.getElementById("age").value,
        gender: document.getElementById("gender").value,
        phone: document.getElementById("phone").value,
        email: document.getElementById("email").value,
        address: document.getElementById("address").value,
        blood_group: document.getElementById("blood_group").value
    };

    try {

        const response = await fetch(
            "http://localhost:8080/patients",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(patientData)
            }
        );

        const result = await response.json();

        if (response.ok) {

            message.textContent =
                "Patient added successfully!";

            patientForm.reset();

        } else {

            message.textContent =
                "Error: " + result.error;
        }

    } catch (error) {

        console.error(error);

        message.textContent =
            "Server connection failed.";
    }

});