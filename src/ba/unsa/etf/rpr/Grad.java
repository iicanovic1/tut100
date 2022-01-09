package ba.unsa.etf.rpr;


public class Grad {
    int id;
    String naziv;
    int brojStanovnika;
    Drzava drzava;
    int idDrzave;

    public int getIdDrzave() {
        return idDrzave;
    }

    public void setIdDrzave(int idDrzave) {
        this.idDrzave = idDrzave;
    }

    public Grad() {
    }

    public Grad(int id, String naziv, int brojStanovnika, int idDrzave) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.idDrzave = idDrzave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
