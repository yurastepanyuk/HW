
<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="true" %>
<%@ page errorPage="Error.jsp" %>
<html>
<head>
    <title>It is INDEX JSP</title>
</head>
<body>

    <%@include file="include.jsp"%>

    <%! int amount; %>

    <form action="servletStart" method="POST">

        Login: <input type="text" name="login" placeholder="login">
        Password: <input type="text" name="password" placeholder="password">
        <input type="submit" name="enter" value="Log In">
        <input type="submit" name="register" value="Registration">

    </form>

    <a href="/getclients">Seeing clients</a>

</body>

<footer>

    <%
        int a = 2;
        int b = 1;

        int result = a + b;

        amount++;

    %>

    Maybe you don't know that 2 + 1 = <font color="blue"> <%= result %> </font>

    <br>
    Amount: <%= amount%>

</footer>

</html>
