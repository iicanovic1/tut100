package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GradoviModel {
    private ObservableList<Grad> gradovi = FXCollections.observableArrayList();

    public ObservableList<Grad> getGradovi() {
        return gradovi;
    }

    public void dodajGrad(Grad grad) {
        gradovi.add(grad);
    }

    public ObservableList<Grad> dodajGradove(ArrayList<Grad> gradovi) {
        this.gradovi.clear();
        for (var grad : gradovi) {
            dodajGrad(grad);
        }
        return this.gradovi;
    }

    public void setGradovi(ObservableList<Grad> gradovi) {
        this.gradovi = gradovi;
    }
}
