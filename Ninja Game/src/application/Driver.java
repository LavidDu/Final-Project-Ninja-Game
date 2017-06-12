package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
/**
 * Main driver
 * 
 * @author Andreas Alvear
 * 
 */
public class Driver extends Application {

	public Pane root;
	public Character player;
	public Sword sword;
	public Image background = new Image("/Images/BackGround.png", 650, 400, true, false);
	public Image scoreboard = new Image("/Images/ScoreBoard.png", 650, 150, true, false);
	public Image bloodsplatter = new Image("/Images/bloodsplatter.png", 100, 100, true, false);
	public Image EndGameScreen = new Image("/Images/EndGameScreen.png", 400, 220, true, false);
	public Image menubackground = new Image("/Images/menubackground.png", 650, 550, true, false);
	public Image instructionsbg= new Image("/Images/instructionsbackground.png", 650, 550, true, false);
	public ImageView playbutton = new ImageView(new Image("/Images/playbutton.png", 80, 80, true, false));
	public ImageView instructionbutton = new ImageView(new Image("/Images/questionmark.png", 80, 80, true, false));
	public GraphicsContext gc;
	public BooleanProperty upPressed = new SimpleBooleanProperty(false);
	public BooleanProperty downPressed = new SimpleBooleanProperty(false);
	public BooleanProperty leftPressed = new SimpleBooleanProperty(false);
	public BooleanProperty rightPressed = new SimpleBooleanProperty(false);
	public ArrayList<Projectile> projectiles;
	public AnimationTimer timer;
	
	public boolean begin=false;

	public Stage window;
	public Scene menu,instructions, game;
	
	/**
	 * Creates a game scene, setting up canvas, character, and projectiles. 
	 * 
	 * @return root, which contains all of the items to make the scene and start the game
	 */
	public Parent createGame() {

		root = new Pane();
		Canvas canvas = new Canvas(650, background.getHeight() + scoreboard.getHeight());
		root.getChildren().add(canvas);
		
		gc = canvas.getGraphicsContext2D();
		player = new Character();
		sword = new Sword();
		projectiles = new ArrayList<>();
		root.getChildren().add(player.getView());
		root.getChildren().add(player.getText());
		root.getChildren().add(sword.getView());

		timer = new AnimationTimer() {
			int i = 0;

			@Override
			public void handle(long startNanoTime) {
				i++;
				reset(gc);
				updatePlayer();
				addProjectiles(i);
				updateProjectiles(timer);
			}
		};

		timer.start();
		return root;
	}
	
	/**
	 * Creates a menu scene, with the play and instruction buttons that lead to new scenes.
	 * 
	 * @return root, which contains the items that create the menu scene
	 */
	public Parent createMenu(){
		root=new Pane();
		Canvas canvas= new Canvas(650,550);
		gc=canvas.getGraphicsContext2D();
		gc.drawImage(menubackground, 0, 0);
		
		Button play=new Button();
		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				reset(gc);
				begin=true;
				window.setScene(game);
				
			}
		});
		play.setLayoutX(450);
		play.setLayoutY(340);
		play.setGraphic(playbutton);
		
		Button ins=new Button();
		ins.setOnAction(e->reset(gc));
		ins.setOnAction(e-> window.setScene(instructions));
		ins.setLayoutX(450);
		ins.setLayoutY(450);
		ins.setGraphic(instructionbutton);
		
		root.getChildren().addAll(canvas,play,ins);
		return root;
		
	}
	
	/**
	 * Creates an instructions scene, with a play button that starts a the game
	 * 
	 * @return root, which contains the items that create the instructions scene
	 */
	public Parent createInstructions(){
		root=new Pane();
		Canvas canvas= new Canvas(650,550);
		gc=canvas.getGraphicsContext2D();
		gc.drawImage(instructionsbg, 0, 0);
		
		Button play=new Button();
		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				reset(gc);
				begin=true;
				window.setScene(game);
				
			}
		});
		play.setLayoutX(450);
		play.setLayoutY(400);
		play.setGraphic(playbutton);
		
		root.getChildren().addAll(canvas,play);
		return root;
	}
	
	/**
	 * Creates projectile objects only when the game has started
	 * 
	 * @param u number of frames that passed
	 */
	public void addProjectiles(int u) {
		if (u % 60 == 0)
			if (begin==true)
				for (int i = 0; i < 1; i++) {
					double x1 = Math.random() * 200 + 1;
					double x2 = Math.random() * 240 + 400;
					double x = Math.random() > 0.5 ? x1 : x2;
					Projectile p = new Projectile(x, Math.random() * 300 + 1);
					p.setVelocity(gc, x == x2);
					projectiles.add(p);
					root.getChildren().add(p.getView());
			}
	}
	
	/**
	 * Updates location of projectiles, and whether collisions have occurred.
	 * 
	 * @param timer is the timer that starts at the beginning of the game
	 */
	public void updateProjectiles(AnimationTimer timer) {
		for (Projectile p : projectiles) {
			if (!p.isAlive())
				continue;
			p.update();
			if (sword.Collide(gc, p)) {
				player.updateScore(1);
				root.getChildren().remove(p.getView());
				p.setAlive(false);
			}
			if (p.collidewithPlayer() && p.isAlive()) {
				gc.drawImage(bloodsplatter, player.getTargetX() -30 , player.getTargetY() - 30);
				root.getChildren().remove(player.getText());
				gc.drawImage(EndGameScreen, 140, 100);
				gc.setFont(new Font(32));
				gc.setFill(Color.WHITE);
				gc.fillText("Score: " + player.getScore(), 265, 260);
				timer.stop();
			}
		}
	}
	
	/**
	 * Updates the state of the character based on buttons pressed.
	 */
	public void updatePlayer() {
		if (downPressed.get() && leftPressed.get()) {
			player.setBlockDir(BlockDir.downLeft);
			sword.setBlockDir(BlockDir.downLeft);
		} else if (downPressed.get() && rightPressed.get()) {
			player.setBlockDir(BlockDir.downRight);
			sword.setBlockDir(BlockDir.downRight);
		} else if (leftPressed.get() && upPressed.get()) {
			player.setBlockDir(BlockDir.upLeft);
			sword.setBlockDir(BlockDir.upLeft);
		} else if (rightPressed.get() && upPressed.get()) {
			player.setBlockDir(BlockDir.upRight);
			sword.setBlockDir(BlockDir.upRight);
		} else if (upPressed.get() || downPressed.get()) {
			player.setBlockDir(BlockDir.up);
			sword.setBlockDir(BlockDir.up);
		} else if (leftPressed.get()) {
			player.setBlockDir(BlockDir.left);
			sword.setBlockDir(BlockDir.left);
		} else if (rightPressed.get()) {
			player.setBlockDir(BlockDir.right);
			sword.setBlockDir(BlockDir.right);
		}
		player.switchState();
		sword.switchState();
	}

	/**\
	 * resets the game
	 * 	
	 * @param gc the graphics context 2D of the main game
	 */
	public void reset(GraphicsContext gc) {
		player.setBlockDir(BlockDir.rest);
		sword.setBlockDir(BlockDir.rest);
		gc.drawImage(background, 0, 0);
		gc.drawImage(scoreboard, 0, background.getHeight());
	}

	@Override
	/**
	 * Java fx programs follow the instructions on the start method
	 */
	public void start(Stage stage) {
		window=stage;

		menu=new Scene(createMenu());
		instructions=new Scene(createInstructions());
		game=new Scene(createGame());
		
		window.setScene(menu);

		game.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					upPressed.set(true);
				}
				if (event.getCode() == KeyCode.DOWN) {
					downPressed.set(true);
				}
				if (event.getCode() == KeyCode.LEFT) {
					leftPressed.set(true);
				}
				if (event.getCode() == KeyCode.RIGHT) {
					rightPressed.set(true);
				}
			}

		});

		game.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					upPressed.set(false);
				}
				if (event.getCode() == KeyCode.DOWN) {
					downPressed.set(false);
				}
				if (event.getCode() == KeyCode.LEFT) {
					leftPressed.set(false);
				} 
				if (event.getCode() == KeyCode.RIGHT) {
					rightPressed.set(false);
				}
			}

		});
		window.show();
	}
	
	/**
	 * Actually unnecessary for javafx
	 * 
	 * @param args string arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
