package shop.gui.web;

import shop.Shop;
import shop.database.DB;
import shop.enums.CategoryPrice;
import shop.inforamation.ServiceMethodInformation;
import shop.reference.AutoParts;
import shop.reference.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletGetProducts", urlPatterns = "/getallproducts")
public class ServletGetProducts extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8;");

        PrintWriter out = response.getWriter();

        //out.println("<B>List ot Products</B>");
        out.println("<br>");

        Shop shop = Shop.getInstance();

        if (shop == null) {
            return;
        }

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
        //out.println("</body>");


//        <body>
//        <table width="500">
//        <caption>My First table</caption>
//        <thead>
//        <tr>
//        <th>Item</th>
//        <th>Item</th>
//        <th>Item</th>
//        <th>Item</th>
//        </tr>
//        </thead>
//
//        <tbody>
//        <tr>
//        <td rowspan="2">Item</td>
//        <td>Item</td>
//        <td>Item</td>
//        <td>Item</td>
//        </tr>
//        <tr>
//        <td>Item</td>
//        <td>Item</td>
//        <td>Item</td>
//        </tr>
//        </tbody>
//
//        <tfoot>
//        <tr>
//        <td>Item</td>
//        <td>Item</td>
//        <td colspan="2">Item</td>
//        </tr>
//        </tfoot>
//
//        </table>
//        </body>


    }
}
