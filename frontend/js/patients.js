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
                    <button class="table-btn">
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