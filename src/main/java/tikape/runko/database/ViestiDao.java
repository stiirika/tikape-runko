package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE ViestiId = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer ViestiId = rs.getInt("ViestiId");
        Integer AiheId = rs.getInt("AiheId");
        String Tunnus = rs.getString("Tunnus");
        Integer KeskusteluId = rs.getInt("KeskusteluId");
        String Sisalto = rs.getString("Sisalto");
        String PvmAika = rs.getString("PvmAika");
        Integer Lkm = rs.getInt("Lkm");

        Viesti o = new Viesti(ViestiId, AiheId, Tunnus, KeskusteluId, Sisalto, PvmAika, Lkm);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti");

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            Integer ViestiId = rs.getInt("ViestiId");
            Integer AiheId = rs.getInt("AiheId");
            String Tunnus = rs.getString("Tunnus");
            Integer KeskusteluId = rs.getInt("KeskusteluId");
            String Sisalto = rs.getString("Sisalto");
            String PvmAika = rs.getString("PvmAika");
            Integer Lkm = rs.getInt("Lkm");

            viestit.add(new Viesti(ViestiId, AiheId, Tunnus, KeskusteluId, Sisalto, PvmAika, Lkm));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestit;
    }

    public List<Viesti> findAll(Integer key1, Integer key2) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE AiheId = ? AND KeskusteluId = ?");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);

        ResultSet rs = stmt.executeQuery();
        List<Viesti> avaus = new ArrayList<>();
        while (rs.next()) {

            Integer ViestiId = rs.getInt("ViestiId");
            Integer AiheId = rs.getInt("AiheId");
            String Tunnus = rs.getString("Tunnus");
            Integer KeskusteluId = rs.getInt("KeskusteluId");
            String Sisalto = rs.getString("Sisalto");
            String PvmAika = rs.getString("PvmAika");
            Integer Lkm = rs.getInt("Lkm");

            avaus.add(new Viesti(ViestiId, AiheId, Tunnus, KeskusteluId, Sisalto, PvmAika, Lkm));
        }

        rs.close();
        stmt.close();
        connection.close();

        return avaus;
    }

    public int CountAll(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(ViestiId) AS arvo FROM Viesti WHERE AiheId = ? AND KeskusteluId = ?");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);

        ResultSet rs = stmt.executeQuery();
        Integer id = rs.getInt("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    public String selectDate(Integer key1, Integer key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT PvmAika AS arvo FROM Viesti WHERE AiheId = ? AND KeskusteluId = ? ORDER BY PvmAika DESC LIMIT 1");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);

        ResultSet rs = stmt.executeQuery();
        String id = rs.getString("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    @Override
    public void AddOne(Viesti avaus) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Viesti (ViestiId, AiheId, Tunnus, KeskusteluId, Sisalto, PvmAika, Lkm) VALUES (?,?,?,?,?,datetime('now','localtime'),?)");
        stmt.setInt(1, avaus.getViestiId());
        stmt.setInt(2, avaus.getAiheId());
        stmt.setString(3, avaus.getTunnus());
        stmt.setInt(4, avaus.getKeskusteluId());
        stmt.setString(5, avaus.getSisalto());
        //  stmt.setString(6, avaus.getPvmAika());

        stmt.execute();
        stmt.close();
        connection.close();

    }

    public int selectId() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(ViestiId) AS arvo FROM Viesti");

        ResultSet rs = stmt.executeQuery();
        Integer id = rs.getInt("arvo");

        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
