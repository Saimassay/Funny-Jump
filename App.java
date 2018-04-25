package game;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class App extends Application {
	//attributes
public static Stage window;
public static Scene gameScene;
public static Scene mainScene;



    @Override

	public void start(Stage window) throws FileNotFoundException {
    	App.window = window;
    	//label
    	Label label = new Label("Funny Jump");
    	//font of label
		label.setFont(new Font("Arial",45));
		//declaring play button
	    Button play = new Button("PLAY");
	    play.setId("play");
	    //setting style of button
		play.setStyle("-fx-background-color: FF66B2;");
		play.setMinSize(100,70);
		play.setStyle("-fx-background-radius: 120em;"+
		"-fx-min-width:120px;"+
				"-fx-min-height:120px;"+
		"-fx-max-width: 5px;"+"-fx-max-height: 120px;"+"-fx-background-color: FF66B2;");
		play.setPadding(new Insets(10,10,10,10));
		//creating setting button
		Button setting = new Button("SETTINGS");
		//setting style
		setting.setMinSize(250,100);
		setting.setPadding(new Insets(40));
		setting.setStyle("-fx-background-radius: 250px;"+"-fx-background-color: FFFF99; ");
		setting.setShape(new Circle(150));
		setting.setPadding(new Insets(10,10,10,10));
		//creating about button
		Button about = new Button("ABOUT");
		//setting style
		about.setMinSize(175,90);
		about.setShape(new Circle(150));
		about.setPadding(new Insets(10,10,10,10));
		about.setStyle("-fx-background-color: #66B2FF;");
		//creating quit button
		Button quit = new Button ("QUIT");
		//setting style
		quit.setMinSize(120,70);
		quit.setShape(new Circle(150));
		quit.setPadding(new Insets(10,10,10,10));
		quit.setStyle("-fx-background-color: #66B2FF;");
		//setting action on button
		quit.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.close();
			}
			
		});
		//creating vertical box
		VBox vMenu = new VBox();
		//adding components
		vMenu.getChildren().addAll(label,play,setting,about,quit);
		//setting paddings and style
		vMenu.setPadding(new Insets(20,20,20,20));
		vMenu.setSpacing(10);
		vMenu.setStyle("-fx-font-size: 25pt; "+"-fx-background-color: #00FFFF;");
		vMenu.setAlignment(Pos.CENTER);
		//creating label
		Label info = new Label("Designed in SDU \r\nby Gulnara Saimassay\r\n"
				+ " Version 10.1712.3351.0 \r\n"
				+ "All rights reserved");
		//setting style
		info.setStyle("-fx-font-size: 15pt; -fx-text-fill: linear-gradient(to right,#FF9999, #FFCCCC) ");
		info.setAlignment(Pos.CENTER);
		//creating back button
		Button backToMenu = new Button("<-");
		backToMenu.setId("back");
		
		backToMenu.setStyle("-fx-font-size: 10pt;"+"-fx-background-color: transparent;");
		//creating border pane
		BorderPane backBtn = new BorderPane();
		
		backBtn.setTop(backToMenu);
		//creating vertical box 
		VBox About = new VBox();
		About.getChildren().addAll(backBtn,info);
		
		About.setStyle("-fx-background: linear-gradient(#3333FF,#99FFFF)");
		//creating scene
		Scene aboutScene = new Scene(About,Constants.PANE_WIDTH,Constants.PANE_HEIGHT);
		aboutScene.getStylesheets().add(getClass().getResource("design.css").toString());
	    PaneOrganizer organizer = new PaneOrganizer();
		Scene playScene = new Scene(organizer.getRoot(),Constants.PANE_WIDTH,Constants.PANE_HEIGHT);
		playScene.getStylesheets().addAll(this.getClass().getResource("design.css").toExternalForm());
		//vertical box for setting scene
		VBox settingMenu = new VBox();
	
		Button On = new Button("ON");
		On.setId("onBtn");
		On.setStyle("-fx-font-size: 10pt;"+"-fx-background-color: transparent;");
		Button Off = new Button("OFF");
		Off.setId("offBtn");
		Off.setStyle("-fx-font-size: 10pt;"+"-fx-background-color: transparent;");
		//adding sound 
		final java.net.URL resource = getClass().getResource("mystery.wav");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        //setting action on On button
        On.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 mediaPlayer.play();
			}
        	
        });
        //setting action on Off 
        Off.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
			}
        	
        });
        
        //style for on and off
		On.setStyle("-fx-font-size: 12pt;");
		Off.setStyle("-fx-font-size: 12pt;");
		//label music
		Label musicLabel = new Label("MUSIC");
		musicLabel.setStyle("-fx-font-size: 15pt;"+"-fx-font-color:red;");
		//label for control buttons
		Label control = new Label("Control Buttons: ");
		control.setStyle("-fx-font-color: white;"+"-fx-font-size: 15pt;");
		//hbox for setting scene 
		HBox MSound = new HBox();
		MSound.setPadding(new Insets(20,20,20,20));
		MSound.setSpacing(20);
		MSound.getChildren().addAll(musicLabel,On,Off);
		MSound.setAlignment(Pos.CENTER);
		//button back
		Button back = new Button("<-");
		
		back.setStyle("-fx-font-size: 12pt;"+"-fx-background-color: transparent;");
		Button left = new Button("<-");
		Button right = new Button("->");
		BorderPane btnBack = new BorderPane();
		
		btnBack.setTop(back);
		
		left.setStyle("-fx-font-size: 12pt;"+"-fx-background-color: white;");
		right.setStyle("-fx-font-size: 12pt;"+"-fx-background-color: white;");
		HBox hb = new HBox();
		hb.getChildren().addAll(control,left,right);
		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER);
		
		settingMenu.setSpacing(10);
	settingMenu.setStyle("-fx-background: linear-gradient(white,black)");
		settingMenu.getChildren().addAll(btnBack,MSound,hb);
		//settingMenu.setAlignment(Pos.CENTER);
		//creating scene for settings
		Scene settingScene = new Scene(settingMenu,Constants.PANE_WIDTH,Constants.PANE_HEIGHT);
		settingScene.getStylesheets().add(getClass().getResource("design.css").toString());
		//main scene borders
		 mainScene = new Scene(vMenu,Constants.PANE_WIDTH,Constants.PANE_HEIGHT);
		mainScene.getStylesheets().add(getClass().getResource("design.css").toString());
		window.setScene(mainScene);
		window.show();
		//setting action on back button
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(mainScene);
			}
			
		});
		Button back2 = new Button("back");
		back2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(mainScene);
			}
			
	});
		
		play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(playScene);
			}
			
		});
		setting.setOnAction(new EventHandler <ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(settingScene);
				
			}

			
		});
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(aboutScene);
			}
			
		});
		backToMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				window.setScene(mainScene);
			}
			
		});

  }

	public static void main(String[] argv) {

        	// launch is a method inherited from Application

		launch(argv);

	}

}
