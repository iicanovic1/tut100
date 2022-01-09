package ba.unsa.etf.rpr;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad {
    int id;
    String naziv;
    int brojStanovnika;
    Drzava drzava;
    int idDrzave;

    private SimpleIntegerProperty idProperty;
    private SimpleStringProperty nazivStringProprety;
    private SimpleIntegerProperty brojStanovnikaProperty;
    private SimpleObjectProperty<Drzava> drzavaProperty;

    public int getIdProperty() {
        return idProperty.get();
    }

    public SimpleIntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }


    public String getNazivStringProprety() {
        return nazivStringProprety.get();
    }

    public SimpleStringProperty nazivStringPropretyProperty() {
        return nazivStringProprety;
    }

    public void setNazivStringProprety(String nazivStringProprety) {
        this.nazivStringProprety.set(nazivStringProprety);
    }

    public int getBrojStanovnikaProperty() {
        return brojStanovnikaProperty.get();
    }

    public SimpleIntegerProperty brojStanovnikaPropertyProperty() {
        return brojStanovnikaProperty;
    }

    public void setBrojStanovnikaProperty(int brojStanovnikaProperty) {
        this.brojStanovnikaProperty.set(brojStanovnikaProperty);
    }

    public String  getDrzavaProperty() {
        return drzava.getNaziv();
    }

    public SimpleObjectProperty<Drzava> drzavaPropertyProperty() {
        return drzavaProperty;
    }

    public void setDrzavaProperty(Drzava drzavaProperty) {
        this.drzavaProperty.set(drzavaProperty);
    }



    public int getIdDrzave() {
        return idDrzave;
    }

    public void setIdDrzave(int idDrzave) {
        this.idDrzave = idDrzave;
    }


    public Grad() {
        this.idProperty = new SimpleIntegerProperty(0);
        this.brojStanovnikaProperty = new SimpleIntegerProperty(0);
        this.nazivStringProprety = new SimpleStringProperty("");
    }

    public Grad(int id, String naziv, int brojStanovnika, int idDrzave) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.idDrzave = idDrzave;

        this.idProperty = new SimpleIntegerProperty(id);
        this.brojStanovnikaProperty = new SimpleIntegerProperty(brojStanovnika);
        this.nazivStringProprety = new SimpleStringProperty(naziv);
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
