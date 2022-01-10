package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GlavnaController {

    public TableView<Grad> tableViewGradovi;
    private GeografijaDAO dao = GeografijaDAO.getInstance();
    private  GradoviModel gradoviModel = new GradoviModel();
    private  DrzaveModel drzaveModel = new DrzaveModel();
    Grad grad = new Grad();

    @FXML
    public TableColumn<GradoviModel, Integer> colGradId;

    @FXML
    public TableColumn<GradoviModel, String> colGradNaziv;

    @FXML
    public TableColumn<GradoviModel, Integer> colGradStanovnika;

    @FXML
    public TableColumn<GradoviModel, String> colGradDrzava;

    @FXML
    public  void  initialize(){
        drzaveModel.dodajDrzave(dao.drzave());

        colGradId.setCellValueFactory(new PropertyValueFactory<>("IdProperty"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<>("NazivStringProprety"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory<>("BrojStanovnikaProperty"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<>("DrzavaProperty"));
        prikaziTabelu();

        tableViewGradovi.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                    if(newValue == null)
                        return;
                    grad = newValue;
                });
    }

    public void dodajGrad(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        Grad dodajGrad = new Grad();
        GradController controler = new GradController(dodajGrad,drzaveModel);
        loader.setController(controler);
        Parent root = loader.load();
        stage.setTitle("Dodavanje grada");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.toFront();
        stage.show();
        stage.setOnHiding(e -> {
            if(dodajGrad.getDrzava() != null)
            {
                dao.dodajGrad(dodajGrad);
                prikaziTabelu();
            }
            else return;
        });
    }

    public void dodajDrzavu(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
        Drzava dodajDrzavu = new Drzava();
        DrzavaController controler = new DrzavaController(dodajDrzavu,gradoviModel);
        loader.setController(controler);
        Parent root = loader.load();
        stage.setTitle("Dodavanje drzave");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.toFront();
        stage.show();
        stage.setOnHiding(e -> {
            if(dodajDrzavu.getGlavniGrad() != null)
            {
                dao.dodajDrzavu(dodajDrzavu);
                dodajDrzavu.getGlavniGrad().setDrzava( dao.nadjiDrzavu(dodajDrzavu.getNaziv()));
                dao.izmijeniGrad(dodajDrzavu.getGlavniGrad());
                prikaziTabelu();
            }
            else return;
        });
    }

    public void izmijeniGrad(ActionEvent actionEvent)throws IOException  {
        if(tableViewGradovi.getSelectionModel().getSelectedCells() == null)
            return;
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        GradController controler = new GradController(grad,drzaveModel);
        loader.setController(controler);
        Parent root = loader.load();
        stage.setTitle("Izmjena grada");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.toFront();
        stage.show();
        stage.setOnHiding(e -> {
            dao.izmijeniGrad(grad);
            prikaziTabelu();
        });
    }

    public void obrisiGrad(ActionEvent actionEvent) {

    }

    public void prikaziTabelu(){
        ObservableList<Grad> gradoviZaSortiranje =  gradoviModel.dodajGradove(dao.gradovi());
        Collections.sort(gradoviZaSortiranje, Comparator.comparing(Grad::getId));
        tableViewGradovi.setItems(gradoviZaSortiranje);
    }
}
