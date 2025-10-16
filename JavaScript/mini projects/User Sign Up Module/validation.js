// Function to display current date and time (Step 1.1)
function updateDateTime() {
    const now = new Date();
    const options = { 
        weekday: 'long', 
        year: 'numeric', 
        month: 'long', 
        day: 'numeric', 
        hour: '2-digit', 
        minute: '2-digit', 
        second: '2-digit' 
    };
    document.getElementById('datetime').textContent = now.toLocaleDateString('en-US', options);
}

// Initial call and set interval to update every second
setInterval(updateDateTime, 1000);
updateDateTime(); 


// Timer for 3 minutes (180,000 milliseconds) (Step 1.4)
let timer;
const TIME_LIMIT = 180000; // 3 minutes in milliseconds

function startTimer() {
    timer = setTimeout(() => {
        // Pop up an alert message saying 3 mins past.
        alert("3 mins past. You took too long to fill the form.");
        
        // Optional: Reset the form if the time limit is reached
        document.forms["registrationForm"].reset();
    }, TIME_LIMIT);
}

// Function to clear the timer when the form is successfully submitted
function stopTimer() {
    clearTimeout(timer);
}


// Client-side Validation Function (Step 1.3)
function validateForm() {
    let isValid = true;
    
    // Helper function to show/hide error messages
    const displayError = (id, message) => {
        document.getElementById(`error${id}`).textContent = message;
        if (message) {
            document.getElementById(id.charAt(0).toLowerCase() + id.slice(1)).focus();
        }
    };

    // --- First Name Validation ---
    const firstName = document.getElementById('firstName').value.trim();
    if (!firstName) { // Must be entered
        displayError('FirstName', 'First Name must be entered.');
        isValid = false;
    } else if (!/^[a-zA-Z]+$/.test(firstName)) { // Must be character
        displayError('FirstName', 'First Name must contain only characters.');
        isValid = false;
    } else {
        displayError('FirstName', '');
    }

    // --- Last Name Validation ---
    const lastName = document.getElementById('lastName').value.trim();
    if (!lastName) { // Must be entered
        displayError('LastName', 'Last Name must be entered.');
        isValid = false;
    } else if (!/^[a-zA-Z]+$/.test(lastName)) { // Must be character
        displayError('LastName', 'Last Name must contain only characters.');
        isValid = false;
    } else {
        displayError('LastName', '');
    }

    // --- Password Validation ---
    const password = document.getElementById('password').value;
    if (!password) { // Must be entered
        displayError('Password', 'Password must be entered.');
        isValid = false;
    } else if (password.length < 6 || password.length > 20) { // Length between 6 and 20
        displayError('Password', 'Password length must be between 6 and 20 characters.');
        isValid = false;
    } else {
        displayError('Password', '');
    }

    // --- Confirm Password Validation ---
    const confirmPassword = document.getElementById('confirmPassword').value;
    if (!confirmPassword) { // Must be entered
        displayError('ConfirmPassword', 'Confirm Password must be entered.');
        isValid = false;
    } else if (confirmPassword.length < 6 || confirmPassword.length > 20) { // Length between 6 and 20
        displayError('ConfirmPassword', 'Password length must be between 6 and 20 characters.');
        isValid = false;
    } else if (confirmPassword !== password) { // Note: Password and Confirm password should be same.
        displayError('ConfirmPassword', 'Password and Confirm Password do not match.');
        isValid = false;
    } else {
        displayError('ConfirmPassword', '');
    }

    // --- Gender Validation (Radio button) ---
    const gender = document.querySelector('input[name="gender"]:checked');
    if (!gender) { // Must be selected
        displayError('Gender', 'Gender must be selected.');
        isValid = false;
    } else {
        displayError('Gender', '');
    }

    // --- Mobile Number Validation ---
    const mobileNumber = document.getElementById('mobileNumber').value.trim();
    // Mobile number formats: XXX-XXX-XXXX or XXX.XXX.XXXX or XXX XXX XXXX
    const mobilePattern = /^(\d{3}[-\.\s]?\d{3}[-\.\s]?\d{4})$/;
    if (!mobileNumber) { // Must be entered
        displayError('MobileNumber', 'Mobile Number must be entered.');
        isValid = false;
    } else if (!mobilePattern.test(mobileNumber)) { // Must be in below format
        displayError('MobileNumber', 'Invalid format. Use XXX-XXX-XXXX, XXX.XXX.XXXX, or XXX XXX XXXX.');
        isValid = false;
    } else {
        displayError('MobileNumber', '');
    }

    // --- DOB Validation ---
    const dob = document.getElementById('dob').value.trim();
    // DOB format: DD-MM-YYYY
    const dobPattern = /^(0[1-9]|[12]\d|3[01])-(0[1-9]|1[0-2])-(\d{4})$/;
    if (!dob) { // Must be entered
        displayError('Dob', 'DOB must be entered.');
        isValid = false;
    } else if (!dobPattern.test(dob)) { // Must be in DD-MM-YYYY format
        displayError('Dob', 'Invalid format. Use DD-MM-YYYY.');
        isValid = false;
    } else {
        displayError('Dob', '');
    }

    // --- Email Address Validation ---
    const email = document.getElementById('email').value.trim();
    // Email rules: Must contain at least an @ sign and a dot (.). @ must not be the first character. Last dot must be at least one character after the @.
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic but robust pattern
    
    // Detailed logic for required rules:
    const atIndex = email.indexOf('@');
    const lastDotIndex = email.lastIndexOf('.');

    if (!email) { // Must be entered
        displayError('Email', 'Email Address must be entered.');
        isValid = false;
    } else if (atIndex < 1) { // @ must not be the first character (atIndex starts at 0)
        displayError('Email', 'Email must contain @ and @ must not be the first character.');
        isValid = false;
    } else if (lastDotIndex < atIndex + 2) { // Last dot must be at least one character after the @.
        displayError('Email', 'The last dot must be at least one character after the @ sign.');
        isValid = false;
    } else if (!emailPattern.test(email)) { // Catch remaining simple errors (like missing dot)
        displayError('Email', 'Email must contain a valid @ and dot (.).');
        isValid = false;
    } else {
        displayError('Email', '');
    }


    // --- Final Submission ---
    if (isValid) {
        stopTimer(); // Stop the 3-minute timer on successful validation
        alert("Registration Successful! Data is valid.");
        // In a real application, you would submit the form data to the server here.
        return true; 
    } else {
        // Prevent form submission if validation failed
        return false;
    }
}
