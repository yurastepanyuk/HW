
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

    <form action="" method="post">


        Login:      <input type="text" name="login"/>
        <br>
        <br>
        Password:   <input type="password" name="password"/>

        <br>
        <br>

        <fieldset width="100">
            <legend>Personal Information</legend>
            First name: <input type="text" name="firstname"/>
            <br>
            <br>
            Last name:  <input type="text" name="lastname">
            <br>
            <br>
            <label for=\"sex\">Sex:</label>
            <select name="sex" id="sex">
                <option value="Male">Male</option>
             <option value="Female">Female</option>
            </select>
        </fieldset>

        <input type="submit" name="register" value="Registration">

    </form>

</body>
</html>
