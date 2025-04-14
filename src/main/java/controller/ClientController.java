package controller;

import dao.ClientDAO;
import model.Client;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ClientDAO dao;

    @Override
    public void init() throws ServletException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdb", "root", "");
            dao = new ClientDAO(conn);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    req.getRequestDispatcher("View/addClient.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Client client = dao.getClient(id);
                    req.setAttribute("client", client);
                    req.getRequestDispatcher("View/addClient.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.deleteClient(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("clients");
                    break;
                default:
                    req.setAttribute("clients", dao.getAllClients());
                    req.getRequestDispatcher("View/listClients.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
            String nom = req.getParameter("nom");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");

            Client client = new Client(id, nom, email, telephone);
            if (id == 0) {
                dao.addClient(client);
            } else {
                dao.updateClient(client);
            }
            resp.sendRedirect("clients");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
