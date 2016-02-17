package shop.gui.web;

import shop.Shop;
import shop.database.DB;
import shop.reference.Client;
import shop.reference.ServiceMethodReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "servletGetClients", urlPatterns = "/getclients")
public class ServletGetClients extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.println("<B>List ot Clients</B>");
        out.println("<br>");

        Shop shop = Shop.getInstance();

        if (shop == null) {
            return;
        }

        DB db = shop.getDb();

        List<Client> clients = (List<Client>) db.getDataFromTable(Client.class);

        out.println("<ol>");
        for (Client curClient : clients) {
            out.println("<li>" + curClient.toString() + "</li>");
        }
        out.println("</ol>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
