// Implementing functions like login(), getCustomerList(), addCustomer(), and syncData() using fetch API.

async function login() {
    try {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const response = await fetch('/api/customers/admin/adminProfile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            // Handle successful login,
            console.log('Login successful');
        } else {
            // Handle login failure, e.g., display an error message
            console.error('Login failed:', response.statusText);
        }
    } catch (error) {
        console.error('Error during login:', error);
    }
}
/*Querying the customer data from the database using fetch API*/
async function getCustomerList() {
    try {
        const response = await fetch('/api/customers');
        if (!response.ok) {
            throw new Error(`Failed to fetch customer data: ${response.statusText}`);
        }

        const data = await response.json();
        const tableBody = document.getElementById('customerTableBody');

        tableBody.innerHTML = '';

        data.forEach(customer => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${customer.uuid}</td>
                <td>${customer.name}</td>
                <td>${customer.email}</td>
                <td>${customer.address}</td>
                <td>${customer.phone}</td>
                <td>${customer.address}</td>
                <td>${customer.state}</td>
                <td>${customer.zip}</td>
               
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching customer data:', error);
    }
}

async function addCustomer() {
    try {
        const firstName = document.getElementById('firstName').value;
        const email = document.getElementById('email').value;
        const address = document.getElementById('address').value;
        const phone = document.getElementById('phone').value;
        const city = document.getElementById('city').value;
        const state = document.getElementById('state').value;
        const zip = document.getElementById('zip').value;
        const country = document.getElementById('country').value;
        const response = await fetch('/api/customers/addNewUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ firstName, email, address, phone, city, state, zip, country}),
        });
        if (response.ok) {
            // Handle successful customer addition,
            console.log('Customer added successfully');
        } else {
            // Handle customer addition failure,
            console.error('Failed to add customer:', response.statusText);
        }
    } catch (error) {
        console.error('Error during customer addition:', error);
    }
}
async function syncData() {
    try {
        const response = await fetch('/api/syncData');
        if (!response.ok) {
            throw new Error(`Failed to sync data: ${response.statusText}`);
        }
        const data = await response.json();
        // Implement logic to update local database with the received data
        console.log('Data synchronized successfully:', data);
    } catch (error) {
        console.error('Error during data synchronization:', error);
    }
}