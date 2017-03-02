package tikape.runko.domain;

import java.sql.Timestamp;

public class Viesti {

    private Integer ViestiId;
    private Integer AiheId;
    private String Tunnus;
    private Integer KeskusteluId;
    private String Sisalto;
    private String PvmAika;
    private Integer Lkm;    

    public Viesti(Integer ViestiId, Integer AiheId, String Tunnus, Integer KeskusteluId, String Sisalto, String PvmAika, Integer Lkm) {
        this.ViestiId = ViestiId;
        this.AiheId = AiheId;
        this.Tunnus = Tunnus;
        this.KeskusteluId = KeskusteluId;
        this.Sisalto = Sisalto;
        this.PvmAika = PvmAika;
        this.Lkm=Lkm;
    }

    public Integer getViestiId() {
        return ViestiId;
    }

    public Integer getAiheId() {
        return AiheId;
    }

    public String getTunnus() {
        return Tunnus;
    }

    public Integer getKeskusteluId() {
        return KeskusteluId;
    }

    public String getSisalto() {
        return Sisalto;
    }

    public String getPvmAika() {
        return PvmAika;
    }
    
     public Integer getLkm() {
        return Lkm;
    }   

    public void setViestiId(Integer ViestiId) {
        this.ViestiId = ViestiId;
    }

    public void setAiheId(Integer AiheId) {
        this.AiheId = AiheId;
    }

    public void setTunnus(String Tunnus) {
        this.Tunnus = Tunnus;
    }

    public void setKeskusteluId(Integer KeskusteluId) {
        this.KeskusteluId = KeskusteluId;
    }

    public void setSisalto(String Sisalto) {
        this.Sisalto = Sisalto;
    }

    public void setPvmAika(String PvmAika) {
        this.PvmAika = PvmAika;
    }
    
    public void setLkm(Integer Lkm) {
        this.Lkm = Lkm;
    }    
}
