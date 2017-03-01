package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Kayttaja;

public class KayttajaDao implements Dao<Kayttaja, Integer> {

    private Database database;

    public KayttajaDao(Database database) {
        this.database = database;
    }

    public Kayttaja findOne(String key1, String key2) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kayttaja WHERE Tunnus = ? AND Salasana = ?");
        stmt.setObject(1, key1);
        stmt.setObject(2, key2);        

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String Tunnus = rs.getString("Tunnus");
        String Etunimi = rs.getString("Etunimi");
        String Sukunimi = rs.getString("Sukunimi");
        String Osoite = rs.getString("Osoite");
        Integer Puhelinnumero = rs.getInt("Puhelinnumero");
        String Sahkoposti = rs.getString("Sahkoposti");
        String PvmAika = rs.getString("PvmAika");

        Kayttaja o = new Kayttaja(Tunnus, Etunimi, Sukunimi, Osoite, Puhelinnumero, Sahkoposti, PvmAika);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Kayttaja> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kayttaja");

        ResultSet rs = stmt.executeQuery();
        List<Kayttaja> kayttajat = new ArrayList<>();
        while (rs.next()) {

            String Tunnus = rs.getString("Tunnus");
            String Etunimi = rs.getString("Etunimi");
            String Sukunimi = rs.getString("Sukunimi");
            String Osoite = rs.getString("Osoite");
            Integer Puhelinnumero = rs.getInt("Puhelinnumero");
            String Sahkoposti = rs.getString("Sahkoposti");
            String PvmAika = rs.getString("PvmAika");

            kayttajat.add(new Kayttaja(Tunnus, Etunimi, Sukunimi, Osoite, Puhelinnumero, Sahkoposti, PvmAika));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kayttajat;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }



    @Override
    public void AddOne(Kayttaja kayttaja) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Kayttaja (Tunnus, Salasana, Nimi, Osoite, Puhelinnumero, Sahkoposti, PvmAika) VALUES (?,?,?,?,?,?,datetime('now','localtime'))");
        stmt.setString(1, kayttaja.getTunnus());
        stmt.setString(2, kayttaja.getSalasana());
        stmt.setString(3, kayttaja.getNimi());
        stmt.setString(4, kayttaja.getOsoite());
        stmt.setInt(5, kayttaja.getPuhelinnumero());        
        stmt.setString(6, kayttaja.getSahkoposti());        
    //    stmt.setString(7, kayttaja.getPvmAika());

        stmt.execute();
        stmt.close();
        connection.close();
    }



    @Override
    public Kayttaja findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int CountAll(Integer key1, Integer key2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int selectId(Integer key1, Integer key2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
