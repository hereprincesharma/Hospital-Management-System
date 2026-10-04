async function loadPatients() {

    try {

        const response = await fetch(
            "http://localhost:8080/patients"
        );

        const patients = await response.json();

        const tableBody =
            document.getElementById("patientTableBody");

        const total =
            document.getElementById("patientTotal");

        tableBody.innerHTML = "";

        total.textContent =
            "Total : " + patients.length;

        patients.forEach(patient => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>P${String(patient.patient_id).padStart(3, "0")}</td>

                <td>${patient.name}</td>

                <td>${patient.age}</td>

                <td>${patient.gender}</td>

                <td>${patient.phone}</td>

                <td>${patient.blood_group}</td>

                <td>
                    <span class="status confirmed">
                        Active
                    </span>
                </td>

               <td>
                    <button
                        class="table-btn"
                        onclick="viewPatient(${patient.patient_id})">
                        View
                    </button>
                </td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {

        console.error("Error loading patients:", error);

    }
}

loadPatients();

async function viewPatient(patientId) {

    console.log("Clicked patient ID:", patientId);

    try {

        const url =
            "http://localhost:8080/patients/" + patientId;

        console.log("Request URL:", url);

        const response = await fetch(url);

        console.log("Response status:", response.status);

        const text = await response.text();

        console.log("Response:", text);

        if (!response.ok) {
            alert("Error: " + text);
            return;
        }

        const patient = JSON.parse(text);

        alert(
            "Patient Details\n\n" +
            "Patient ID: P" +
            String(patient.patient_id).padStart(3, "0") +
            "\nName: " + patient.name +
            "\nAge: " + patient.age +
            "\nGender: " + patient.gender +
            "\nPhone: " + patient.phone +
            "\nEmail: " + patient.email +
            "\nAddress: " + patient.address +
            "\nBlood Group: " + patient.blood_group
        );

    } catch (error) {

        console.error("View Patient Error:", error);

        alert(
            "Failed to fetch patient details.\n" +
            error
        );
    }
}