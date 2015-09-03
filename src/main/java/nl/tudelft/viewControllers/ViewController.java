package nl.tudelft.viewControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;

public class ViewController extends StackPane {

	private HashMap<String, Node> screens = new HashMap<>();
	
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}
	
	/**
	 * Loads a screen, and adds it to a HashMap.
	 * @param name : name of the screen by which the screen can be found in the HashMap
	 * @param resource : the location of the fxml file
	 * @return true if the screen has been loaded
	 */
	public boolean loadScreen(String name, String resource){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent loadScreen = loader.load();
			ControlledScreen viewController = loader.getController();
			viewController.setViewController(this);
			
			addScreen(name, loadScreen);
			return true;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean setScreen(String name){
		if(screens.get(name) != null){
			
			if(!getChildren().isEmpty()){
				getChildren().remove(0);
				getChildren().add(0, screens.get(name));
				
			} else{
				getChildren().add(screens.get(name));
			}
			return true;
		} 
		else{
			System.out.println("Screen hasn't been loaded!\n");
			return false;
		}
	}
}
