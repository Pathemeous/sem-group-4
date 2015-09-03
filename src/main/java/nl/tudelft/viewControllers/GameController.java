package nl.tudelft.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class GameController implements Initializable, ControlledScreen {

	private ViewController controller;
	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Game controller initiliazed");
	}

	public void setViewController(ViewController controller) {
		this.controller = controller;
	}

}
