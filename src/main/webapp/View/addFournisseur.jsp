<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Fournisseur" %>
<%
    Fournisseur fournisseur = (Fournisseur) request.getAttribute("fournisseur");
    boolean isEdit = (fournisseur != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Modifier" : "Ajouter" %> un Fournisseur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4"><%= isEdit ? "Modifier" : "Ajouter" %> un Fournisseur</h2>
    <form action="fournisseurs" method="post" class="border p-4 bg-white shadow-sm rounded">
        <input type="hidden" name="id" value="<%= isEdit ? fournisseur.getId() : 0 %>">
        <div class="mb-3">
            <label class="form-label">Nom :</label>
            <input type="text" name="nom" class="form-control" value="<%= isEdit ? fournisseur.getNom() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Adresse :</label>
            <input type="text" name="adresse" class="form-control" value="<%= isEdit ? fournisseur.getAdresse() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Téléphone :</label>
            <input type="text" name="telephone" class="form-control" value="<%= isEdit ? fournisseur.getTelephone() : "" %>" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <%= isEdit ? "Mettre à jour" : "Ajouter" %>
            </button>
            <a href="fournisseurs" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
