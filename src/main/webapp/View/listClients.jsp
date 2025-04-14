<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<%
    List<Client> clients = (List<Client>) request.getAttribute("clients");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Clients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2 class="mb-4">Liste des Clients</h2>
    <a href="clients?action=add" class="btn btn-success mb-3">Ajouter un nouveau client</a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th><th>Nom</th><th>Email</th><th>Téléphone</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.id}</td>
                <td>${client.nom}</td>
                <td>${client.email}</td>
                <td>${client.telephone}</td>
                <td>
                    <a href="clients?action=edit&id=${client.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="clients?action=delete&id=${client.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce client ?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
