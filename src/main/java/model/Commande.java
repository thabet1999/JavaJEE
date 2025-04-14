package model;

import java.util.Date;

public class Commande {
    private int id;
    private int clientId;
    private int produitId;
    private int quantite;
    private Date date;

    public Commande() {}

    public Commande(int id, int clientId, int produitId, int quantite, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.produitId = produitId;
        this.quantite = quantite;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public int getProduitId() { return produitId; }
    public void setProduitId(int produitId) { this.produitId = produitId; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
