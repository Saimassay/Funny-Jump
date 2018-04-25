package game;

import java.io.FileNotFoundException;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Monster extends Pane{
	private Circle monster;
	
	public  Monster (Pane gamePane) throws FileNotFoundException{	
		//creating circle
		monster = new Circle(25);
		//setting color
		monster.setFill(Color.RED);
		//setting center of circle in x,y
		monster.setCenterX(400);
		monster.setCenterY(400);
		//calling method transition
		transition();
		//adding monster to game pane
        gamePane.getChildren().add(monster);
        
	}
	public TranslateTransition transition() {
		//creating transition for monster
		TranslateTransition transition = new TranslateTransition();
		//setting duration
		transition.setDuration(Duration.millis(3000));
		//setting transition to monster
		transition.setNode(monster);
		//setting coordinates for transition
		transition.setByX(Math.random()-100);
		//setting number of cycle
		transition.setCycleCount(1000);
		transition.setAutoReverse(true);
        transition.play();
		return transition;
        
	}
	//method getter
		public double getX(){

			return monster.getCenterX();

		}
		//method getter
		public double getY(){

			return monster.getCenterY();

		}
		//method setter
		public void setY(double y){

			monster.setCenterY(y);

		}
		//method setter
		public void setX(double x){

			monster.setCenterX(x);
		}
	    public Node getNode(){

			return monster;

		}
		//method for removing monster
		 public void remove(Pane gamePane){

				gamePane.getChildren().remove(monster);
		    }
}
