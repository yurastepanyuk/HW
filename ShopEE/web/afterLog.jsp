<%@ page import="java.sql.Struct" %>
<%@ page import="shop.database.DB" %>
<%@ page import="shop.reference.AutoParts" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.inforamation.ServiceMethodInformation" %>
<%@ page import="shop.enums.CategoryPrice" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="shop.Shop" %>

<html>
<head>
    <title>After log</title>
</head>
<body>

    <%! shop.Shop shop; %>

    <%
        String login = request.getParameter("login");
        String pass  = request.getParameter("password");
    %>

    <p>Your Login: <%= login%></p>
    <p>Your password: <%= pass%> </p>

    <br>

    <%
        shop = Shop.getInstance();

        DB db = shop.getDb();

        List<AutoParts> autoparts = (List<AutoParts>) db.getDataFromTable(AutoParts.class);

        ServiceMethodInformation serviceMethodInformation = shop.getServiceMethodInformation();

        //out.println("<body>");
        out.println("<table width=500>");
        out.println("<caption>List ot Products<caption>");

        out.println("<thead>");
        out.println("<tr>");

        out.println("<th style=\"border: 2px solid black\">Catalog number</th>");
        out.println("<th style=\"border: 2px solid black\">Name</th>");
        out.println("<th style=\"border: 2px solid black\">Price</th>");

        out.println("</tr>");
        out.println("</thead>");

        out.println("<tbody>");

        for (AutoParts curAuroPart : autoparts) {
            out.println("<tr>");
            out.println("<td style=\"border: 1px solid black\">" + curAuroPart.getCatalogNumber()  + "</td>");
            out.println("<td style=\"border: 1px solid black\">" + curAuroPart.getName()           + "</td>");
            out.println("<td style=\"border: 1px solid black\">" + serviceMethodInformation.getPriceByGoods(curAuroPart, CategoryPrice.ROZNITSA) + "</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");

        out.println("</table>");

    %>

</body>
</html>
