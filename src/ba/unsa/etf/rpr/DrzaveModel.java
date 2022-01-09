package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DrzaveModel {
    private ObservableList<Drzava> drzave = FXCollections.observableArrayList();
    public ObservableList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ObservableList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public void dodajDrzavu(Drzava drzava) {
        drzave.add(drzava);
    }

    public ObservableList<Drzava> dodajDrzave(ArrayList<Drzava> drzave) {
        this.drzave.clear();
        for (var drzava : drzave) {
            dodajDrzavu(drzava);
        }
        return this.drzave;
    }

}
