package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Platform {
	private Node platform;

	public Platform(Pane gamePane) throws FileNotFoundException{
		//setting image 
		platform = new ImageView(new Image(new FileInputStream("C:\\Users\\user\\Desktop\\greenF.png")));
		gamePane.getChildren().add(platform);
		//setting start point x
		platform.setLayoutX(Constants.PLATFORMSTARTX);
//setting start point y
		platform.setLayoutY(Constants.PLATFORMSTARTY);

	}
//getter
	public double getY(){

		return platform.getLayoutY();

	}
//setter
	public void setY(double y){

		platform.setLayoutY(y);

	}
//setter
	public void setX(double x){

		platform.setLayoutX(x);

	}
//getter
	public double getX(){

		return platform.getLayoutX();

	}

	public Node getNode(){

		return platform;

	}
//method for removing platform
	public void remove(Pane gamePane){

		gamePane.getChildren().remove(platform);

	}

}
