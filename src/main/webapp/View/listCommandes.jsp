<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Commande" %>
<%@ page import="java.util.List" %>
<%
    List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Commandes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Liste des Commandes</h2>
    <a href="commandes?action=add" class="btn btn-success mb-3">Ajouter une commande</a>
    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Client</th>
            <th>Produit</th>
            <th>Quantité</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${commandes}">
            <tr>
                <td>${c.id}</td>
                <td>${c.clientId}</td>
                <td>${c.produitId}</td>
                <td>${c.quantite}</td>
                <td>${c.date}</td>
                <td>
                    <a href="commandes?action=edit&id=${c.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="commandes?action=delete&id=${c.id}" class="btn btn-danger btn-sm" onclick="return confirm('Supprimer cette commande ?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
