package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GradController {
    public TextField fieldBrojStanovnika;
    public TextField fieldNaziv;
    Boolean  validacija = false;

    public DrzaveModel drzaveModel;
    public ChoiceBox<Drzava> choiceDrzava;
    public Grad grad;
    public Drzava drzava = new Drzava();

    public GradController(DrzaveModel drzaveModel,Grad grad) {
        this.drzaveModel = drzaveModel;
        this.grad = grad;
    }

    @FXML
    public void initialize() {
        choiceDrzava.getItems().addAll(drzaveModel.getDrzave());
        fieldNaziv.getStyleClass().add("poljeNijeIspravno");
        fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
        if(grad.getDrzava() != null) {
                fieldNaziv.textProperty().setValue(grad.getNaziv());
                fieldBrojStanovnika.textProperty().setValue(String.valueOf(grad.getBrojStanovnika()));
                choiceDrzava.setValue(grad.getDrzava());
                drzava = grad.getDrzava();
            }
        else if(drzaveModel.getDrzave().size() != 0) {
            drzava = drzaveModel.getDrzave().get(0);
            choiceDrzava.setValue(drzaveModel.getDrzave().get(0));
        }


        choiceDrzava.valueProperty().addListener((obs,oldValue,newValue)->{
            drzava = newValue;
        });

        fieldNaziv.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeIspravno");
                validacija = true;
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
                validacija = false;
            }
        });

        fieldBrojStanovnika.textProperty().addListener((obs, oldIme, newIme) -> {
            boolean ispravno = true;
            try {
                Integer stanovnici = Integer.parseInt(newIme);
                if(stanovnici <= 0) {
                    ispravno = false;
                    validacija = false;
                }
            }catch (NumberFormatException e){
                ispravno = false;
                validacija = false;
            }
            if (!newIme.isEmpty() && ispravno) {
                fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
                validacija  =true;
            } else {
                fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                validacija = false;
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldBrojStanovnika.getScene().getWindow();
        stage.close();

    }

    public void okAkcija(ActionEvent actionEvent) {
        if(validacija == false)
            return;
        grad.setNaziv(fieldNaziv.textProperty().getValue());
        grad.setDrzava(drzava);
        grad.setIdDrzave(grad.getDrzava().getId());
        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.textProperty().getValue()));
        Stage stage = (Stage) fieldBrojStanovnika.getScene().getWindow();
        stage.close();
    }
}
