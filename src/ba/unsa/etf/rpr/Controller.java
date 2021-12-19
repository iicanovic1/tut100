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
            for (Korisnik korisnik : model.getKorisnici())
                lista.getItems().add(korisnik);
            ime.setText(model.getTrenutniKorisnik().getIme());
            prezime.setText(model.getTrenutniKorisnik().getPrezime());
            email.setText(model.getTrenutniKorisnik().getEmail());
            korIme.setText(model.getTrenutniKorisnik().getKorisnickoIme());
            lozinka.setText(model.getTrenutniKorisnik().getPassword());
        }

        lista.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    System.out.println(newKorisnik.toString());
                    model.setTrenutniKorisnik(newKorisnik);
                    lista.refresh();
                    ime.setText(model.getTrenutniKorisnik().getIme());
                    prezime.setText(model.getTrenutniKorisnik().getPrezime());
                    email.setText(model.getTrenutniKorisnik().getEmail());
                    korIme.setText(model.getTrenutniKorisnik().getKorisnickoIme());
                    lozinka.setText(model.getTrenutniKorisnik().getPassword());
                }
        );

        ime.textProperty().addListener(
                (obs, staroIme, novoIme) -> {
                    model.getTrenutniKorisnik().setIme(novoIme);
                    lista.refresh();
                }
        );

        prezime.textProperty().addListener(
                (obs, staroPrezime, novoPrezime) -> {
                    model.getTrenutniKorisnik().setPrezime(novoPrezime);
                    lista.refresh();
                }
        );
        email.textProperty().addListener(
                (obs, staro, novo) -> {
                    model.getTrenutniKorisnik().setEmail(novo);
                }
        );
        lozinka.textProperty().addListener(
                (obs, staro, novo) -> {
                    model.getTrenutniKorisnik().setPassword(novo);
                }
        );
        korIme.textProperty().addListener(
                (obs, staro, novo) -> {
                    model.getTrenutniKorisnik().setKorisnickoIme(novo);
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
