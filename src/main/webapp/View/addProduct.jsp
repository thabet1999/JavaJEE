<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%
    Product produit = (Product) request.getAttribute("produit");
    boolean isEdit = (produit != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Modifier" : "Ajouter" %> un Produit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4"><%= isEdit ? "Modifier" : "Ajouter" %> un Produit</h2>
    <form action="products" method="post" class="border p-4 bg-white shadow-sm rounded">
        <input type="hidden" name="id" value="<%= isEdit ? produit.getId() : 0 %>">
        <div class="mb-3">
            <label class="form-label">Nom :</label>
            <input type="text" name="name" class="form-control" value="<%= isEdit ? produit.getName() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Quantité :</label>
            <input type="number" name="quantity" class="form-control" value="<%= isEdit ? produit.getQuantity() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Prix :</label>
            <input type="number" step="0.01" name="price" class="form-control" value="<%= isEdit ? produit.getPrice() : "" %>" required>
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <%= isEdit ? "Mettre à jour" : "Ajouter" %>
            </button>
            <a href="products" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
