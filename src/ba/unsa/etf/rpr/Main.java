package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }
    private KorisnikModel model =  new KorisnikModel();

    @Override
    public void start(Stage stage) throws Exception {
        model.napuni();
        Controller controller = new Controller(model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mian.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        stage.setTitle("Korisnici");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.toFront();
        stage.show();
    }
}
