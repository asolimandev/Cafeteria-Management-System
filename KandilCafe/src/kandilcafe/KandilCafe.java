package kandilcafe;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class KandilCafe extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage)throws ClassNotFoundException, SQLException {
        stage = primaryStage;
        stage.setTitle("KandilCafé Management System Version 1.0.0");
        stage.getIcons().add(new Image("logo1.png"));
        stage.setScene(firstGUI.S(stage));
        stage.show();
    }  
    public static void main(String[] args) {
        launch(args);
    }
}
