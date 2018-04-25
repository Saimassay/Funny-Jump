package game;

import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Smile extends Pane {
	//attributes
	private Circle smile;
	private double Velocity;
	

	public Smile(Pane gamePane) throws FileNotFoundException{
    //creating circle
     smile = new Circle(25);
        // setting color
		smile.setFill(Color.YELLOW);
//adding smile to game pane
	gamePane.getChildren().add(smile);
//setting starting coordinates x,y
		smile.setCenterX(Constants.SMILESTARTX);

		smile.setCenterY(Constants.SMILESTARTY);

	}
	
	                       

	//method getter
	public double getX(){

		return smile.getCenterX();

	}
	//method getter
	public double getY(){

		return smile.getCenterY();

	}
	//method setter
	public void setY(double y){

		smile.setCenterY(y);

	}
	//method setter
	public void setX(double x){

		smile.setCenterX(x);
	}
	
    public Node getNode(){

		return smile;

	}
    //method for updating velocity
	public double updateVelocity(){

		Velocity = Velocity + ((double) Constants.GRAVITY) *

		(((double)Constants.DURATION)/1000.0);

		return Velocity;			

	}
	//setter
	public void setVelocity(int velocity){

		Velocity = velocity;

	}
	//method updates velosity
	public void updatePosition(){

		smile.setCenterY(smile.getCenterY()+Velocity*

		((double)Constants.DURATION)/1000.0);

	 }
	//removes smile
    public void remove(Pane gamePane){

		gamePane.getChildren().remove(smile);
    }

	

	

	
}
