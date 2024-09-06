<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 20px;
        }
        .logout-btn, .admin-link {
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: block;
            text-align: center;
            margin-bottom: 10px;
        }
        .logout-btn {
            background-color: #dc3545;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
        .admin-link {
            background-color: #007bff;
        }
        .admin-link:hover {
            background-color: #0056b3;
        }
        .top-menu {
            position: absolute;
            top: 20px;
            right: 20px;
            display: flex;
            align-items: center;
            flex-direction: column;
        }
        .icon-btns {
            display: flex;
            align-items: center;
        }
        .icon-btns i {
            font-size: 24px; /* Adjust size as needed */
            color: #333;
            cursor: pointer;
            margin-right: 10px; /* Space between icon and logout button */
        }
        .icon-btns form {
            margin: 0;
        }
        .admin-link {
            margin-top: 10px;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script type="text/javascript">
        function confirmLogout(event) {
            event.preventDefault(); // Prevent form submission
            const confirmLogout = confirm("Are you sure you want to logout?");
            if (confirmLogout) {
                document.getElementById("logoutForm").submit(); // Submit the form if confirmed
            }
        }
    </script>
</head>
<body>
    <div class="top-menu">
        <div class="icon-btns">
            <!-- FontAwesome user icon -->
            <i class="fas fa-user" title="<%= request.getAttribute("username") %>"></i>
            <form id="logoutForm" action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="button" class="logout-btn" onclick="confirmLogout(event)">Logout</button>
            </form>
        </div>
        <!-- Display admin link only if user has admin role -->
        <% 
            String role = (String) request.getAttribute("role");
            if ("ADMIN".equals(role)) {
        %>
            <a href="/adminregister" class="admin-link">Create Admin User</a>
        <% 
            } 
        %>
    </div>
    <div class="container">
        <h2>Welcome, <%= request.getAttribute("username") %>!</h2>
        <p>You are logged in.</p>
    </div>
</body>
</html>
