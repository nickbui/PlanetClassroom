package planetclassroomsprint;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;




/**
 *
 * @author Nicholas
 */
public class PlanetClassroomSprint extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
      Parent root =FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
      
      Scene primaryScence = new Scene(root);
      stage.setTitle("Welcome");
      stage.setScene(primaryScence);
      stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
    
    
    
}

