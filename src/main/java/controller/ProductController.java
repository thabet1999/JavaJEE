package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdb", "root", "");
            dao = new ProductDAO(conn);
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
                    req.getRequestDispatcher("View/addProduct.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Product p = dao.getProduct(id);
                    req.setAttribute("product", p);
                    req.getRequestDispatcher("View/addProduct.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.deleteProduct(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("products");
                    break;
                default:
                    req.setAttribute("products", dao.getAllProducts());
                    req.getRequestDispatcher("View/listProducts.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double price = Double.parseDouble(req.getParameter("price"));

            Product product = new Product(id, name, quantity, price);
            if (id == 0) {
                dao.addProduct(product);
            } else {
                dao.updateProduct(product);
            }
            resp.sendRedirect("products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
