package nl.tudelft.semgroup4;

import java.io.IOException;

import org.xml.sax.SAXException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage theStage) throws SAXException,IOException {

		//requestFocus();
		theStage.setTitle("Bubble trouble");

		Pane root = new Pane();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );

		Canvas layer1 = new Canvas( 1200, 800);
		Canvas layer2 = new Canvas( 1200, 800);
		root.getChildren().add( layer1 );

		//create background image
		Image background = new Image("/nl/tudelft/semgroup4/startscreen.png");
		ImageView imgView = new ImageView();
		imgView.setImage(background);
		imgView.setFitHeight(800);
		imgView.setFitWidth(1200);
		root.getChildren().add(imgView);     


		root.getChildren().add( layer2 );     
		GraphicsContext gc = layer2.getGraphicsContext2D();
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);
		gc.setFont(Font.font("Comic Sans", FontWeight.BOLD, 70));
		gc.fillText("Bubble Trouble", 350, 175);
		gc.strokeText("Bubble Trouble", 350, 175);
		
		final long startNanoTime = System.nanoTime();
		 
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
	 
	            double x = 232 + 128 * Math.cos(t);
	            double y = 232 + 128 * Math.sin(t);
	            System.out.println("running");
	        }
	    }.start();
		

		theStage.show();
	}

}
