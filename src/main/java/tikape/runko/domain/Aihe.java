package tikape.runko.domain;

import java.sql.Timestamp;

public class Aihe {

    private Integer AiheId;
    private String Aihe;
    private String Kuvaus;
    private String PvmAika;

    public Aihe(Integer AiheId, String Aihe, String Kuvaus, String PvmAika) {
        this.AiheId = AiheId;
        this.Aihe = Aihe;
        this.Kuvaus = Kuvaus;
        this.PvmAika = PvmAika;
    }

    public Integer getAiheId() {
        return AiheId;
    }

    public String getAihe() {
        return Aihe;
    }

    public String getKuvaus() {
        return Kuvaus;
    }

    public String getPvmAika() {
        return PvmAika;
    }

    public void setAiheId(Integer AiheId) {
        this.AiheId = AiheId;
    }

    public void setAihe(String Aihe) {
        this.Aihe = Aihe;
    }

    public void setKuvaus(String Kuvaus) {
        this.Kuvaus = Kuvaus;
    }

    public void setPvmAika(String PvmAika) {
        this.PvmAika = PvmAika;
    }

    
}
