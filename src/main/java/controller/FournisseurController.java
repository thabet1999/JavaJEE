package controller;

import dao.FournisseurDAO;
import model.Fournisseur;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class FournisseurController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private FournisseurDAO dao;

    @Override
    public void init() throws ServletException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdb", "root", "");
            dao = new FournisseurDAO(conn);
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
                    req.getRequestDispatcher("View/addFournisseur.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Fournisseur f = dao.getFournisseur(id);
                    req.setAttribute("fournisseur", f);
                    req.getRequestDispatcher("View/addFournisseur.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.deleteFournisseur(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("fournisseurs");
                    break;
                default:
                    req.setAttribute("fournisseurs", dao.listFournisseurs());
                    req.getRequestDispatcher("View/listFournisseurs.jsp").forward(req, resp);
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
            String adresse = req.getParameter("adresse");
            String telephone = req.getParameter("telephone");

            Fournisseur fournisseur = new Fournisseur(id, nom, adresse, telephone);
            if (id == 0) {
                dao.addFournisseur(fournisseur);
            } else {
                dao.updateFournisseur(fournisseur);
            }
            resp.sendRedirect("fournisseurs");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
