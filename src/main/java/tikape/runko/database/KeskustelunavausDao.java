package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Keskustelunavaus;

public class KeskustelunavausDao implements Dao<Keskustelunavaus, Integer> {

    private Database database;

    public KeskustelunavausDao(Database database) {
        this.database = database;
    }

    @Override
    public Keskustelunavaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelunavaus WHERE KeskusteluId = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer KeskusteluId = rs.getInt("KeskusteluId");
        Integer AiheId = rs.getInt("AiheId");
        String Tunnus = rs.getString("Tunnus");
        String Otsikko = rs.getString("Otsikko");
        String PvmAika = rs.getString("PvmAika");
        Integer Lkm = rs.getInt("Lkm");

        Keskustelunavaus o = new Keskustelunavaus(KeskusteluId, AiheId, Tunnus, Otsikko, PvmAika, Lkm);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    public List<Keskustelunavaus> findAll(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelunavaus WHERE AiheId = ? ORDER BY KeskusteluId ASC LIMIT 10");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Keskustelunavaus> avaus = new ArrayList<>();
        while (rs.next()) {

            Integer KeskusteluId = rs.getInt("KeskusteluId");
            Integer AiheId = rs.getInt("AiheId");
            String Tunnus = rs.getString("Tunnus");
            String Otsikko = rs.getString("Otsikko");
            String PvmAika = rs.getString("PvmAika");
            Integer Lkm = rs.getInt("Lkm");

            avaus.add(new Keskustelunavaus(KeskusteluId, AiheId, Tunnus, Otsikko, PvmAika, Lkm));
        }

        rs.close();
        stmt.close();
        connection.close();

        return avaus;
    }

    @Override
    public void AddOne(Keskustelunavaus avaus) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Keskustelunavaus (KeskusteluId, AiheId, Tunnus, Otsikko, PvmAika, Lkm) VALUES (?,?,?,?,datetime('now','localtime'),?)");
        stmt.setInt(1, avaus.getKeskusteluId());
        stmt.setInt(2, avaus.getAiheId());
        stmt.setString(3, avaus.getTunnus());
        stmt.setString(4, avaus.getOtsikko());
        //    stmt.setString(5, avaus.getPvmAika());

        stmt.execute();
        stmt.close();
        connection.close();

    }

    @Override
    public int selectId() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(KeskusteluId) AS arvo FROM Keskustelunavaus");

        ResultSet rs = stmt.executeQuery();
        Integer id = rs.getInt("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    @Override
    public void update(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE Keskustelunavaus SET Lkm = ? WHERE KeskusteluID = ?");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);

        stmt.execute();
        stmt.close();
        connection.close();
    }
    
        public int CountAll(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(KeskusteluId) AS arvo FROM Keskustelunavaus WHERE AiheId = ?");
        stmt.setObject(1, key1);

        ResultSet rs = stmt.executeQuery();
        Integer id = rs.getInt("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    @Override
    public List<Keskustelunavaus> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectDate(Integer key1, Integer key2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
