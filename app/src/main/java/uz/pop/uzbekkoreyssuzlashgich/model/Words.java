package uz.pop.uzbekkoreyssuzlashgich.model;

public class Words {
    int id, favourite;
    String kr, krill, krillPronounce, lotin, lotinPronounce;

    public Words(int id, int favourite, String kr, String krill, String krillPronounce, String lotin, String lotinPronounce) {
        this.id = id;
        this.favourite = favourite;
        this.kr = kr;
        this.krill = krill;
        this.krillPronounce = krillPronounce;
        this.lotin = lotin;
        this.lotinPronounce = lotinPronounce;
    }

    public Words() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getKr() {
        return kr;
    }

    public void setKr(String kr) {
        this.kr = kr;
    }

    public String getKrill() {
        return krill;
    }

    public void setKrill(String krill) {
        this.krill = krill;
    }

    public String getKrillPronounce() {
        return krillPronounce;
    }

    public void setKrillPronounce(String krillPronounce) {
        this.krillPronounce = krillPronounce;
    }

    public String getLotin() {
        return lotin;
    }

    public void setLotin(String lotin) {
        this.lotin = lotin;
    }

    public String getLotinPronounce() {
        return lotinPronounce;
    }

    public void setLotinPronounce(String lotinPronounce) {
        this.lotinPronounce = lotinPronounce;
    }
}
