package nl.tudelft.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import nl.tudelft.semgroup4.App;

public class GameController implements Initializable, ControlledScreen {

	private ViewController controller;
	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Game controller initiliazed");
	}

	public void setViewController(ViewController controller) {
		this.controller = controller;
	}
	
	@FXML protected void back(ActionEvent event) {
		controller.setScreen(App.HOME);
	}

}
