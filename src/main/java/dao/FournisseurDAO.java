package dao;

import model.Fournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {
    private Connection connection;

    public FournisseurDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFournisseur(Fournisseur f) {
        String sql = "INSERT INTO fournisseurs (nom, adresse, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getNom());
            stmt.setString(2, f.getAdresse());
            stmt.setString(3, f.getTelephone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFournisseur(Fournisseur f) {
        String sql = "UPDATE fournisseurs SET nom=?, adresse=?, telephone=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getNom());
            stmt.setString(2, f.getAdresse());
            stmt.setString(3, f.getTelephone());
            stmt.setInt(4, f.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFournisseur(int id) {
        String sql = "DELETE FROM fournisseurs WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Fournisseur getFournisseur(int id) {
        Fournisseur f = null;
        String sql = "SELECT * FROM fournisseurs WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setAdresse(rs.getString("adresse"));
                f.setTelephone(rs.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public List<Fournisseur> listFournisseurs() {
        List<Fournisseur> list = new ArrayList<>();
        String sql = "SELECT * FROM fournisseurs";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Fournisseur f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setAdresse(rs.getString("adresse"));
                f.setTelephone(rs.getString("telephone"));
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
