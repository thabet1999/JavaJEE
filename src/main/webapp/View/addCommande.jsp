<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Commande" %>
<%
    Commande commande = (Commande) request.getAttribute("commande");
    boolean isEdit = (commande != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Modifier" : "Ajouter" %> une Commande</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4"><%= isEdit ? "Modifier" : "Ajouter" %> une Commande</h2>
    <form action="commandes" method="post" class="border p-4 bg-white shadow-sm rounded">
        <input type="hidden" name="id" value="<%= isEdit ? commande.getId() : 0 %>">
        <div class="mb-3">
            <label class="form-label">Client ID :</label>
            <input type="number" name="clientId" class="form-control" value="<%= isEdit ? commande.getClientId() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Produit ID :</label>
            <input type="number" name="produitId" class="form-control" value="<%= isEdit ? commande.getProduitId() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Quantité :</label>
            <input type="number" name="quantite" class="form-control" value="<%= isEdit ? commande.getQuantite() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Date :</label>
            <input type="date" name="date" class="form-control" value="<%= isEdit ? commande.getDate().toString() : "" %>" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <%= isEdit ? "Mettre à jour" : "Ajouter" %>
            </button>
            <a href="commandes" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
