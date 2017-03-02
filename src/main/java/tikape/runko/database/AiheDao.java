package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aihe;

public class AiheDao implements Dao<Aihe, Integer> {

    private Database database;

    public AiheDao(Database database) {
        this.database = database;
    }

    @Override
    public Aihe findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE AiheId = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer AiheId = rs.getInt("AiheId");
        String Aihe = rs.getString("Aihe");
        String Kuvaus = rs.getString("Kuvaus");
        String PvmAika = rs.getString("PvmAika");
        Integer Lkm = rs.getInt("Lkm");

        Aihe o = new Aihe(AiheId, Aihe, Kuvaus, PvmAika, Lkm);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe ORDER BY Aihe");

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheet = new ArrayList<>();
        while (rs.next()) {
            Integer AiheId = rs.getInt("AiheId");
            String Aihe = rs.getString("Aihe");
            String Kuvaus = rs.getString("Kuvaus");
            String PvmAika = rs.getString("PvmAika");
            Integer Lkm = rs.getInt("Lkm");

            aiheet.add(new Aihe(AiheId, Aihe, Kuvaus, PvmAika, Lkm));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aiheet;
    }

    @Override
    public void update(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE Aihe SET Lkm = ? WHERE AiheID = ?");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    public int CountAll(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(AiheId) AS arvo FROM Aihe WHERE AiheId = ?");
        stmt.setObject(1, key1);

        ResultSet rs = stmt.executeQuery();
        Integer id = rs.getInt("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    @Override
    public void AddOne(Aihe key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int selectId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectDate(Integer key1, Integer key2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
