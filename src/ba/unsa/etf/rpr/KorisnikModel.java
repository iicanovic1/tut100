package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisnikModel {
    private ObservableList<Korisnik> korisnici =  FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    public void napuni(){
        korisnici.add(new Korisnik("Ismail","Icanovic","iicanovic@etf.unsa.ba","iicanovic1","kaicaipeepie"));
        korisnici.add(new Korisnik("Neko","Nekić","nekoNekić@etf.unsa.ba","nekonekic1","nekoafasdfgv"));
        korisnici.add(new Korisnik("Pero","Perić","peroperic1@etf.unsa.ba","peroperic1","fbaserberebre"));
        trenutniKorisnik.set(korisnici.get(0));
    }

    public void dodajKorisnika(Korisnik novi){
        korisnici.add(novi);
        trenutniKorisnik.set(korisnici.get(korisnici.size()-1));
    }
    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }
}
