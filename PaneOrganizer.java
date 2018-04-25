package game;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.event.*;

//for event handlers

import javafx.geometry.Insets;

//positioning on the vbox

import javafx.geometry.Pos;

import java.io.FileNotFoundException;

//import for the timeline

import javafx.animation.Animation;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.util.Duration;

public class PaneOrganizer {
//attributes
	private BorderPane root;

	public static Pane gamePane;

	public static Game game;

	private Timeline timeline;

	public PaneOrganizer() throws FileNotFoundException{

		root = new BorderPane();

		this.gamePane();

		this.setupTimeline();

	}


//getter for border pane
	public BorderPane getRoot(){

		return root;

	}
//game pane compounds
	public void gamePane() throws FileNotFoundException{

		gamePane = new Pane();

		gamePane.setPrefSize(Constants.GAMEWIDTH, Constants.GAMEHEIGHT);

        gamePane.setStyle("-fx-background-color: \r\n" + 
        		"\r\n" + 
        		"  #99d6ff;");	
		root.setCenter(gamePane);
		
		

		game = new Game(gamePane);

		gamePane.setFocusTraversable(true); 

		gamePane.requestFocus();
		
	}
//animation
    public void setupTimeline(){

		KeyFrame kf = new KeyFrame(Duration.millis(Constants.DURATION), new TimeHandler());
		timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private static class TimeHandler implements EventHandler<ActionEvent>{ 

		public void handle(ActionEvent event){
//calling methods 
				game.checkCollision();

				game.scrollScreen();
				
				game.updatePlat(gamePane);
//exception handler in case image is not found
				try {
					game.addPlat(gamePane);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//exception handler
				try {
				game.checkCollisionMonster();
				}
				catch(Exception e1){
					e1.printStackTrace();
					
				}
				game.scrollScreen();
			
				game.updateMonster(gamePane);

				try {
					game.addMonster(gamePane);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//if position of smile is bigger than game height call gameOverScene method
			    if (game.smilePos() > Constants.GAMEHEIGHT){
			    	gameOverScene();	
				}
			    //if position of smile and monster equals call definite method
			    else if (game.smilePos()==game.monsterPos()) {
			    	gameOverScene();
			    	
			    }

			}
	public static void gameOverScene() {
	//gameover label new game and main menu buttons and its settings
		
		Label gameOverLabel = new Label("GAME OVER!");
		gameOverLabel.setFont(new Font("Arial",45));
		gameOverLabel.setTextFill(Color.RED);
	   
		Button mainMenu = new Button("Main Menu");
		mainMenu.setStyle("-fx-font-size: 15pt;");
		mainMenu.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("MainScene was pressed");
				App.window.setScene(App.mainScene);
				game.removeSmile(gamePane);
				game.removePlatform(gamePane);
				//game.removeMonster(gamePane);
			}
			
		});
		Button newGame = new Button("New Game");
		newGame.setStyle("-fx-font-size: 15pt;");
		//newGame.setTextFill(Color.RED);
		newGame.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Button new Game was pressed");
			}
			
		});
		gameOverLabel.setAlignment(Pos.CENTER);
		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren().addAll(mainMenu, newGame);
		VBox vb = new VBox();
		vb.setSpacing(20);
		vb.setPadding(new Insets(100,100,100,100));
		
		vb.getChildren().addAll(gameOverLabel,hb);
		BorderPane bp = new BorderPane();
		bp.setCenter(vb);
		gamePane.getChildren().addAll(bp);
	
	
	}

}

	
		
	}