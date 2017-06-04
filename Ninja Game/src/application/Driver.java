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
import javafx.stage.Stage;

public class Driver extends Application {

	public Pane root;
	public Character player;
	public Sword sword;
	public Image background = new Image("BackGround.png", 650, 400, true, false);
	public GraphicsContext gc;
	public BooleanProperty upPressed = new SimpleBooleanProperty(false);
	public BooleanProperty downPressed = new SimpleBooleanProperty(false);
	public BooleanProperty leftPressed = new SimpleBooleanProperty(false);
	public BooleanProperty rightPressed = new SimpleBooleanProperty(false);
	public ArrayList<Projectile> projectiles;

	public Parent createContent() {
		root = new Pane();
		Canvas canvas = new Canvas(650, 400);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();
		player = new Character();
		sword = new Sword();
		projectiles = new ArrayList<>();
		root.getChildren().add(player.getView());
		root.getChildren().add(sword.getView());
		
		for(int i = 0; i < 10; i++){
			Projectile p = new Projectile(player.getX(), player.getY());
			p.setX((int)(Math.random() * 200) + 1);
			p.setY((int)(Math.random() * 200) + 1);
			p.setVelocity(2, 2);
			projectiles.add(p);
			root.getChildren().add(p.getView());
		}
		
		AnimationTimer timer = new AnimationTimer() {
			int i = 0;
			@Override
			public void handle(long startNanoTime) {
				i++;
				reset(gc);
				for(Projectile p : projectiles){
					p.update();
					if(p.collideWithSword(sword)){
					}
				}

				if(i %10 ==0)for(int i = 0; i < 1; i++){

					Projectile p = new Projectile(player.getX(), player.getY());
					p.setX((int)(Math.random() * 200) + 1);
					p.setY((int)(Math.random() * 200) + 1);
					p.setVelocity(2, 2);
					projectiles.add(p);
					root.getChildren().add(p.getView());
				}
				updatePlayer();
				sword.test(gc);
			}
		};

		timer.start();
		return root;
	}

	public void updatePlayer() {
		if (leftPressed.get() && downPressed.get()) {
			player.setBlockDir(BlockDir.downLeft);
			sword.setBlockDir(BlockDir.downLeft);
		} else if (rightPressed.get() && downPressed.get()) {
			player.setBlockDir(BlockDir.downRight);
			sword.setBlockDir(BlockDir.downRight);
		} else if (leftPressed.get() && upPressed.get()) {
			player.setBlockDir(BlockDir.upLeft);
			sword.setBlockDir(BlockDir.upLeft);
		} else if (rightPressed.get() && upPressed.get()) {
			player.setBlockDir(BlockDir.upRight);
			sword.setBlockDir(BlockDir.upRight);
		} else if (leftPressed.get()) {
			player.setBlockDir(BlockDir.left);
			sword.setBlockDir(BlockDir.left);
		} else if (rightPressed.get()) {
			player.setBlockDir(BlockDir.right);
			sword.setBlockDir(BlockDir.right);
		} else if (upPressed.get()){
			player.setBlockDir(BlockDir.up);
			sword.setBlockDir(BlockDir.up);
		}
		player.switchState();
	}

	public void reset(GraphicsContext gc) {
		player.setBlockDir(BlockDir.rest);
		sword.setBlockDir(BlockDir.rest);
		gc.drawImage(background, 0, 0);
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
