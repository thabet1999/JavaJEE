<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Client" %>
<%
    Client client = (Client) request.getAttribute("client");
    boolean isEdit = (client != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Modifier" : "Ajouter" %> un Client</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4"><%= isEdit ? "Modifier" : "Ajouter" %> un Client</h2>
    <form action="clients" method="post" class="border p-4 bg-white shadow-sm rounded">
        <input type="hidden" name="id" value="<%= isEdit ? client.getId() : 0 %>">
        <div class="mb-3">
            <label class="form-label">Nom :</label>
            <input type="text" name="nom" class="form-control" value="<%= isEdit ? client.getNom() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email :</label>
            <input type="email" name="email" class="form-control" value="<%= isEdit ? client.getEmail() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Téléphone :</label>
            <input type="text" name="telephone" class="form-control" value="<%= isEdit ? client.getTelephone() : "" %>" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <%= isEdit ? "Mettre à jour" : "Ajouter" %>
            </button>
            <a href="clients" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
