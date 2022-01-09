package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {
      int id;
      String naziv;
      Grad glavniGrad;
      int idGlavniGrad;

      SimpleIntegerProperty idProperty;
      SimpleStringProperty nazivProperty;
      SimpleObjectProperty<Grad> glavniGradProperty;
      SimpleIntegerProperty idglGradProperty;

    @Override
    public String toString() {
        return naziv;
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public SimpleIntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getNazivProperty() {
        return nazivProperty.get();
    }

    public SimpleStringProperty nazivPropertyProperty() {
        return nazivProperty;
    }

    public void setNazivProperty(String nazivProperty) {
        this.nazivProperty.set(nazivProperty);
    }

    public Grad getGlavniGradProperty() {
        return glavniGradProperty.get();
    }

    public SimpleObjectProperty<Grad> glavniGradPropertyProperty() {
        return glavniGradProperty;
    }

    public void setGlavniGradProperty(Grad glavniGradProperty) {
        this.glavniGradProperty.set(glavniGradProperty);
    }

    public int getIdglGradProperty() {
        return idglGradProperty.get();
    }

    public SimpleIntegerProperty idglGradPropertyProperty() {
        return idglGradProperty;
    }

    public void setIdglGradProperty(int idglGradProperty) {
        this.idglGradProperty.set(idglGradProperty);
    }

    public int getIdGlavniGrad() {
        return idGlavniGrad;
    }

    public void setIdGlavniGrad(int idGlavniGrad) {
        this.idGlavniGrad = idGlavniGrad;
    }

    public Drzava() {
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

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

    public Drzava(int id, String naziv, int idGlavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.idGlavniGrad = idGlavniGrad;
    }
}
