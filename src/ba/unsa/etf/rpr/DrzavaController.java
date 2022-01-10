package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;
    Drzava drzava;
    GradoviModel gradoviModel = new GradoviModel();
    Grad grad = new Grad();
    Boolean  validacija = false;
    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.drzava = drzava;
        gradoviModel.dodajGradove(gradovi);
    }

    public DrzavaController(Drzava dodajDrzavu, GradoviModel gradoviModel) {
        this.drzava = dodajDrzavu;
        this.gradoviModel= gradoviModel;
    }

    @FXML
    public void initialize() {
        choiceGrad.getItems().addAll(gradoviModel.getGradovi());
        fieldNaziv.getStyleClass().add("poljeNijeIspravno");
        if(gradoviModel.getGradovi().size() != 0) {
            grad = gradoviModel.getGradovi().get(0);
            choiceGrad.setValue(gradoviModel.getGradovi().get(0));
        }

        choiceGrad.valueProperty().addListener((obs,oldValue,newValue)->{
            grad = newValue;
        });

        fieldNaziv.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeIspravno");
                validacija  =true;
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
                validacija  =false;
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void okAkcija(ActionEvent actionEvent) {
        if(validacija == false )
            return;

        if(drzava == null) drzava = new Drzava();
        drzava.setNaziv(fieldNaziv.textProperty().getValue());
        drzava.setGlavniGrad(grad);
        drzava.setIdGlavniGrad(grad.getId());

        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
