package controller;

import dao.CommandeDAO;
import model.Commande;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class CommandeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private CommandeDAO dao;

    public void init() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdb", "root", "");
            dao = new CommandeDAO(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("delete")) {
            dao.deleteCommande(Integer.parseInt(req.getParameter("id")));
        }
        req.setAttribute("commandes", dao.listCommandes());
        req.getRequestDispatcher("View/listCommandes.jsp").forward(req, resp);
        
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
        int clientId = Integer.parseInt(req.getParameter("clientId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantite = Integer.parseInt(req.getParameter("quantite"));
        String date = req.getParameter("date");

        Commande commande = new Commande(id, clientId, productId, quantite, date);
        if (id == 0) {
            dao.addCommande(commande);
        } else {
            dao.updateCommande(commande);
        }
        resp.sendRedirect("commandes");
    }
}
