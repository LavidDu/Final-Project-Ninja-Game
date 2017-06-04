package application;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Driver extends Application {

	public Pane root;
	public Character player;
	public Sword sword;
	public Image background = new Image("/Images/BackGround.png", 650, 400, true, false);
	public Image scoreboard = new Image("/Images/ScoreBoard.png", 650, 150, true, false);
	public GraphicsContext gc;
	public BooleanProperty upPressed = new SimpleBooleanProperty(false);
	public BooleanProperty downPressed = new SimpleBooleanProperty(false);
	public BooleanProperty leftPressed = new SimpleBooleanProperty(false);
	public BooleanProperty rightPressed = new SimpleBooleanProperty(false);
	public ArrayList<Projectile> projectiles;

	public Parent createContent() {
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
		root.getChildren().add(player.c);
		
		AnimationTimer timer = new AnimationTimer() {
			int i = 0;

			@Override
			public void handle(long startNanoTime) {
				i++;
				reset(gc);
				updatePlayer();
				addProjectiles(i);
				updateProjectiles();
			}
		};

		timer.start();
		return root;
	}
	
	public void addProjectiles(int u){
		if(u % 60 == 0)for (int i = 0; i < 1; i++) {
			double x1 = Math.random() * 200 + 1;
			double x2 = Math.random() * 240 + 400;
			double x = Math.random() > 0.5 ? x1 : x2;
			Projectile p = new Projectile(x, Math.random() * 300 + 1);
			p.setVelocity(gc, x == x2);
			projectiles.add(p);
			root.getChildren().add(p.getView());
		}
	}
	
	public void updateProjectiles(){
		for (Projectile p : projectiles) {
			if(!p.isAlive())
				continue;
			p.update();
			if(sword.Collide(gc, p)){
				player.updateScore(1);
				root.getChildren().remove(p.getView());
				p.setAlive(false);
			}
			if(p.collidewithPlayer() && p.isAlive()){
				System.out.println("DEAD");
			}
		}
	}

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

	public void reset(GraphicsContext gc) {
		player.setBlockDir(BlockDir.rest);
		sword.setBlockDir(BlockDir.rest);
		gc.drawImage(background, 0, 0);
		gc.drawImage(scoreboard, 0, background.getHeight());
	}

	@Override
	public void start(Stage stage) {

		Scene scene = new Scene(createContent());
		stage.setScene(scene);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
		stage.show();
	}

	public void stop() {

	}

	public static void main(String[] args) {
		launch(args);
	}

}
