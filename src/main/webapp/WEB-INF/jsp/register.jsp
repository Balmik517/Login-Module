<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            text-align: left;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"],
        select {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        button:hover {
            background-color: #218838;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-bottom: 15px;
            text-align: left;
        }
    </style>
    <script>
        function validateForm() {
            const username = document.getElementById("username").value;
            const usernameRegex = /^[a-zA-Z0-9]+$/; // Regex for alphanumeric characters
            const errorMessage = document.getElementById("usernameError");

            if (!usernameRegex.test(username)) {
                errorMessage.textContent = "Username can only contain letters and numbers.";
                return false; // Prevent form submission
            }

            errorMessage.textContent = ""; // Clear error message if valid
            return true; // Allow form submission
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="/register" method="post" onsubmit="return validateForm()">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <div id="usernameError" class="error"></div>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <!-- Role is statically set to USER -->
            <input type="hidden" name="role" value="USER">
            
            <div class="error">
                <c:if test="${not empty error}">
                    ${error}
                </c:if>
            </div>
            
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
