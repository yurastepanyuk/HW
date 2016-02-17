package shop.gui.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servletStart", urlPatterns = "/servletStart")
public class ServletStart extends ServletDispatcher {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getParameter("enter") != null) super.forward("/getclients",request, response);
       if (request.getParameter("register") != null) super.forward("/registration.jsp", request, response);
    }

}
