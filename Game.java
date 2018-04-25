package game;

import javafx.scene.layout.Pane;


//for event handlers

import javafx.event.*;

import javafx.scene.input.*;

import java.io.FileNotFoundException;

//needed for the array list

import java.util.ArrayList;

public class Game {
//declaring attributes
	private Smile smile;
	private Monster monster;
	private Platform platform;
	private ArrayList<Platform> arrayPlat;
	private ArrayList<Monster> arrayMonster;
	private int rndx;
	private int rndy;
	private int rnd;
	private int randomX;
	private int randomY;
	private int random;
	private int arrayNum;
	private int arrayNumMonster;
	private Pane gamePane;

	

	public Game(Pane gamePane) throws FileNotFoundException{

		platform = new Platform(gamePane);
		smile = new Smile(gamePane);
		monster = new Monster(gamePane);
		this.createArray(gamePane);
		this.createPlatforms(gamePane);
		this.createArrayMonster(gamePane);	
		this.createMonster(gamePane);
		gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());



	}
	
//generates number randomly
	public int generateNumber(double low, double high){

		rnd = (int)((high - low+1 )*Math.random() + low);

		return rnd;						

	}
	

	
//creates array 
	public ArrayList createArray(Pane gamePane){

		arrayPlat = new ArrayList<Platform>();

		arrayPlat.add(platform);

		return arrayPlat;

	}	
	
     //creates platforms
	public ArrayList createPlatforms(Pane gamePane){

	    rndx = Constants.GAMEWIDTH/3;

	    rndy = Constants.GAMEHEIGHT/3;

	   arrayNum =arrayPlat.size();

		do{
      //exception handler in case file not found
	    			try {
						platform = new Platform(gamePane);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//generating y coordinates
				rndy= this.generateNumber(arrayPlat.get(arrayNum - 1).getY()-75, arrayPlat.get(arrayNum - 1).getY()-45);

				platform.setY(rndy);
				//generating y coordinates
				rndx = this.generateNumber(0, Constants.GAMEWIDTH);

				if (rndx > smile.getX() + 30 || rndx < smile.getX() - 30 && rndx != arrayPlat.get(arrayNum-1).getX() ){

					platform.setX(rndx);

			}

		arrayPlat.add(platform);

		arrayNum = arrayPlat.size();

		} while(arrayNum < 20);

		return arrayPlat;

	}
	//smile jumps if smile intersects with platform
	public void checkCollision(){

				smile.updateVelocity();

				smile.updatePosition();

				if (smile.updateVelocity() > 0){

					for (int k = 0; k < arrayPlat.size(); k++){

						if (smile.getNode().intersects(arrayPlat.get(k).getX(), arrayPlat.get(k).getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT)){

							smile.setVelocity(Constants.REBOUND_VELOCITY);

						}

					}

				}

	}
	//method which makes screen scroll
	public void scrollScreen(){
//for smile
		if (smile.getY() < (Constants.GAMEHEIGHT/3)){

			for (int a = 0; a < arrayPlat.size(); a++){

				arrayPlat.get(a).setY(arrayPlat.get(a).getY() + (Constants.GAMEHEIGHT/3 - smile.getY()) );
						}
			//for monster
			for(Monster monster:arrayMonster) {
				monster.setY(monster.getY() + (Constants.GAMEHEIGHT/3 - smile.getY()) );

			}

			smile.setY(Constants.GAMEHEIGHT/3);

		}

	}

		
//method to update platforms when the screen scrolls
	public ArrayList updatePlat(Pane gamePane){

		for (int c = 0; c < arrayPlat.size(); c++){
//each time the platform's coordinates are higher than gameheight it removes platforms
			if (arrayPlat.get(c).getY() > Constants.GAMEHEIGHT){

				arrayPlat.get(c).remove(gamePane);

				arrayPlat.remove(c);

			}

		}

		return arrayPlat;

	}


//adds new platforms
	public ArrayList addPlat(Pane gamePane) throws FileNotFoundException{

		if (arrayPlat.size()<6){

			do{ 

				arrayNum = arrayPlat.size();

				platform = new Platform(gamePane);

				platform.setY(0);

				rndx = this.generateNumber(arrayPlat.get(arrayNum - 1).getX()-50, arrayPlat.get(arrayNum - 1).getX()+10);

			if (rndx > smile.getX() + 35 || rndx < smile.getX() - 35 && rndx != arrayPlat.get(arrayNum-1).getX() && rndx > 35 && rndx < Constants.GAMEWIDTH-35){
					platform.setX(rndx);

				} 

				arrayPlat.add(platform);

			} while (smile.getY() == Constants.GAMEHEIGHT/3 && smile.updateVelocity() == 0);

		}	

		return arrayPlat;

	}
	
	//methods that create monsters
	public int generateNumberMonster (double low, double high){

		random = (int)((high - low+1 )*Math.random() + high);

		return random;						

	}
	//creates array of monsters
	public ArrayList createArrayMonster(Pane gamePane){

		arrayMonster = new ArrayList<Monster>();

		arrayMonster.add(monster);

		return arrayMonster;

	}	
	//creates monsters
	public ArrayList createMonster(Pane gamePane) throws FileNotFoundException{
		
	   randomX = Constants.GAMEWIDTH/5;

	    randomY = Constants.GAMEHEIGHT/5;

	   arrayNumMonster =1; 

		do{

	    			monster = new Monster(gamePane);

				randomY= this.generateNumberMonster(arrayMonster.get(arrayNumMonster - 1).getY()+100, arrayMonster.get(arrayNumMonster - 1).getY()-10);

				monster.setY(randomY);

				randomX = this.generateNumberMonster(0, Constants.GAMEWIDTH);

		arrayMonster.add(monster);

		arrayNumMonster = arrayMonster.size();

		} while(arrayNumMonster <=1);

		return arrayMonster;

	}
	
	public void checkCollisionMonster(){

		if (smile.updateVelocity() > 0){

			for (int k = 0; k < arrayMonster.size(); k++){

				if (smile.getNode().intersects(arrayMonster.get(k).getX(), arrayMonster.get(k).getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT)){
					removeSmile(gamePane);
					
			}

			}

		}
	}


//every time when the screen scrolls it generates new monsters
	public ArrayList updateMonster(Pane gamePane){

		for (int c = 0; c < arrayMonster.size(); c++){

			if (arrayMonster.get(c).getY() > Constants.GAMEHEIGHT){

				arrayMonster.get(c).remove(gamePane);

				arrayMonster.remove(c);

			}

		}

		return arrayMonster;

	}


//adds monsters 
	public ArrayList addMonster(Pane gamePane) throws FileNotFoundException{

		if (arrayMonster.size()<=1){

			do{ 

				arrayNumMonster = arrayMonster.size();

				monster = new Monster(gamePane);

				monster.setY(0);

				randomY = this.generateNumber(arrayMonster.get(arrayNumMonster - 1).getX()-15, arrayMonster.get(arrayNumMonster - 1).getX()+15);

				arrayMonster.add(monster);

			} while (smile.getY() == Constants.GAMEHEIGHT/3 && smile.updateVelocity() == 0);

		}	

		return arrayMonster;

	}


//key handler to control left and right movements
	private class KeyHandler implements EventHandler<KeyEvent>{

		public void handle(KeyEvent e){

			KeyCode keyPressed = e.getCode();

			if (keyPressed == KeyCode.LEFT){

				smile.setX(smile.getX()-20);

			} else if (keyPressed == KeyCode.RIGHT){

				smile.setX(smile.getX()+20);

			}
			

			e.consume();

		}

	}
//method position of smile in y coordinate
	public double smilePos(){

		return smile.getY();

	}
//removes smile from game pane
    public void removeSmile(Pane gamePane){

			smile.remove(gamePane);

	}
    //position of monster in y coordinate
     public double monsterPos(){

		return monster.getY();

	}
     //removes monster
    public void removeMonster(Pane gamePane){

		monster.remove(gamePane);

}
    //remove platform
    public void removePlatform(Pane gamePane){

		platform.remove(gamePane);

}
    

}