package application;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GameObject {
	
	private ImageView view;
	private boolean alive = true;
	private int x, y, velX, velY;
	private Point2D velocity = new Point2D(0,0);
	
	public GameObject(ImageView view){
		this.view = view;
	}
	
	public ImageView getView(){
		return view;
	}
	
	public Point2D getVelocity(){
		return velocity;
	}
	
	public void setVelocity(int dx, int dy){
		velocity = velocity.add(dx, dy);
	}
	
	public void update(){
		view.setTranslateX(view.getTranslateX() + velocity.getX());
		view.setTranslateY(view.getTranslateY() + velocity.getY());
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setAlive(boolean value){
		alive = value;
	}
	
	public boolean isColliding(GameObject other){
		return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		view.setX(x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		view.setY(y);
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
}
