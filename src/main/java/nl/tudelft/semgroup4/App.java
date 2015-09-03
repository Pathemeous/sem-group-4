package nl.tudelft.semgroup4;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

import org.xml.sax.SAXException;

public class App extends Application implements EventHandler {	
	private Timeline timeline;
    private AnimationTimer timer;
    
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@SuppressWarnings("deprecation")
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
		Image background = new Image("startscreen.png");
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
		
		timeline = new Timeline(60);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                System.out.println(l/1000000000);
            }
 
        };
        
        timeline.play();
        timer.start();
//      //create a keyFrame, the keyValue is reached at time 2s
//        Duration duration = Duration.millis(2000);
//        //one can add a specific action when the keyframe is reached
//        EventHandler onFinished = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent t) {
//                
//                 
//            }
//        };
// 
//  KeyFrame keyFrame = new KeyFrame(duration, onFinished);
// 
//        //add the keyframe to the timeline
//        timeline.getKeyFrames().add(keyFrame);
// 
//        timeline.play();
//        timer.start();
//    
		
//		
//		final long startNanoTime = System.nanoTime();
//		 
//	    new AnimationTimer()
//	    {
//	        public void handle(long currentNanoTime)
//	        {
//	            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
//	 
//	            double x = 232 + 128 * Math.cos(t);
//	            double y = 232 + 128 * Math.sin(t);
//	            System.out.println("running");
//	        }
//	    }.start();
		

		theStage.show();
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}

}



