package tikape.runko.domain;

import java.sql.Timestamp;

public class Kayttaja {

    private String Tunnus;
    private String Salasana;
    private String Nimi;
    private String Osoite;
    private Integer Puhelinnumero;
    private String Sahkoposti;
    private String PvmAika;

    public Kayttaja(String Tunnus, String Salasana, String Nimi, String Osoite, Integer Puhelinnumero, String Sahkoposti, String PvmAika) {
        this.Tunnus = Tunnus;
        this.Salasana = Salasana;
        this.Nimi = Nimi;
        this.Osoite = Osoite;
        this.Puhelinnumero = Puhelinnumero;
        this.Sahkoposti = Sahkoposti;
        this.PvmAika = PvmAika;
    }

    public String getTunnus() {
        return Tunnus;
    }

    public String getSalasana() {
        return Salasana;
    }

    public String getNimi() {
        return Nimi;
    }

    public String getOsoite() {
        return Osoite;
    }

    public Integer getPuhelinnumero() {
        return Puhelinnumero;
    }

    public String getSahkoposti() {
        return Sahkoposti;
    }

    public String getPvmAika() {
        return PvmAika;
    }

    public void setTunnus(String Tunnus) {
        this.Tunnus = Tunnus;
    }

    public void setSalasana(String Salasana) {
        this.Salasana = Salasana;
    }

    public void setNimi(String Nimi) {
        this.Nimi = Nimi;
    }

    public void setOsoite(String Osoite) {
        this.Osoite = Osoite;
    }

    public void setPuhelinnumero(Integer Puhelinnumero) {
        this.Puhelinnumero = Puhelinnumero;
    }

    public void setSahkoposti(String Sahkoposti) {
        this.Sahkoposti = Sahkoposti;
    }

    public void setPvmAika(String PvmAika) {
        this.PvmAika = PvmAika;
    }
}
