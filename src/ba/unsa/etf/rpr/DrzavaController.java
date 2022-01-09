package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DrzavaController {
    public TextField fieldNaziv;

    @FXML
    public void initialize() {
        fieldNaziv.getStyleClass().add("poljeNijeIspravno");

        fieldNaziv.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeIspravno");
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void okAkcija(ActionEvent actionEvent) {
    }
}
