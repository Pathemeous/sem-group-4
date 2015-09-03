package nl.tudelft.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nl.tudelft.semgroup4.App;

public class HomeController implements Initializable, ControlledScreen {
	
	private ViewController viewController;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * Method for a new game. Loads the game screen, and then sets it as the current view. 
	 * @param event
	 */
	@FXML protected void start(ActionEvent event) {
		viewController.loadScreen(App.GAME, App.GAME_FXML);
		viewController.setScreen(App.GAME);
	}

	public void setViewController(ViewController controller) {
		this.viewController = controller;
	}
}
