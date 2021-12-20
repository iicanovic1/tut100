package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{

    public ListView<Korisnik> lista;
    public TextField ime;
    public TextField prezime;
    public TextField email;
    public TextField korIme;
    public PasswordField lozinka;
    public Button dodaj;
    public Button kraj;

    public KorisnikModel model = null;

    public Controller(KorisnikModel model) {
        this.model = model;
    }

    @FXML
    public void initialize(){
        if(model != null) {
            ime.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
            prezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
            email.textProperty().bindBidirectional(model.getTrenutniKorisnik().emailProperty());
            lozinka.textProperty().bindBidirectional(model.getTrenutniKorisnik().passwordProperty());
            korIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().korisnickoImeProperty());

            for (Korisnik korisnik : model.getKorisnici())
                lista.getItems().add(korisnik);
        }

        lista.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    System.out.println(newKorisnik.toString());
                    model.setTrenutniKorisnik(newKorisnik);
                }
        );
        model.trenutniKorisnikProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    ime.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                    ime.textProperty().bindBidirectional(newKorisnik.imeProperty());
                    prezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                    prezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                    email.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                    email.textProperty().bindBidirectional(newKorisnik.emailProperty());
                    lozinka.textProperty().unbindBidirectional(oldKorisnik.passwordProperty());
                    lozinka.textProperty().bindBidirectional(newKorisnik.passwordProperty());
                    korIme.textProperty().unbindBidirectional(oldKorisnik.korisnickoImeProperty());
                    korIme.textProperty().bindBidirectional(newKorisnik.korisnickoImeProperty());
                }
        );
        ime.textProperty().addListener(
                (obs, staroIme, novoIme) -> {
                    lista.refresh();
                }
        );

        prezime.textProperty().addListener(
                (obs, staroPrezime, novoPrezime) -> {
                    lista.refresh();
                }
        );


    }

    public void listaAction(ListView.EditEvent editEvent) {
    }

    public void dodajAction(ActionEvent actionEvent) {
        Korisnik novi = new Korisnik("","","","","");
        model.dodajKorisnika(novi);
        System.out.println(model.getTrenutniKorisnik().toString());
        lista.getItems().add(novi);
        lista.refresh();
        lista.getSelectionModel().select(novi);
    }

    public void KrajAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ime.getScene().getWindow();
        stage.close();
    }
}
