<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%
    List<Product> produits = (List<Product>) request.getAttribute("produits");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Produits</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Liste des Produits</h2>
    <a href="products?action=add" class="btn btn-success mb-3">Ajouter un produit</a>
    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Quantité</th>
            <th>Prix</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${produits}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.quantity}</td>
                <td>${p.price}</td>
                <td>
                    <a href="products?action=edit&id=${p.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="products?action=delete&id=${p.id}" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
