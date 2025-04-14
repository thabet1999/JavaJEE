package dao;

import model.Commande;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private Connection conn;

    public CommandeDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCommande(Commande c) {
        String sql = "INSERT INTO commandes (client_id, produit_id, quantite, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getClientId());
            ps.setInt(2, c.getProduitId());
            ps.setInt(3, c.getQuantite());
            ps.setDate(4, new java.sql.Date(c.getDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCommande(Commande c) {
        String sql = "UPDATE commandes SET client_id=?, produit_id=?, quantite=?, date=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getClientId());
            ps.setInt(2, c.getProduitId());
            ps.setInt(3, c.getQuantite());
            ps.setDate(4, new java.sql.Date(c.getDate().getTime()));
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCommande(int id) {
        String sql = "DELETE FROM commandes WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Commande getCommande(int id) {
        Commande c = null;
        String sql = "SELECT * FROM commandes WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Commande(
                    rs.getInt("id"),
                    rs.getInt("client_id"),
                    rs.getInt("produit_id"),
                    rs.getInt("quantite"),
                    rs.getDate("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Commande> listCommandes() {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT * FROM commandes";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Commande c = new Commande(
                    rs.getInt("id"),
                    rs.getInt("client_id"),
                    rs.getInt("produit_id"),
                    rs.getInt("quantite"),
                    rs.getDate("date")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
