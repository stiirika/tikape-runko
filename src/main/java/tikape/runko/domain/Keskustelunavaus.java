package tikape.runko.domain;

import java.sql.Timestamp;

public class Keskustelunavaus {

    private Integer KeskusteluId;
    private Integer AiheId;
    private String Tunnus;
    private String Otsikko;
    private String PvmAika;

    public Keskustelunavaus(Integer KeskusteluId, Integer AiheId, String Tunnus, String Otsikko, String PvmAika) {
        this.KeskusteluId = KeskusteluId;
        this.AiheId = AiheId;
        this.Tunnus = Tunnus;
        this.Otsikko = Otsikko;
        this.PvmAika = PvmAika;
    }

    public Integer getKeskusteluId() {
        return KeskusteluId;
    }

    public Integer getAiheId() {
        return AiheId;
    }

    public String getTunnus() {
        return Tunnus;
    }

    public String getOtsikko() {
        return Otsikko;
    }

    public String getPvmAika() {
        return PvmAika;
    }

    public void setKeskusteluId(Integer KeskusteluId) {
        this.KeskusteluId = KeskusteluId;
    }

    public void setAiheId(Integer AiheId) {
        this.AiheId = AiheId;
    }

    public void setTunnus(String Tunnus) {
        this.Tunnus = Tunnus;
    }

    public void setOtsikko(String Otsikko) {
        this.Otsikko = Otsikko;
    }

    public void setPvmAika(String PvmAika) {
        this.PvmAika = PvmAika;
    }
}
