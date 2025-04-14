<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Fournisseur" %>
<%@ page import="java.util.List" %>
<%
    List<Fournisseur> fournisseurs = (List<Fournisseur>) request.getAttribute("fournisseurs");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Fournisseurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Liste des Fournisseurs</h2>
    <a href="fournisseurs?action=add" class="btn btn-success mb-3">Ajouter un fournisseur</a>
    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Adresse</th>
            <th>Téléphone</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="f" items="${fournisseurs}">
            <tr>
                <td>${f.id}</td>
                <td>${f.nom}</td>
                <td>${f.adresse}</td>
                <td>${f.telephone}</td>
                <td>
                    <a href="fournisseurs?action=edit&id=${f.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="fournisseurs?action=delete&id=${f.id}" class="btn btn-danger btn-sm" onclick="return confirm('Supprimer ce fournisseur ?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
