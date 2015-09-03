package nl.tudelft.semgroup4;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.viewControllers.ViewController;
		
public class App extends Application {	
	public static final String GAME = "game";
	public static final String GAME_FXML = "/views/game.fxml";
	public static final String HOME = "home";
	public static final String HOME_FXML = "/views/home.fxml";
    
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		ViewController viewController = new ViewController();
		
		viewController.loadScreen(App.HOME, App.HOME_FXML);
		viewController.setScreen(App.HOME);
		
		Group root = new Group(viewController);
		Scene scene = new Scene(root, 600, 400);
		stage.setTitle("Bubble trouble");
		stage.setScene(scene);
		stage.show();
		
		viewController.prefWidthProperty().bind(scene.widthProperty());
        viewController.prefHeightProperty().bind(scene.heightProperty());
	}
}



